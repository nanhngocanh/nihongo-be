package com.hedspi.nihongobe.repository;

import com.hedspi.nihongobe.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
}