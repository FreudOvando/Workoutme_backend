package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CompetitionStageRequest(

        @NotBlank(message = "El nombre del WOD es obligatorio")
        @Size(max = 100, message = "El nombre no puede superar 100 caracteres")
        String name,

        @NotNull(message = "El orden es obligatorio")
        @Min(value = 1, message = "El orden debe ser al menos 1")
        Integer stageOrder
) {}