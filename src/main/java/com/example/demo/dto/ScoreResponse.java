package com.example.demo.dto;

import java.time.LocalDateTime;

public record ScoreResponse(
        Long id,
        Long userId,
        String userFullName,
        String photoUrl,
        Long wodId,
        String wodName,
        boolean completed,
        String result,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
