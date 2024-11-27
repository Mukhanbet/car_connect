package com.example.car_connect.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.example.car_connect.exception.CustomException;
import com.example.car_connect.mapper.ImageMapper;
import com.example.car_connect.model.domain.Car;
import com.example.car_connect.model.domain.Image;
import com.example.car_connect.model.domain.User;
import com.example.car_connect.model.dto.image.CarFonImageResponse;
import com.example.car_connect.model.dto.image.CarImageResponse;
import com.example.car_connect.repository.ImageRepository;
import com.example.car_connect.repository.CarRepository;
import com.example.car_connect.service.ImageService;
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
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final CarRepository carRepository;
    private final ImageMapper imageMapper;
    private final AmazonS3 s3Client;
    @Value("${application.bucket.name}")
    private String bucketName;

    @Override
    public CarFonImageResponse uploadCarImage(List<MultipartFile> images, MultipartFile fonImage, UUID carId) {
        if (images.isEmpty() || fonImage.isEmpty()) {
            throw new CustomException("Incorrect file", HttpStatus.BAD_REQUEST);
        }
        Car car = carRepository.findById(carId).orElseThrow(() -> new CustomException("Car not found", HttpStatus.NOT_FOUND));
        List<Image> carImages = new ArrayList<>();
        for (MultipartFile image : images) {
            File fileObj = convertMultipartFileToFile(image);
            String fileName = System.currentTimeMillis() + "_" + Objects.requireNonNull(image.getOriginalFilename()).replaceAll("\\s+", "_");
            s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            boolean delete = fileObj.delete();
            if (!delete) {
                throw new CustomException("Failed to delete file", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            Image carImage = imageMapper.toImage(fileName, s3Client.getUrl(bucketName, fileName).toString());
            carImage.setCarDetail(car);
            carImage = imageRepository.save(carImage);
            carImages.add(carImage);
        }

        File fileObj = convertMultipartFileToFile(fonImage);
        String fileName = System.currentTimeMillis() + "_" + Objects.requireNonNull(fileObj.getName()).replaceAll("\\s+", "_");
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        boolean delete = fileObj.delete();
        if (!delete) {
            throw new CustomException("Failed to delete file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Image image = imageRepository.save(imageMapper.toImage(fileName, s3Client.getUrl(bucketName, fileName).toString()));
        car.setFonImage(image);
        car.setImages(carImages);
        carRepository.save(car);
        return imageMapper.toCarFonImageResponse(image, carImages);
    }

    @Override
    public List<CarImageResponse> getImages(UUID carId, int page, int size) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new CustomException("Car not found", HttpStatus.NOT_FOUND));
        return imageMapper.toCarImageResponseList(imageRepository.findAllByCar(car, PageRequest.of(page, size)));
    }

    @Override
    public byte[] downloadCarImage(String fileName) {
        Image image = imageRepository.findByName(fileName).orElseThrow(() -> new CustomException("Image not found", HttpStatus.NOT_FOUND));
        S3Object s3Object = s3Client.getObject(bucketName, image.getName());
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            throw new CustomException("Error while downloading image", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deleteCarImage(UUID imageId) {
        Image image = imageRepository.findById(imageId).orElseThrow(() -> new CustomException("Image not found", HttpStatus.NOT_FOUND));
        s3Client.deleteObject(bucketName, image.getName());
        imageRepository.delete(image);
    }

    @Override
    public Image uploadOwnerImage(MultipartFile file, User owner) {
        File fileObj = convertMultipartFileToFile(file);
        String fileName = System.currentTimeMillis() + "_" + Objects.requireNonNull(fileObj.getName()).replaceAll("\\s+", "_");
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return imageRepository.save(imageMapper.toImage(fileName, s3Client.getUrl(bucketName, fileName).toString()));
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
