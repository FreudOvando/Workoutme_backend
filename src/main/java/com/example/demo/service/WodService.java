package com.example.demo.service;

import com.example.demo.dto.WodRequest;
import com.example.demo.dto.WodResponse;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.WodMapper;
import com.example.demo.model.Wod;
import com.example.demo.repository.ScoreRepository;
import com.example.demo.repository.WodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional

public class WodService {

    private final WodRepository wodRepository;
    private final WodMapper wodMapper;
    private final ScoreRepository scoreRepository;

    @Transactional(readOnly = true)
    public List<WodResponse> findAll() {
        return wodRepository.findAllByOrderByPublicationDateDescIdDesc()
                .stream()
                .map(wodMapper::toResponse)
                .toList();
    }


    @Transactional(readOnly = true)
    public WodResponse findById(Long id) {
        return wodMapper.toResponse(getWodOrThrow(id));
    }

    @Transactional
    public WodResponse create(WodRequest request) {
        Wod saved = wodRepository.save(wodMapper.toEntity(request));
        return wodMapper.toResponse(saved);
    }

    @Transactional
    public WodResponse update(Long id, WodRequest request) {
        Wod wod = getWodOrThrow(id);
        wodMapper.updateEntity(wod, request);
        return wodMapper.toResponse(wodRepository.save(wod));
    }

    public void delete(Long id) {
        Wod wod = getWodOrThrow(id);
        scoreRepository.deleteAllByWodId(id);
        wodRepository.delete(wod);
    }


    private Wod getWodOrThrow(Long id) {
        return wodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontró el WOD con id " + id));
    }

    @Transactional(readOnly = true)
    public WodResponse getTodayWod() {
        Wod wod = wodRepository.findFirstByPublicationDateOrderByIdDesc(LocalDate.now())
                .orElseThrow(() -> new ResourceNotFoundException("Todavía no hay un WOD publicado para hoy"));
        return wodMapper.toResponse(wod);
    }
}