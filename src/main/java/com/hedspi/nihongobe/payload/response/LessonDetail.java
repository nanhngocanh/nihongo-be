package com.hedspi.nihongobe.payload.response;

import com.hedspi.nihongobe.payload.data.Grammar;
import com.hedspi.nihongobe.payload.data.Sentence;
import com.hedspi.nihongobe.payload.data.Vocabulary;

import java.util.List;

public class LessonDetail {
    private Integer id;
    private Integer no;
    private String title;
    private String description;
    private Integer duration;
    private String sample;

    private List<Vocabulary> vocabularies;
    private List<Grammar> grammars;
    private List<Sentence> sentences;

    public LessonDetail(Integer id, Integer no, String title, String description, Integer duration, String sample, List<Vocabulary> vocabularies, List<Grammar> grammars, List<Sentence> sentences) {
        this.id = id;
        this.no = no;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.sample = sample;
        this.vocabularies = vocabularies;
        this.grammars = grammars;
        this.sentences = sentences;
    }

    public LessonDetail() {
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

    public List<Vocabulary> getVocabularies() {
        return vocabularies;
    }

    public void setVocabularies(List<Vocabulary> vocabularies) {
        this.vocabularies = vocabularies;
    }

    public List<Grammar> getGrammars() {
        return grammars;
    }

    public void setGrammars(List<Grammar> grammars) {
        this.grammars = grammars;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public void setSentences(List<Sentence> sentences) {
        this.sentences = sentences;
    }
}
