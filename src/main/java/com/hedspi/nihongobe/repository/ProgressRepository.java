package com.hedspi.nihongobe.repository;

import com.hedspi.nihongobe.entity.Lesson;
import com.hedspi.nihongobe.entity.Progress;
import com.hedspi.nihongobe.payload.response.LessonResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProgressRepository extends JpaRepository<Progress, Integer> {
    Optional<Progress> findByUserIdAndLesson_No(String userId, Integer no);
    Optional<Progress> findByLessonAndUserId(Lesson lesson, String userId);
    @Query("select new com.hedspi.nihongobe.payload.response.LessonResponse(p.lesson.id, p.lesson.no, p.lesson.title, p.status) from Progress p where p.userId=:userId and p.lesson.isActive=true order by p.lesson.no asc ")
    List<LessonResponse> getActivatedLessonsByUserId(String userId);

    @Query("select distinct p.userId from Progress p")
    List<String> getStudyingUsers();
}