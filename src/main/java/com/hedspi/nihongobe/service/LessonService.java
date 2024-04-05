package com.hedspi.nihongobe.service;

import com.hedspi.nihongobe.entity.Lesson;
import com.hedspi.nihongobe.entity.LessonTheory;
import com.hedspi.nihongobe.entity.Progress;
import com.hedspi.nihongobe.enums.Status;
import com.hedspi.nihongobe.enums.Type;
import com.hedspi.nihongobe.exception.LessonNotFoundException;
import com.hedspi.nihongobe.payload.data.Grammar;
import com.hedspi.nihongobe.payload.data.Sentence;
import com.hedspi.nihongobe.payload.data.Vocabulary;
import com.hedspi.nihongobe.payload.response.LessonDetail;
import com.hedspi.nihongobe.payload.response.LessonFlashcard;
import com.hedspi.nihongobe.payload.response.LessonResponse;
import com.hedspi.nihongobe.payload.response.Message;
import com.hedspi.nihongobe.repository.LessonRepository;
import com.hedspi.nihongobe.repository.LessonTheoryRepository;
import com.hedspi.nihongobe.repository.ProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final LessonTheoryRepository lessonTheoryRepository;
    private final ProgressRepository progressRepository;

    public List<LessonResponse> getActivatedLessonsByUserId(String userId){
        List<LessonResponse> lessons = progressRepository.getActivatedLessonsByUserId(userId);
        Progress lesson1Progress;
        if (!lessons.isEmpty() && (lessons.get(0).getStatus() == Status.LOCKED)) {
                lessons.get(0).setStatus(Status.STUDYING);
                lesson1Progress = progressRepository.findByUserIdAndLesson_No(userId, 1).orElseThrow();
                lesson1Progress.setStatus(Status.STUDYING);
                progressRepository.save(lesson1Progress);

        }
        return lessons;
    }

    public LessonDetail getLessonDetailByIdAndUserId(Integer lessonId, String userId){
        LessonDetail lessonDetail;
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(
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
                vocabularies.add(new Vocabulary(element.getTerm(),element.getDefinition()));
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
                progress.getDuration(),//thời gian người học cần để hoàn thành bài học
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
                progressTmp = new Progress(userId, lesson,lesson.getDuration(), Status.LOCKED);
                if (lesson.getNo().equals(1)) progressTmp.setStatus(Status.STUDYING);
                progressList.add(progressTmp);
            }
            progressRepository.saveAll(progressList);
            return new Message("Đã tạo bài học cho người dùng.");
        } else return new Message("Bài học của người dùng đã có sẵn.");
    }

//  for  admin
    public List<Lesson> getAllLessons(){
        return lessonRepository.findAll();
    }

    public List<Lesson> saveAllLessons(List<Lesson> lessons) {
        return lessonRepository.saveAll(lessons);
    }

}
