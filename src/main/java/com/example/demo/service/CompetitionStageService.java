package com.example.demo.service;

import com.example.demo.dto.CompetitionStageRequest;
import com.example.demo.dto.CompetitionStageResponse;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.CompetitionMapper;
import com.example.demo.model.CompetitionStage;
import com.example.demo.repository.CompetitionStageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompetitionStageService {

    private final CompetitionStageRepository stageRepository;
    private final CompetitionService competitionService;
    private final CompetitionMapper competitionMapper;

    @Transactional(readOnly = true)
    public List<CompetitionStageResponse> findAllByCompetition(Long competitionId) {
        return stageRepository.findAllByCompetitionIdOrderByStageOrderAsc(competitionId)
                .stream()
                .map(competitionMapper::toResponse)
                .toList();
    }

    @Transactional
    public CompetitionStageResponse create(Long competitionId, CompetitionStageRequest request) {
        CompetitionStage stage = CompetitionStage.builder()
                .competition(competitionService.getCompetitionOrThrow(competitionId))
                .name(request.name().trim())
                .stageOrder(request.stageOrder())
                .build();
        return competitionMapper.toResponse(stageRepository.save(stage));
    }

    @Transactional
    public CompetitionStageResponse update(Long stageId, CompetitionStageRequest request) {
        CompetitionStage stage = getStageOrThrow(stageId);
        stage.setName(request.name().trim());
        stage.setStageOrder(request.stageOrder());
        return competitionMapper.toResponse(stageRepository.save(stage));
    }

    @Transactional
    public void delete(Long stageId) {
        CompetitionStage stage = getStageOrThrow(stageId);
        stageRepository.delete(stage);
    }

    private CompetitionStage getStageOrThrow(Long stageId) {
        return stageRepository.findById(stageId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el WOD de competencia con id " + stageId));
    }
}