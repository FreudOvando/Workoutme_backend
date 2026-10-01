package com.example.demo.mapper;

import com.example.demo.dto.*;
import com.example.demo.model.Competition;
import com.example.demo.model.CompetitionEnrollment;
import com.example.demo.model.CompetitionResult;
import com.example.demo.model.CompetitionStage;
import org.springframework.stereotype.Component;

@Component
public class CompetitionMapper {

    public CompetitionResponse toResponse(Competition competition) {
        return new CompetitionResponse(
                competition.getId(),
                competition.getName(),
                competition.getCompetitionDate(),
                competition.getCreatedAt(),
                competition.getUpdatedAt()
        );
    }

    public CompetitionStageResponse toResponse(CompetitionStage stage) {
        return new CompetitionStageResponse(
                stage.getId(),
                stage.getCompetition().getId(),
                stage.getName(),
                stage.getStageOrder()
        );
    }

    public CompetitionEnrollmentResponse toResponse(CompetitionEnrollment enrollment) {
        return new CompetitionEnrollmentResponse(
                enrollment.getId(),
                enrollment.getCompetition().getId(),
                enrollment.getUser().getId(),
                enrollment.getUser().getFirstName() + " " + enrollment.getUser().getLastName(),
                enrollment.getCategory(),
                enrollment.getEnrolledAt()
        );
    }

    public CompetitionResultResponse toResponse(CompetitionResult result) {
        return new CompetitionResultResponse(
                result.getId(),
                result.getStage().getId(),
                result.getStage().getName(),
                result.getEnrollment().getId(),
                result.getEnrollment().getUser().getId(),
                result.getEnrollment().getUser().getFirstName() + " " + result.getEnrollment().getUser().getLastName(),
                result.getPercentage(),
                result.getCreatedAt(),
                result.getUpdatedAt()
        );
    }
}