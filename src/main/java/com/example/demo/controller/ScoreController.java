package com.example.demo.controller;

import com.example.demo.dto.ScoreRequest;
import com.example.demo.dto.ScoreResponse;
import com.example.demo.service.ScoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/scores")
@RequiredArgsConstructor
public class ScoreController {

    private final ScoreService scoreService;

    @PostMapping
    @PreAuthorize("hasRole(isAuthenticated()) or #request.userId() == authentication.principal.id")
    public ScoreResponse saveOrUpdate(@Valid @RequestBody ScoreRequest request) {
        return scoreService.saveOrUpdate(request);
    }

    @GetMapping("/user/{userId}/wod/{wodId}")
    @PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.id")
    public ScoreResponse findByUserAndWod(@PathVariable Long userId, @PathVariable Long wodId) {
        return scoreService.findByUserAndWod(userId, wodId);
    }

    @GetMapping("/wod/{wodId}")
    public List<ScoreResponse> findAllByWod(@PathVariable Long wodId) {
        return scoreService.findAllByWod(wodId);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.id")
    public List<ScoreResponse> findAllByUser(@PathVariable Long userId) {
        return scoreService.findAllByUser(userId);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        scoreService.delete(id);
        return ResponseEntity.noContent().build();
    }
}