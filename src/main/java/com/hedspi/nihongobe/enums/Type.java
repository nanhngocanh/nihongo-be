package com.hedspi.nihongobe.enums;

public enum Type {
    VOCABULARY(0),
    GRAMMAR(1),
    SENTENCE(2);

    private final int value;

    Type(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
