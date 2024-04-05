package com.hedspi.nihongobe.exception;

import com.hedspi.nihongobe.enums.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ApiResponseException extends RuntimeException {
  private final HttpStatus statusCode;
  private final Message errorMessage;
}
