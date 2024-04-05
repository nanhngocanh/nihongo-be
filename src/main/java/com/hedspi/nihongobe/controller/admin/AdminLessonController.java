package com.hedspi.nihongobe.controller.admin;

import com.hedspi.nihongobe.entity.Lesson;
import com.hedspi.nihongobe.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin/lessons")
@RequiredArgsConstructor
@PreAuthorize("hasRole('admin')")
public class AdminLessonController {
    private final LessonService lessonService;

    @GetMapping()
    public ResponseEntity<List<Lesson>> getAllLessons(){
        return ResponseEntity.ok(lessonService.getAllLessons());
    }

    @PutMapping()
    public ResponseEntity<List<Lesson>> saveAllLesson(@RequestBody List<Lesson> lessons){
        return ResponseEntity.ok(lessonService.saveAllLessons(lessons));
    }

}
