package com.example.demo.dto;

import java.time.LocalDateTime;

public record CompetitionResultResponse(
        Long id,
        Long stageId,
        String stageName,
        Long enrollmentId,
        Long userId,
        String userFullName,
        Integer percentage,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}