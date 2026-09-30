package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "wods",
        indexes = @Index(name = "idx_wods_publication_date", columnList = "publication_date")
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Opcional: "Helen", "Fran", o null
    @Column(length = 100)
    private String name;

    @Column(name = "publication_date", nullable = false)
    private LocalDate publicationDate;

    // Por ahora es texto; en fases futuras será una relación con User
    @Column(name = "coach_name", nullable = false, length = 100)
    private String coachName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private WodType type;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
