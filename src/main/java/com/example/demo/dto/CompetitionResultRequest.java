package com.example.demo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CompetitionResultRequest(

        @NotNull(message = "El WOD de la competencia es obligatorio")
        Long stageId,

        @NotNull(message = "La inscripción es obligatoria")
        Long enrollmentId,

        @NotNull(message = "El porcentaje es obligatorio")
        @Min(value = 0, message = "El porcentaje no puede ser menor a 0")
        @Max(value = 100, message = "El porcentaje no puede ser mayor a 100")
        Integer percentage
) {}