package com.example.demo.dto;

import com.example.demo.model.CompetitionCategory;
import jakarta.validation.constraints.NotNull;

public record CompetitionEnrollmentRequest(

        @NotNull(message = "El usuario es obligatorio")
        Long userId,

        @NotNull(message = "La categoría es obligatoria")
        CompetitionCategory category
) {}