package com.example.demo.repository;

import com.example.demo.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository


public interface ScoreRepository extends JpaRepository<Score, Long> {




    List<Score> findAllByWodId(Long wodId);

    List<Score> findAllByUserId(Long userId);

    Optional<Score> findByUserIdAndWodId(Long userId, Long wodId);


    @Modifying
    @Query("delete from Score s where s.wod.id = :wodId")
    Integer deleteAllByWodId(Long wodId);



}