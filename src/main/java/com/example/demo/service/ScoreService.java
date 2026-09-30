package com.example.demo.service;

import com.example.demo.dto.ScoreRequest;
import com.example.demo.dto.ScoreResponse;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.ScoreMapper;
import com.example.demo.model.Score;
import com.example.demo.model.User;
import com.example.demo.model.Wod;
import com.example.demo.repository.ScoreRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.WodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreService {

    private final ScoreRepository scoreRepository;
    private final UserRepository userRepository;
    private final WodRepository wodRepository;
    private final ScoreMapper scoreMapper;

    // Crea el puntaje si no existe; si el usuario ya había registrado uno
    // para ese WOD, lo actualiza en vez de duplicarlo.
    @Transactional
    public ScoreResponse saveOrUpdate(ScoreRequest request) {
        Score score = scoreRepository.findByUserIdAndWodId(request.userId(), request.wodId())
                .orElseGet(() -> Score.builder()
                        .user(getUserOrThrow(request.userId()))
                        .wod(getWodOrThrow(request.wodId()))
                        .build());

        score.setCompleted(request.completed());
        score.setResult(request.result());

        return scoreMapper.toResponse(scoreRepository.save(score));
    }

    @Transactional(readOnly = true)
    public ScoreResponse findByUserAndWod(Long userId, Long wodId) {
        return scoreRepository.findByUserIdAndWodId(userId, wodId)
                .map(scoreMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Este usuario todavía no registró un puntaje para ese WOD"));
    }

    @Transactional(readOnly = true)
    public List<ScoreResponse> findAllByWod(Long wodId) {
        return scoreRepository.findAllByWodId(wodId)
                .stream()
                .map(scoreMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ScoreResponse> findAllByUser(Long userId) {
        return scoreRepository.findAllByUserId(userId)
                .stream()
                .map(scoreMapper::toResponse)
                .toList();
    }

    @Transactional
    public void delete(Long id) {
        if (!scoreRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se encontró el puntaje con id " + id);
        }
        scoreRepository.deleteById(id);
    }

    private User getUserOrThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el usuario con id " + userId));
    }

    private Wod getWodOrThrow(Long wodId) {
        return wodRepository.findById(wodId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el WOD con id " + wodId));
    }
}