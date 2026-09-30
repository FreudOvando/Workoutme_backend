package com.example.demo.repository;

import com.example.demo.model.Wod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.time.LocalDate;

import java.util.List;

@Repository
public interface WodRepository extends JpaRepository<Wod, Long> {

    // Más recientes primero; si hay dos el mismo día, el último creado va antes
    List<Wod> findAllByOrderByPublicationDateDescIdDesc();
    Optional<Wod> findFirstByPublicationDateOrderByIdDesc(LocalDate date);
}