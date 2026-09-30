package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ScoreRequest(

        @NotNull(message = "El usuario es obligatorio")
        Long userId,

        @NotNull(message = "El WOD es obligatorio")
        Long wodId,

        @NotNull(message = "Indica si completaste el WOD")
        Boolean completed,

        @Size(max = 255, message = "El resultado no puede superar 255 caracteres")
        String result
) {}