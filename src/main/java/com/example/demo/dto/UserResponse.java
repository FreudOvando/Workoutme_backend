package com.example.demo.dto;

import com.example.demo.model.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        LocalDate birthDate,
        String email,
        String photoUrl,
        Role role,
        LocalDateTime createdAt
) {}