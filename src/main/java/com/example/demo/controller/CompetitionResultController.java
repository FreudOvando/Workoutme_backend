package com.example.demo.controller;

import com.example.demo.dto.CompetitionResultRequest;
import com.example.demo.dto.CompetitionResultResponse;
import com.example.demo.service.CompetitionResultService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/competitions")
@RequiredArgsConstructor
public class CompetitionResultController {

    private final CompetitionResultService resultService;

    @GetMapping("/{competitionId}/results")
    public List<CompetitionResultResponse> findAllByCompetition(@PathVariable Long competitionId) {
        return resultService.findAllByCompetition(competitionId);
    }

    @PostMapping("/results")
    @PreAuthorize("hasRole('ADMIN')")
    public CompetitionResultResponse saveOrUpdate(@Valid @RequestBody CompetitionResultRequest request) {
        return resultService.saveOrUpdate(request);
    }
}

