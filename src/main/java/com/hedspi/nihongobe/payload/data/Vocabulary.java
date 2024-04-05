package com.hedspi.nihongobe.payload.data;

public class Vocabulary {
    private String term;
    private String definition;

    public Vocabulary(String term, String definition) {
        this.term = term;
        this.definition = definition;
    }

    public Vocabulary() {
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}

