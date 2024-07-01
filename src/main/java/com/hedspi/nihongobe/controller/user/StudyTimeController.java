package com.hedspi.nihongobe.controller.user;

import com.hedspi.nihongobe.entity.Progress;
import com.hedspi.nihongobe.entity.StudyTime;
import com.hedspi.nihongobe.payload.request.StudyDuration;
import com.hedspi.nihongobe.payload.request.StudyLessonDuration;
import com.hedspi.nihongobe.payload.response.StudyTimePerDay;
import com.hedspi.nihongobe.service.StudyTimeService;
import com.hedspi.nihongobe.utils.BasicInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("api/v1/study_time")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('user')")
public class StudyTimeController {
    private final StudyTimeService service;

    @PostMapping()
    public ResponseEntity<StudyTime> saveStudyTime(@RequestBody StudyDuration duration, Authentication authentication){
        String userId = BasicInfo.getUserIdByAuthentication(authentication);
        StudyTime studyTime = service.saveStudyTime(userId,duration.getDuration());
        return ResponseEntity.ok(studyTime);
    }

    @GetMapping()
    public ResponseEntity<List<StudyTimePerDay>> getStudyTimeInDays(@RequestParam Integer numOfDays, Authentication authentication) {
        String userId = BasicInfo.getUserIdByAuthentication(authentication);
        List<StudyTimePerDay> list = service.getStudyTimeInDays(userId,numOfDays);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/lesson")
    public ResponseEntity<Progress> updateDuration(@RequestBody StudyLessonDuration studyLessonDuration, Authentication authentication) {
        String userId = BasicInfo.getUserIdByAuthentication(authentication);
        Progress progress = service.updateDuration(userId, studyLessonDuration);
        return ResponseEntity.ok(progress);
    }
}
