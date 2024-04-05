package com.hedspi.nihongobe.payload.data;

public class Grammar {
    private String term;
    private String using;
    private String example;

    public Grammar(String term, String using, String example) {
        this.term = term;
        this.using = using;
        this.example = example;
    }

    public Grammar() {
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getUsing() {
        return using;
    }

    public void setUsing(String using) {
        this.using = using;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}
