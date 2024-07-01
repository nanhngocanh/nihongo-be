package com.hedspi.nihongobe.payload.data;

import com.hedspi.nihongobe.payload.response.LessonFlashcard;

public class Vocabulary extends LessonFlashcard {
    private String example;

    public Vocabulary(String term, String definition, String example) {
        super(term, definition);
        this.example = example;
    }

    public Vocabulary(String example) {
        this.example = example;
    }

    public Vocabulary() {
    }


    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}

