package com.example.demo.controller;

import com.example.demo.dto.CompetitionStageRequest;
import com.example.demo.dto.CompetitionStageResponse;
import com.example.demo.service.CompetitionStageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/competitions")
@RequiredArgsConstructor
public class CompetitionStageController {

    private final CompetitionStageService stageService;

    @GetMapping("/{competitionId}/stages")
    public List<CompetitionStageResponse> findAllByCompetition(@PathVariable Long competitionId) {
        return stageService.findAllByCompetition(competitionId);
    }

    @PostMapping("/{competitionId}/stages")
    @PreAuthorize("hasRole('ADMIN')")
    public CompetitionStageResponse create(
            @PathVariable Long competitionId,
            @Valid @RequestBody CompetitionStageRequest request
    ) {
        return stageService.create(competitionId, request);
    }

    @PutMapping("/stages/{stageId}")
    @PreAuthorize("hasRole('ADMIN')")
    public CompetitionStageResponse update(
            @PathVariable Long stageId,
            @Valid @RequestBody CompetitionStageRequest request
    ) {
        return stageService.update(stageId, request);
    }

    @DeleteMapping("/stages/{stageId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long stageId) {
        stageService.delete(stageId);
        return ResponseEntity.noContent().build();
    }
}