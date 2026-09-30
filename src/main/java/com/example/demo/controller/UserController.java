package com.example.demo.controller;

import com.example.demo.dto.UpdatePhotoRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.security.CustomUserDetails;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PatchMapping("/me/photo")
    @PreAuthorize("isAuthenticated()")
    public UserResponse updateMyPhoto(
            @AuthenticationPrincipal CustomUserDetails principal,
            @Valid @RequestBody UpdatePhotoRequest request) {
        return userService.updatePhoto(principal.getId(), request.photoUrl());
    }
}