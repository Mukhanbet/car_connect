package com.example.car_connect.mapper.impl;

import com.example.car_connect.mapper.UserMapper;
import com.example.car_connect.model.domain.User;
import com.example.car_connect.model.dto.user.OwnerRequest;
import com.example.car_connect.model.dto.user.OwnerResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public User toUser(User user, OwnerRequest request) {
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        return user;
    }

    @Override
    public OwnerResponse toOwnerResponse(User user) {
        OwnerResponse response = new OwnerResponse();
        response.setName(user.getName());
        response.setImage(user.getImage().getPath());
        return response;
    }

    @Override
    public List<OwnerResponse> toOwnerResponses(List<User> users) {
        List<OwnerResponse> responses = new ArrayList<>();
        for (User user : users) {
            responses.add(toOwnerResponse(user));
        }
        return responses;
    }
}
