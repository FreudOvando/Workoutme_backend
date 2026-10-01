package com.example.demo.service;

import com.example.demo.dto.CompetitionRequest;
import com.example.demo.dto.CompetitionResponse;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.CompetitionMapper;
import com.example.demo.model.Competition;
import com.example.demo.repository.CompetitionEnrollmentRepository;
import com.example.demo.repository.CompetitionRepository;
import com.example.demo.repository.CompetitionResultRepository;
import com.example.demo.repository.CompetitionStageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompetitionService {


    private final CompetitionRepository competitionRepository;
    private final CompetitionMapper competitionMapper;
    private final CompetitionStageRepository stageRepository;
    private final CompetitionEnrollmentRepository enrollmentRepository;
    private final CompetitionResultRepository resultRepository;

    @Transactional(readOnly = true)
    public List<CompetitionResponse> findAll() {
        return competitionRepository.findAllByOrderByCompetitionDateDesc()
                .stream()
                .map(competitionMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public CompetitionResponse findById(Long id) {
        return competitionMapper.toResponse(getCompetitionOrThrow(id));
    }

    @Transactional
    public CompetitionResponse create(CompetitionRequest request) {
        Competition competition = Competition.builder()
                .name(request.name().trim())
                .competitionDate(request.competitionDate())
                .build();
        return competitionMapper.toResponse(competitionRepository.save(competition));
    }

    @Transactional
    public CompetitionResponse update(Long id, CompetitionRequest request) {
        Competition competition = getCompetitionOrThrow(id);
        competition.setName(request.name().trim());
        competition.setCompetitionDate(request.competitionDate());
        return competitionMapper.toResponse(competitionRepository.save(competition));
    }

    @Transactional
    public void delete(Long id) {
        Competition competition = getCompetitionOrThrow(id);
        resultRepository.deleteAllByStage_CompetitionId(id);
        enrollmentRepository.deleteAllByCompetitionId(id);
        stageRepository.deleteAllByCompetitionId(id);
        competitionRepository.delete(competition);
    }


    Competition getCompetitionOrThrow(Long id) {
        return competitionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la competencia con id " + id));
    }
}