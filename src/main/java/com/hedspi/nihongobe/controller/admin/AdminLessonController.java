package com.hedspi.nihongobe.controller.admin;

import com.hedspi.nihongobe.entity.Lesson;
import com.hedspi.nihongobe.payload.request.CreateLessonRequest;
import com.hedspi.nihongobe.payload.response.AdminLessonDetail;
import com.hedspi.nihongobe.payload.response.Message;
import com.hedspi.nihongobe.service.LessonService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin/lessons")
@RequiredArgsConstructor
public class AdminLessonController {

    private final LessonService lessonService;


    @GetMapping()
//    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<List<Lesson>> getAllLessons(){
        return ResponseEntity.ok(lessonService.getAllLessons());
    }

    @PutMapping()
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<List<Lesson>> saveAllLesson(@RequestBody List<Lesson> lessons){
        return ResponseEntity.ok(lessonService.saveAllLessons(lessons));
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<Lesson> createLesson(@RequestBody CreateLessonRequest createLessonRequest) {
        Lesson lesson = lessonService.createLesson(createLessonRequest);
        return ResponseEntity.ok(lesson);
    }
    @PostMapping("/{id}/saveVideo")
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<Lesson> saveLessonVideo(@RequestParam("video") MultipartFile video, @PathVariable("id")Integer lessonId) throws IOException {
        Lesson lesson = lessonService.saveLessonVideoUrl(video, lessonId);
        return ResponseEntity.ok(lesson);
    }

    @GetMapping(value = "/{id}/play", produces = MediaType.ALL_VALUE)
    @PreAuthorize("hasAnyAuthority('admin')")
    public void play(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        lessonService.play(id, response);
    }

    @PutMapping("/{id}/deleteVideo")
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<Lesson> deleteVideo(@PathVariable Integer id) throws IOException {
        Lesson lesson = lessonService.deleteVideo(id);
        return ResponseEntity.ok(lesson);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<Lesson> updateLesson(@PathVariable Integer id, @RequestBody CreateLessonRequest request){
        Lesson lesson =  lessonService.updateLesson(id,request);
        return ResponseEntity.ok(lesson);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<AdminLessonDetail> getLesson(@PathVariable Integer id){
        AdminLessonDetail detail = lessonService.getLessonDetailForAdmin(id);
        return ResponseEntity.ok(detail);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<Message> deleteLesson(@PathVariable Integer id){
        return ResponseEntity.ok(lessonService.deleteLesson(id));
    }



}
