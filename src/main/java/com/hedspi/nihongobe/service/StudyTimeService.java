package com.hedspi.nihongobe.service;

import com.hedspi.nihongobe.entity.Lesson;
import com.hedspi.nihongobe.entity.Progress;
import com.hedspi.nihongobe.entity.StudyTime;
import com.hedspi.nihongobe.enums.Status;
import com.hedspi.nihongobe.payload.request.StudyLessonDuration;
import com.hedspi.nihongobe.payload.response.LessonResponse;
import com.hedspi.nihongobe.payload.response.Message;
import com.hedspi.nihongobe.payload.response.StudyTimePerDay;
import com.hedspi.nihongobe.repository.LessonRepository;
import com.hedspi.nihongobe.repository.ProgressRepository;
import com.hedspi.nihongobe.repository.StudyTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyTimeService {
    private final StudyTimeRepository studyTimeRepository;
    private final ProgressRepository progressRepository;
    private final LessonRepository lessonRepository;

    public StudyTime saveStudyTime(String userId, Integer duration){
        LocalDate curDate = LocalDate.now();
        StudyTime newStudyTime = new StudyTime(userId,0, curDate);
        StudyTime studyTime = studyTimeRepository.findByUserIdAndDate(userId, curDate).orElse(newStudyTime);
        studyTime.setDuration(studyTime.getDuration() + duration);
        return studyTimeRepository.save(studyTime);
    }

    public List<StudyTimePerDay> getStudyTimeInDays(String userId, Integer numOfDays){
        LocalDate curDate = LocalDate.now();
        StudyTime studyTime;
        List<StudyTimePerDay> days = new ArrayList<>();
        for (int i = 0; i < numOfDays; i++) {
            studyTime = studyTimeRepository.findByUserIdAndDate(userId,curDate.minusDays(i)).orElse(new StudyTime(userId,0,curDate.minusDays(i)));
            days.add(new StudyTimePerDay(Math.round((float) studyTime.getDuration() /60),studyTime.getDate()));
        }
        return days;
    }

    public Progress updateDuration(String userId, StudyLessonDuration studyLessonDuration) {
        Lesson lesson = lessonRepository.findById(studyLessonDuration.getLessonId()).orElseThrow();
        Progress progress = progressRepository.findByLessonAndUserId(lesson, userId).orElseThrow();
        Progress nextLessonProgress;
        int remainingDuration = progress.getDuration() < studyLessonDuration.getDuration()
                ? 0 : progress.getDuration() - studyLessonDuration.getDuration();
        progress.setDuration(remainingDuration);
        if (remainingDuration == 0) {
            if (progress.getStatus().equals(Status.STUDYING)) progress.setStatus(Status.STUDIED);
            nextLessonProgress = progressRepository.findByUserIdAndLesson_No(userId, progress.getLesson().getNo()+1).orElse(progress);
            if (nextLessonProgress.getStatus().equals(Status.LOCKED)) {
                nextLessonProgress.setStatus(Status.STUDYING);
                progressRepository.save(nextLessonProgress);
            }
        }
        saveStudyTime(userId,studyLessonDuration.getDuration());
        return progressRepository.save(progress);
    }

}
