package com.example.demo.controller;

import com.example.demo.dto.PaymentRequest;
import com.example.demo.dto.PaymentResponse;
import com.example.demo.model.Role;
import com.example.demo.security.CustomUserDetails;
import com.example.demo.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or #request.userId() == authentication.principal.id")
    public PaymentResponse create(
            @Valid @RequestBody PaymentRequest request,
            @AuthenticationPrincipal CustomUserDetails principal
    ) {
        boolean isAdmin = principal.getRole() == Role.ADMIN;
        return paymentService.create(request, isAdmin);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.id")
    public List<PaymentResponse> findAllByUser(@PathVariable Long userId) {
        return paymentService.findAllByUser(userId);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<PaymentResponse> findAll() {
        return paymentService.findAll();
    }
}