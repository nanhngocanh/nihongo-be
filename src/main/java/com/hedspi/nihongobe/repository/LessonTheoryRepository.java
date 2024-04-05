package com.hedspi.nihongobe.repository;

import com.hedspi.nihongobe.entity.Lesson;
import com.hedspi.nihongobe.entity.LessonTheory;
import com.hedspi.nihongobe.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LessonTheoryRepository extends JpaRepository<LessonTheory, Integer> {
    List<LessonTheory> findByLesson(Lesson lesson);

    @Query("select lt.definition from LessonTheory lt where lt.id != :correctAnswerId and lt.lesson.id = :lessonId and lt.type = :type")
    List<String> getIncorrectAnswerInLessonByType(Integer correctAnswerId, Type type, Integer lessonId);

    @Query(value = "select definition from lesson_theory lt where lt.lesson_id != :lessonId and lt.type = :type limit 300",nativeQuery = true)
    List<String> getIncorrectAnswerInAnotherLessonByType(Type type, Integer lessonId);
}