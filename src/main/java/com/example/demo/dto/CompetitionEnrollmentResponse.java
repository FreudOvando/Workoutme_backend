package com.example.demo.dto;

import com.example.demo.model.CompetitionCategory;

import java.time.LocalDateTime;

public record CompetitionEnrollmentResponse(
        Long id,
        Long competitionId,
        Long userId,
        String userFullName,
        CompetitionCategory category,
        LocalDateTime enrolledAt
) {}