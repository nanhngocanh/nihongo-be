package com.hedspi.nihongobe.exception;

import com.hedspi.nihongobe.enums.Message;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ApiResponseException{
    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, Message.USER_NOT_FOUND);
    }
}
