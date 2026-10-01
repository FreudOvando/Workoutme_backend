package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CompetitionRequest(

        @NotBlank(message = "El nombre de la competencia es obligatorio")
        @Size(max = 150, message = "El nombre no puede superar 150 caracteres")
        String name,

        @NotNull(message = "La fecha es obligatoria")
        LocalDate competitionDate
) {}