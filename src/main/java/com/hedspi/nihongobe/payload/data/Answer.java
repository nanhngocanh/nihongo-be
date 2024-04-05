package com.hedspi.nihongobe.payload.data;

public class Answer {
    private String content;
    private Boolean isTrue;
    private Boolean selected = false;

    public Answer() {
    }

    public Answer(String content, Boolean isTrue) {
        this.content = content;
        this.isTrue = isTrue;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getTrue() {
        return isTrue;
    }

    public void setTrue(Boolean aTrue) {
        isTrue = aTrue;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
