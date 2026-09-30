package com.example.demo.dto;

import jakarta.validation.constraints.Size;

public record UpdatePhotoRequest(
        @Size(max = 2048, message = "La URL no puede superar 2048 caracteres")
        String photoUrl
) {}