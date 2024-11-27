package com.example.car_connect.controller;

import com.example.car_connect.model.dto.image.CarImageResponse;
import com.example.car_connect.service.ImageService;
import com.google.common.net.HttpHeaders;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLConnection;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/images")
public class CarImageController {
    private final ImageService imageService;

    @PostMapping("/upload/{carId}")
    public CarImageResponse uploadCarImage(
            @RequestPart List<MultipartFile> images,
            @RequestPart MultipartFile fonImage,
            @PathVariable UUID carId
    ) {
        return imageService.uploadCarImage(images, fonImage, carId);
    }

    @GetMapping("/cars/{carId}")
    public List<CarImageResponse> getImages(
            @PathVariable UUID carId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return imageService.getImages(carId, page, size);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadCarImage(@PathVariable String fileName) {
        String contentType = URLConnection.guessContentTypeFromName(fileName);

        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                .body(imageService.downloadCarImage(fileName));
    }

    @DeleteMapping("/delete/{imageId}")
    public void deleteCarImage(@PathVariable UUID imageId) {
        imageService.deleteCarImage(imageId);
    }
}
