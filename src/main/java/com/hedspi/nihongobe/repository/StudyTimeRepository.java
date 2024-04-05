package com.hedspi.nihongobe.repository;

import com.hedspi.nihongobe.entity.StudyTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface StudyTimeRepository extends JpaRepository<StudyTime, Integer> {
    Optional<StudyTime> findByUserIdAndDate(String userId, LocalDate date);
}