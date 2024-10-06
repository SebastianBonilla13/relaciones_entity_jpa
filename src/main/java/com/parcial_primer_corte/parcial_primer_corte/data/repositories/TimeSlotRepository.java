package com.parcial_primer_corte.parcial_primer_corte.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parcial_primer_corte.parcial_primer_corte.data.models.TimeSlot;

import java.util.List;
import java.util.Optional;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Integer> {

    Optional<TimeSlot> findByCourseId(Integer courseId);
}
