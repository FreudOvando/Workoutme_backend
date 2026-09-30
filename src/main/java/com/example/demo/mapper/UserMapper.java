package com.example.demo.mapper;

import com.example.demo.dto.UserResponse;
import com.example.demo.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthDate(),
                user.getEmail(),
                user.getPhotoUrl(),
                user.getRole(),
                user.getCreatedAt()
        );
    }
}