package com.example.demo.service;

import com.example.demo.dto.CompetitionResultRequest;
import com.example.demo.dto.CompetitionResultResponse;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.CompetitionMapper;
import com.example.demo.model.CompetitionEnrollment;
import com.example.demo.model.CompetitionResult;
import com.example.demo.model.CompetitionStage;
import com.example.demo.repository.CompetitionEnrollmentRepository;
import com.example.demo.repository.CompetitionResultRepository;
import com.example.demo.repository.CompetitionStageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompetitionResultService {

    private final CompetitionResultRepository resultRepository;
    private final CompetitionStageRepository stageRepository;
    private final CompetitionEnrollmentRepository enrollmentRepository;
    private final CompetitionMapper competitionMapper;

    @Transactional(readOnly = true)
    public List<CompetitionResultResponse> findAllByCompetition(Long competitionId) {
        return resultRepository.findAllByStage_CompetitionId(competitionId)
                .stream()
                .map(competitionMapper::toResponse)
                .toList();
    }

    // Upsert: si ya existe un resultado para ese stage+inscripción, lo actualiza
    @Transactional
    public CompetitionResultResponse saveOrUpdate(CompetitionResultRequest request) {
        CompetitionResult result = resultRepository
                .findByStageIdAndEnrollmentId(request.stageId(), request.enrollmentId())
                .orElseGet(() -> CompetitionResult.builder()
                        .stage(getStageOrThrow(request.stageId()))
                        .enrollment(getEnrollmentOrThrow(request.enrollmentId()))
                        .build());

        result.setPercentage(request.percentage());

        return competitionMapper.toResponse(resultRepository.save(result));
    }

    private CompetitionStage getStageOrThrow(Long stageId) {
        return stageRepository.findById(stageId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el WOD de competencia con id " + stageId));
    }

    private CompetitionEnrollment getEnrollmentOrThrow(Long enrollmentId) {
        return enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la inscripción con id " + enrollmentId));
    }
}