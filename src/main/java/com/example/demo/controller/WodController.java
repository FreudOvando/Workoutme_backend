package com.example.demo.controller;

import com.example.demo.dto.WodRequest;
import com.example.demo.dto.WodResponse;
import com.example.demo.service.WodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/wods")
@RequiredArgsConstructor
public class WodController {

    private final WodService wodService;

    @GetMapping
    public List<WodResponse> findAll() {
        return wodService.findAll();
    }

    @GetMapping("/today")
    public WodResponse findToday() {
        return wodService.getTodayWod();
    }

    @GetMapping("/{id}")
    public WodResponse findById(@PathVariable Long id) {
        return wodService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<WodResponse> create(@Valid @RequestBody WodRequest request) {
        WodResponse created = wodService.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public WodResponse update(@PathVariable Long id, @Valid @RequestBody WodRequest request) {
        return wodService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        wodService.delete(id);
        return ResponseEntity.noContent().build();
    }
}