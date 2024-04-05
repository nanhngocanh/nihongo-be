package com.hedspi.nihongobe.exception;

import com.hedspi.nihongobe.enums.Message;
import org.springframework.http.HttpStatus;

public class LessonNotFoundException extends ApiResponseException{
    public LessonNotFoundException() {
        super(HttpStatus.NOT_FOUND, Message.LESSON_NOT_FOUND);
    }
}