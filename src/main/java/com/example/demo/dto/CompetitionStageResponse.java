package com.example.demo.dto;

public record CompetitionStageResponse(
        Long id,
        Long competitionId,
        String name,
        Integer stageOrder
) {}