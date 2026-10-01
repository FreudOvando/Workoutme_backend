package com.example.demo.repository;

import com.example.demo.model.CompetitionEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompetitionEnrollmentRepository extends JpaRepository<CompetitionEnrollment, Long> {
    List<CompetitionEnrollment> findAllByCompetitionId(Long competitionId);
    Optional<CompetitionEnrollment> findByCompetitionIdAndUserId(Long competitionId, Long userId);
    boolean existsByCompetitionIdAndUserId(Long competitionId, Long userId);
}

