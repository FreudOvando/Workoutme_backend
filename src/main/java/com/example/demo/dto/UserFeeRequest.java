package com.example.demo.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UserFeeRequest(
        @NotNull(message = "El monto es obligatorio")
        @DecimalMin(value = "0.0", message = "El monto no puede ser negativo")
        BigDecimal monthlyFee
) {}