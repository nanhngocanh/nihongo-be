package com.hedspi.nihongobe.controller.user;

import com.hedspi.nihongobe.payload.response.LessonDetail;
import com.hedspi.nihongobe.payload.response.LessonFlashcard;
import com.hedspi.nihongobe.payload.response.LessonResponse;
import com.hedspi.nihongobe.service.LessonService;
import com.hedspi.nihongobe.utils.BasicInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/lessons")
@RequiredArgsConstructor
@PreAuthorize("hasRole('user')")
public class LessonController {
    private final LessonService lessonService;

    @GetMapping("")
    public ResponseEntity<List<LessonResponse>> getAllLessonsOfUser(Authentication authentication){
        String userId = BasicInfo.getUserIdByAuthentication(authentication);
        List<LessonResponse> lessons = lessonService.getActivatedLessonsByUserId(userId);
        return ResponseEntity.ok().body(lessons);
    }

    @GetMapping("/{lesson_id}")
    public ResponseEntity<LessonDetail> getLessonDetailByIdAndUserId(@PathVariable("lesson_id")Integer lessonId, Authentication authentication) {
        String userId = BasicInfo.getUserIdByAuthentication(authentication);
        LessonDetail lessonDetail = lessonService.getLessonDetailByIdAndUserId(lessonId, userId);
        return ResponseEntity.ok().body(lessonDetail);
    }

    @GetMapping("/{lesson_id}/flashcard")
    public ResponseEntity<List<LessonFlashcard>> getLessonFlashcardById(@PathVariable("lesson_id")Integer lessonId) {
        List<LessonFlashcard> flashcards = lessonService.getLessonFlashcardById(lessonId);
        return ResponseEntity.ok(flashcards);
    }
}
