package com.example.demo.mapper;

import com.example.demo.dto.ScoreResponse;
import com.example.demo.model.Score;
import org.springframework.stereotype.Component;

@Component
public class ScoreMapper {

    public ScoreResponse toResponse(Score score) {
        return new ScoreResponse(
                score.getId(),
                score.getUser().getId(),
                score.getUser().getFirstName() + " " + score.getUser().getLastName(),
                score.getWod().getId(),
                score.getWod().getName() != null ? score.getWod().getName() : "WOD del día",
                score.isCompleted(),
                score.getResult(),
                score.getCreatedAt(),
                score.getUpdatedAt()
        );
    }
}