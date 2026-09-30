package com.example.demo.dto;

import com.example.demo.model.WodType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record WodRequest(

        @Size(max = 100, message = "El nombre no puede superar 100 caracteres")
        String name,

        @NotNull(message = "La fecha de publicación es obligatoria")
        LocalDate publicationDate,

        @NotBlank(message = "El nombre del coach es obligatorio")
        @Size(max = 100, message = "El nombre del coach no puede superar 100 caracteres")
        String coachName,

        @NotNull(message = "El tipo de WOD es obligatorio")
        WodType type,

        @NotBlank(message = "La descripción es obligatoria")
        String description
) {}