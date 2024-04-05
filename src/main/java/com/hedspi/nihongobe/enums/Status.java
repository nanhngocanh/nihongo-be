package com.hedspi.nihongobe.enums;

public enum Status {
    LOCKED(0),
    STUDYING(1),
    STUDIED(2),
    TESTED(3);

    private final int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
