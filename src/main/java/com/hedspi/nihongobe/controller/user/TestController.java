package com.hedspi.nihongobe.controller.user;

import com.hedspi.nihongobe.entity.Progress;
import com.hedspi.nihongobe.payload.response.TestQuestion;
import com.hedspi.nihongobe.service.TestService;
import com.hedspi.nihongobe.utils.BasicInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/test")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;
    @GetMapping("/{lesson_id}")
    public ResponseEntity<List<TestQuestion>> getTestOfLesson(@PathVariable("lesson_id") Integer lessonId) {
        List<TestQuestion> questionList = testService.createTestByLessonId(lessonId);
        return ResponseEntity.ok(questionList);
    }
    @PutMapping("/{lesson_id}/done")
    public ResponseEntity<Progress> doneTest(@PathVariable("lesson_id")Integer lessonId, Authentication authentication) {
        String userId = BasicInfo.getUserIdByAuthentication(authentication);
        Progress progress = testService.doneTest(userId, lessonId);
        return ResponseEntity.ok(progress);
    }
}
