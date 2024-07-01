package com.hedspi.nihongobe.controller.user;

import com.hedspi.nihongobe.payload.response.Message;
import com.hedspi.nihongobe.service.LessonService;
import com.hedspi.nihongobe.utils.BasicInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("api/v1/user_progress")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('user')")
public class UserController {
    private final LessonService lessonService;

    @PostMapping()
    public ResponseEntity<Message> newUserProgress(Authentication authentication){
        String userId = BasicInfo.getUserIdByAuthentication(authentication);
        return ResponseEntity.ok(lessonService.createUserProgress(userId));
    }
}
