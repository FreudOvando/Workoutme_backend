package com.example.demo.repository;

import com.example.demo.model.CompetitionStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetitionStageRepository extends JpaRepository<CompetitionStage, Long> {
    List<CompetitionStage> findAllByCompetitionIdOrderByStageOrderAsc(Long competitionId);
}

