package com.example.car_connect.controller;

import com.example.car_connect.model.domain.CarImage;
import com.example.car_connect.model.dto.image.CarImageResponse;
import com.example.car_connect.service.CarImageService;
import com.google.common.net.HttpHeaders;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLConnection;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/images")
public class CarImageController {
    private final CarImageService carImageService;

    @PostMapping("/upload")
    public CarImageResponse uploadCarImage(MultipartFile image) {
        return carImageService.uploadCarImage(image);
    }

    @GetMapping("/download/{imageId}")
    public ResponseEntity<?> downloadCarImage(@PathVariable UUID imageId) {
        CarImage carImage = carImageService.getCarImageData(imageId);
        String fileName = carImage.getName();
        String contentType = URLConnection.guessContentTypeFromName(fileName);

        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                .body(carImageService.downloadCarImage(imageId));
    }

    @DeleteMapping("/delete/{imageId}")
    public void deleteCarImage(@PathVariable UUID imageId) {
        carImageService.deleteCarImage(imageId);
    }
}
