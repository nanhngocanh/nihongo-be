package com.hedspi.nihongobe.service;

import com.hedspi.nihongobe.entity.Lesson;
import com.hedspi.nihongobe.entity.LessonTheory;
import com.hedspi.nihongobe.entity.Progress;
import com.hedspi.nihongobe.enums.Status;
import com.hedspi.nihongobe.enums.Type;
import com.hedspi.nihongobe.exception.LessonNotFoundException;
import com.hedspi.nihongobe.payload.data.*;
import com.hedspi.nihongobe.payload.request.CreateLessonRequest;
import com.hedspi.nihongobe.payload.response.*;
import com.hedspi.nihongobe.repository.LessonRepository;
import com.hedspi.nihongobe.repository.LessonTheoryRepository;
import com.hedspi.nihongobe.repository.ProgressRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final LessonTheoryRepository lessonTheoryRepository;
    private final ProgressRepository progressRepository;

    @Value("${project.video}")
    private String path;

    public List<LessonResponse> getActivatedLessonsByUserId(String userId){
        List<LessonResponse> lessons = progressRepository.getActivatedLessonsByUserId(userId);
        Progress lesson1Progress;
        if (!lessons.isEmpty() && (lessons.get(0).getStatus() == Status.LOCKED)) {
                lessons.get(0).setStatus(Status.STUDYING);//bài đầu tiên k được lock
                lesson1Progress = progressRepository.findByUserIdAndLesson_No(userId, 1).orElseThrow();
                lesson1Progress.setStatus(Status.STUDYING);
                progressRepository.save(lesson1Progress);
        }
        return lessons;
    }

    public LessonDetail getActivatedLessonDetailByIdAndUserId(Integer lessonId, String userId){
        LessonDetail lessonDetail;
        Lesson lesson = lessonRepository.findByIdAndIsActiveTrue(lessonId).orElseThrow(
                LessonNotFoundException::new
        );
        Progress progress = progressRepository.findByLessonAndUserId(lesson,userId).orElseThrow(
                LessonNotFoundException::new
        );
        List<LessonTheory> lessonTheory = lessonTheoryRepository.findByLesson(lesson);
        List<Vocabulary> vocabularies = new ArrayList<>();
        List<Grammar> grammars = new ArrayList<>();
        List<Sentence> sentences = new ArrayList<>();

        for (LessonTheory element : lessonTheory) {
            if (element.getType() == Type.VOCABULARY) {
                vocabularies.add(new Vocabulary(element.getTerm(),element.getDefinition(),element.getDescription()));
            } else if (element.getType() == Type.GRAMMAR) {
                grammars.add(new Grammar(element.getTerm(),element.getDefinition(), element.getDescription()));
            } else if (element.getType() == Type.SENTENCE) {
                sentences.add(new Sentence(element.getTerm(), element.getDefinition()));
            }
        }

        lessonDetail = new LessonDetail(
                lesson.getId(),
                lesson.getNo(),
                lesson.getTitle(),
                lesson.getDescription(),
                lesson.getDuration() - progress.getDuration(),//thời gian người học cần để hoàn thành bài học
                lesson.getSample(),
                vocabularies,
                grammars,
                sentences
                );
        return lessonDetail;
    }

    public List<LessonFlashcard> getLessonFlashcardById(Integer lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(
                LessonNotFoundException::new
        );
        List<LessonTheory> lessonTheory = lessonTheoryRepository.findByLesson(lesson);
        List<LessonFlashcard> lessonFlashcards = new ArrayList<>();
        for (LessonTheory el : lessonTheory) {
            lessonFlashcards.add(new LessonFlashcard(el.getTerm(),el.getDefinition()));
        }
        return lessonFlashcards;
    }

    public Message createUserProgress(String userId){
        List<Lesson> lessons = lessonRepository.findAll();
        List<Progress> progressList = new ArrayList<>();
        Progress progressTmp;
        if (getActivatedLessonsByUserId(userId).isEmpty()) {
            for (Lesson lesson : lessons) {
                progressTmp = new Progress(userId, lesson,0, Status.LOCKED);
                if (lesson.getNo().equals(1)) progressTmp.setStatus(Status.STUDYING);
                progressList.add(progressTmp);
            }
            progressRepository.saveAll(progressList);
            return new Message("Đã tạo bài học cho người dùng.");
        } else return new Message("Bài học của người dùng đã có sẵn.");
    }


//  ============================================================================================
//  ---------------------------------- for admin -----------------------------------------------
    public List<Lesson> getAllLessons(){
        return lessonRepository.findAll(Sort.by(Sort.Direction.ASC, "no"));
    }

    public List<Lesson> saveAllLessons(List<Lesson> lessons) {
        return lessonRepository.saveAll(lessons);
    }

    public FileModel uploadVideo(String path, MultipartFile file) throws IOException {
        FileModel fileModel = new FileModel();
        //Fetch file original name .
        String fileName = file.getOriginalFilename();
        //try to generate random file name .
        String randomId = UUID.randomUUID().toString();
        assert fileName != null;
        String finalName = randomId.concat(fileName.substring(fileName.indexOf(".")));

        String filePath = path + File.separator + finalName ;

        File f = new File(path);
        if(!f.exists()) {
            f.mkdir();
        }

        Files.copy(file.getInputStream(), Paths.get(filePath));

        fileModel.setVideoFileName(finalName);

        return fileModel;
    }

    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path+File.separator+fileName ;
        return new FileInputStream(fullPath);
    }

    @Transactional
    public Lesson createLesson(CreateLessonRequest request){
        Lesson lesson = new Lesson(
                request.getNo(),
                request.getTitle(),
                request.getDescription(),
                request.getSample(),
                request.getDuration(),
                request.getActive()
                );
        Lesson newLesson = lessonRepository.save(lesson);
        List<LessonTheory> lessonTheories = new ArrayList<>();
        for (Theory theory : request.getTheories()) {
            lessonTheories.add(
                    new LessonTheory(
                            newLesson,
                            theory.getType(),
                            theory.getTerm(),
                            theory.getDefinition(),
                            theory.getDescription()
                    )
            );
        }
        lessonTheoryRepository.saveAll(lessonTheories);

        //thêm bài học cho all user
        List<String> users = progressRepository.getStudyingUsers();
        List<Progress> progressList = new ArrayList<>();
        Progress tmp;
        for (String user : users) {
            tmp = new Progress(user, newLesson,0, Status.LOCKED);
            progressList.add(tmp);
        }
        progressRepository.saveAll(progressList);
        return newLesson;
    }

    public Lesson saveLessonVideoUrl(MultipartFile video, Integer lessonId) throws IOException{
        FileModel fileModel = uploadVideo(path, video);
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow();
        lesson.setVideoUrl(fileModel.getVideoFileName());

        return lessonRepository.save(lesson);
    }

    public void play(Integer lessonId, HttpServletResponse response) throws IOException {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow();
        InputStream resource = getResource(path, lesson.getVideoUrl());
        response.setContentType(MediaType.ALL_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }

    public Lesson deleteVideo(Integer lessonId) throws IOException {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow();
        Path exactPath = Paths.get(path + File.separator + lesson.getVideoUrl());
        System.out.println(exactPath);
        try {
            Files.deleteIfExists(exactPath);

        } catch (Exception e1) {
            e1.getMessage();
            System.out.println(e1.getMessage()+"can not delete now ");
        }
        lesson.setVideoUrl(null);
        return this.lessonRepository.save(lesson);
    }

    @Transactional
    public Lesson updateLesson(Integer lessonId, CreateLessonRequest request) {
//        update lesson table
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow();
        lesson.setTitle(request.getTitle());
        lesson.setDescription(request.getDescription());
        lesson.setSample(request.getSample());
        lesson.setDuration(request.getDuration());
        Lesson updatedLesson = lessonRepository.save(lesson);

//        delete old theories
        List<LessonTheory> oldLessonTheories = lessonTheoryRepository.findByLesson(updatedLesson);
        lessonTheoryRepository.deleteAll(oldLessonTheories);

//        insert new theories
        LessonTheory lessonTheory;
        List<LessonTheory> newLessonTheories = new ArrayList<>();
        for (Theory el : request.getTheories()) {
            lessonTheory = new LessonTheory();
            lessonTheory.setType(el.getType());
            lessonTheory.setTerm(el.getTerm());
            lessonTheory.setDefinition(el.getDefinition());
            lessonTheory.setDescription(el.getDescription());
            lessonTheory.setLesson(updatedLesson);
            newLessonTheories.add(lessonTheory);
        }
        lessonTheoryRepository.saveAll(newLessonTheories);
        return updatedLesson;
    }

    public AdminLessonDetail getLessonDetailForAdmin(Integer lessonId){
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow();
        List<LessonTheory> lessonTheories = lessonTheoryRepository.findByLesson(lesson);
        AdminLessonDetail detail = new AdminLessonDetail();

        detail.setNo(lesson.getNo());
        detail.setTitle(lesson.getTitle());
        detail.setDescription(lesson.getDescription());
        detail.setSample(lesson.getSample());
        detail.setDuration(lesson.getDuration());
        detail.setActive(lesson.getActive());
        detail.setCreateAt(lesson.getCreateAt());
        detail.setUpdateAt(lesson.getUpdateAt());

        Theory theory;
        List<Theory> theories = new ArrayList<>();
        for (LessonTheory el : lessonTheories) {
            theory = new Theory();
            theory.setId(el.getId());
            theory.setType(el.getType());
            theory.setTerm(el.getTerm());
            theory.setDefinition(el.getDefinition());
            theory.setDescription(el.getDescription());

            theories.add(theory);
        }
        detail.setTheories(theories);
        return detail;
    }

    public Message deleteLesson(Integer lessonId){
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow();
        if (Boolean.FALSE.equals(lesson.getActive())) {
            lessonRepository.delete(lesson);
            return new Message("Đã xóa bài học!");
        } else return new Message("Bài học đang áp dụng sẽ không được xóa!");


    }

}
