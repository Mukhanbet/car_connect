package com.example.car_connect.service;

import com.example.car_connect.model.dto.user.OwnerResponse;
import org.springframework.web.multipart.MultipartFile;

public interface MyUserService {
    OwnerResponse uploadImage(MultipartFile file, String token);
}
