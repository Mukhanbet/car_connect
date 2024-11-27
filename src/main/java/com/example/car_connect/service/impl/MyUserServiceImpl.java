package com.example.car_connect.service.impl;

import com.example.car_connect.mapper.UserMapper;
import com.example.car_connect.model.domain.User;
import com.example.car_connect.model.dto.user.OwnerResponse;
import com.example.car_connect.repository.UserRepository;
import com.example.car_connect.service.AuthService;
import com.example.car_connect.service.ImageService;
import com.example.car_connect.service.MyUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class MyUserServiceImpl implements MyUserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ImageService imageService;
    private final AuthService authService;


    @Override
    public OwnerResponse uploadImage(MultipartFile file, String token) {
        User owner = authService.getUserFromToken(token);
        owner.setImage(imageService.uploadOwnerImage(file, owner));
        return userMapper.toOwnerResponse(userRepository.save(owner));
    }
}
