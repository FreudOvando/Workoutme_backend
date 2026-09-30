package com.example.demo.dto;

import com.example.demo.model.WodType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record WodResponse(
        Long id,
        String name,
        LocalDate publicationDate,
        String coachName,
        WodType type,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}