package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record PaymentResponse(
        Long id,
        Long userId,
        String userFullName,
        BigDecimal amount,
        LocalDate paymentDate,
        LocalDate nextDueDate,
        LocalDateTime createdAt
) {}