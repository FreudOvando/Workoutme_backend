package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CompetitionResponse(
        Long id,
        String name,
        LocalDate competitionDate,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}

