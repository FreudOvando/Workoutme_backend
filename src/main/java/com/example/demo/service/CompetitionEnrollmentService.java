package com.example.demo.service;

import com.example.demo.dto.CompetitionEnrollmentRequest;
import com.example.demo.dto.CompetitionEnrollmentResponse;
import com.example.demo.exception.EnrollmentAlreadyExistsException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.CompetitionMapper;
import com.example.demo.model.CompetitionEnrollment;
import com.example.demo.model.User;
import com.example.demo.repository.CompetitionEnrollmentRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompetitionEnrollmentService {

    private final CompetitionEnrollmentRepository enrollmentRepository;
    private final CompetitionService competitionService;
    private final UserRepository userRepository;
    private final CompetitionMapper competitionMapper;

    @Transactional(readOnly = true)
    public List<CompetitionEnrollmentResponse> findAllByCompetition(Long competitionId) {
        return enrollmentRepository.findAllByCompetitionId(competitionId)
                .stream()
                .map(competitionMapper::toResponse)
                .toList();
    }

    // La categoría queda fija: no hay método "update", solo inscripción única
    @Transactional
    public CompetitionEnrollmentResponse enroll(Long competitionId, CompetitionEnrollmentRequest request) {
        if (enrollmentRepository.existsByCompetitionIdAndUserId(competitionId, request.userId())) {
            throw new EnrollmentAlreadyExistsException("Ya estás inscrito en esta competencia");
        }

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el usuario con id " + request.userId()));

        CompetitionEnrollment enrollment = CompetitionEnrollment.builder()
                .competition(competitionService.getCompetitionOrThrow(competitionId))
                .user(user)
                .category(request.category())
                .build();

        return competitionMapper.toResponse(enrollmentRepository.save(enrollment));
    }
}