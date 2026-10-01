package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ScoreRequest(

        @NotNull(message = "El usuario es obligatorio")
        Long userId,

        @NotNull(message = "El WOD es obligatorio")
        Long wodId,

        @NotNull(message = "Indica si completaste el WOD")
        Boolean completed,

        @Size(max = 255, message = "El resultado no puede superar 255 caracteres")
        String result
) {
    public static record CompetitionRequest(

            @NotBlank(message = "El nombre de la competencia es obligatorio")
            @Size(max = 150, message = "El nombre no puede superar 150 caracteres")
            String name,

            @NotNull(message = "La fecha es obligatoria")
            LocalDate competitionDate
    ) {}
}