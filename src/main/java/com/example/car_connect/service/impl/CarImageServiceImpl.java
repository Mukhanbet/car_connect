package com.example.car_connect.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.example.car_connect.exception.CustomException;
import com.example.car_connect.mapper.CarImageMapper;
import com.example.car_connect.model.domain.Car;
import com.example.car_connect.model.domain.CarImage;
import com.example.car_connect.model.dto.image.CarImageResponse;
import com.example.car_connect.repository.CarImageRepository;
import com.example.car_connect.repository.CarRepository;
import com.example.car_connect.service.CarImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarImageServiceImpl implements CarImageService {
    private final CarImageRepository carImageRepository;
    private final CarRepository carRepository;
    private final CarImageMapper carImageMapper;
    private final AmazonS3 s3Client;
    @Value("${application.bucket.name}")
    private String bucketName;

    @Override
    public List<CarImageResponse> uploadCarImage(List<MultipartFile> images, UUID carId) {
        if (images.isEmpty()) {
            throw new CustomException("Incorrect file", HttpStatus.BAD_REQUEST);
        }
        Car car = carRepository.findById(carId).orElseThrow(() -> new CustomException("Car not found", HttpStatus.NOT_FOUND));
        List<CarImage> carImages = new ArrayList<>();
        for (MultipartFile image : images) {
            File fileObj = convertMultipartFileToFile(image);
            String fileName = System.currentTimeMillis() + "_" + Objects.requireNonNull(image.getOriginalFilename()).replaceAll("\\s+", "_");
            s3Client.putObject(bucketName, fileName, fileObj);
            boolean delete = fileObj.delete();
            if (!delete) {
                throw new CustomException("Failed to delete file", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            CarImage carImage = carImageRepository.save(carImageMapper.toCarImage(fileName, car));
            carImages.add(carImage);
        }
        return carImageMapper.toCarImageResponseList(carImages);
    }

    @Override
    public List<CarImageResponse> getImages(UUID carId, int page, int size) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new CustomException("Car not found", HttpStatus.NOT_FOUND));
        return carImageMapper.toCarImageResponseList(carImageRepository.findAllByCar(car, PageRequest.of(page, size)));
    }

    @Override
    public byte[] downloadCarImage(UUID imageId) {
        CarImage carImage = carImageRepository.findById(imageId).orElseThrow(() -> new CustomException("Image not found", HttpStatus.NOT_FOUND));
        S3Object s3Object = s3Client.getObject(bucketName, carImage.getName());
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            throw new CustomException("Error while downloading image", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deleteCarImage(UUID imageId) {
        CarImage carImage = carImageRepository.findById(imageId).orElseThrow(() -> new CustomException("Image not found", HttpStatus.NOT_FOUND));
        s3Client.deleteObject(bucketName, carImage.getName());
        carImageRepository.delete(carImage);
    }

    @Override
    public CarImage getCarImageData(UUID imageId) {
        return carImageRepository.findById(imageId).orElseThrow(() -> new CustomException("Image not found", HttpStatus.NOT_FOUND));
    }

    private File convertMultipartFileToFile(MultipartFile image) {
        File convertedFile = new File(Objects.requireNonNull(image.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(image.getBytes());
        } catch (IOException e) {
            throw new CustomException("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return convertedFile;
    }
}
