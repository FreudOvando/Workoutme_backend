package com.example.demo.config;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.email}")
    private String adminEmail;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Override
    @Transactional
    public void run(String... args) {
        String normalizedEmail = adminEmail.trim().toLowerCase();

        userRepository.findByEmail(normalizedEmail).ifPresentOrElse(
                admin -> {
                    if (admin.getRole() != Role.ADMIN) {
                        admin.setRole(Role.ADMIN);
                        userRepository.save(admin);
                        log.info("Rol de administrador restaurado para: {}", normalizedEmail);
                    }
                },
                () -> {
                    User admin = User.builder()
                            .firstName("Admin")
                            .lastName("WorkoutMe")
                            .birthDate(LocalDate.of(2000, 1, 1))
                            .email(normalizedEmail)
                            .passwordHash(passwordEncoder.encode(adminPassword))
                            .role(Role.ADMIN)
                            .build();
                    userRepository.save(admin);
                    log.info("Usuario administrador creado: {}", normalizedEmail);
                }
        );
    }
}