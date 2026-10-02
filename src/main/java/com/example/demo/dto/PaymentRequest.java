package com.example.demo.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PaymentRequest(

        @NotNull(message = "El usuario es obligatorio")
        Long userId,

        // Solo la usa el ADMIN; si la manda un atleta, el backend la ignora
        @DecimalMin(value = "0.0", message = "El monto no puede ser negativo")
        BigDecimal amount,

        // Opcional: si no se manda, se usa la fecha de hoy
        LocalDate paymentDate
) {}