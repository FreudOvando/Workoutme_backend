package com.example.demo.controller;

import com.example.demo.dto.CompetitionEnrollmentRequest;
import com.example.demo.dto.CompetitionEnrollmentResponse;
import com.example.demo.service.CompetitionEnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/competitions")
@RequiredArgsConstructor
public class CompetitionEnrollmentController {

    private final CompetitionEnrollmentService enrollmentService;

    @GetMapping("/{competitionId}/enrollments")
    public List<CompetitionEnrollmentResponse> findAllByCompetition(@PathVariable Long competitionId) {
        return enrollmentService.findAllByCompetition(competitionId);
    }

    @PostMapping("/{competitionId}/enroll")
    @PreAuthorize("hasRole('ADMIN') or #request.userId() == authentication.principal.id")
    public CompetitionEnrollmentResponse enroll(
            @PathVariable Long competitionId,
            @Valid @RequestBody CompetitionEnrollmentRequest request
    ) {
        return enrollmentService.enroll(competitionId, request);
    }
}