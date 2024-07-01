package com.hedspi.nihongobe.service;

import com.hedspi.nihongobe.entity.Lesson;
import com.hedspi.nihongobe.entity.LessonTheory;
import com.hedspi.nihongobe.entity.Progress;
import com.hedspi.nihongobe.enums.Status;
import com.hedspi.nihongobe.enums.Type;
import com.hedspi.nihongobe.payload.data.Answer;
import com.hedspi.nihongobe.payload.response.TestQuestion;
import com.hedspi.nihongobe.repository.LessonRepository;
import com.hedspi.nihongobe.repository.LessonTheoryRepository;
import com.hedspi.nihongobe.repository.ProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TestService {
    private final LessonRepository lessonRepository;
    private final LessonTheoryRepository lessonTheoryRepository;
    private final ProgressRepository progressRepository;

    private final Random rand = new Random();

    private List<Answer> getRandomAnswer(List<String> list, Integer totalItems)
    {
        // create a temporary list for storing
        // selected element
        List<Answer> newList = new ArrayList<>();
        for (int i = 0; i < totalItems; i++) {

            // take a random index between 0 to size
            // of given List
            int randomIndex = rand.nextInt(list.size());

            // add element in temporary list
            newList.add(new Answer(list.get(randomIndex),false));

            // Remove selected element from original list
//            list.remove(list.get(randomIndex));
        }
        return newList;
    }

    private List<String> getIncorrectAnswerInLessonByType(LessonTheory correctAnswer) {
        return lessonTheoryRepository.getIncorrectAnswerInLessonByType(correctAnswer.getId(),correctAnswer.getType(),correctAnswer.getLesson().getId());
    }
    private List<String> getIncorrectAnswerInAnotherLessonByType(Lesson lesson, Type type) {
        return lessonTheoryRepository.getIncorrectAnswerInAnotherLessonByType(type,lesson.getId());
    }

    private List<Answer> shuffleArray(List<Answer> arr){
        Collections.shuffle(arr);
        return arr;
    }
    public TestQuestion createQuestion(LessonTheory lessonTheory, List<String> incorrectAnswerInAnotherLesson){
        String question = lessonTheory.getTerm();
        List<String> incorrectAnswerInLesson = getIncorrectAnswerInLessonByType(lessonTheory);
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer(lessonTheory.getDefinition(),true));
        answers.addAll(getRandomAnswer(incorrectAnswerInLesson,1));
        answers.addAll(getRandomAnswer(incorrectAnswerInAnotherLesson,2));
        return new TestQuestion(question, shuffleArray(answers));
    }
    public List<TestQuestion> createTestByLessonId(Integer lessonId){
        List<TestQuestion> questionList = new ArrayList<>();
        Lesson lesson = lessonRepository.findByIdAndIsActiveTrue(lessonId).orElseThrow();
        List<String> incorrectVocabInAnotherLesson = getIncorrectAnswerInAnotherLessonByType(lesson,Type.VOCABULARY);
        List<String> incorrectGrammarInAnotherLesson = getIncorrectAnswerInAnotherLessonByType(lesson,Type.GRAMMAR);
        List<String> incorrectSentenceInAnotherLesson = getIncorrectAnswerInAnotherLessonByType(lesson,Type.SENTENCE);
        List<LessonTheory> lessonTheories = lessonTheoryRepository.findByLesson(lesson);
        for (LessonTheory el : lessonTheories) {
            if (el.getType().equals(Type.VOCABULARY)){
                questionList.add(createQuestion(el,incorrectVocabInAnotherLesson));
            } else if (el.getType().equals(Type.GRAMMAR)){
                questionList.add(createQuestion(el,incorrectGrammarInAnotherLesson));
            } else if (el.getType().equals(Type.SENTENCE)){
                questionList.add(createQuestion(el,incorrectSentenceInAnotherLesson));
            }
        }
        return questionList;
    }

    public Progress doneTest(String userId, Integer lessonId){
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow();
        Progress progress = progressRepository.findByLessonAndUserId(lesson,userId).orElseThrow();
        progress.setStatus(Status.TESTED);
        progressRepository.save(progress);
        return progress;
    }
}
