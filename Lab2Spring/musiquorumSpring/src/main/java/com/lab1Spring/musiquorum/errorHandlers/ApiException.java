package com.lab1Spring.musiquorum.errorHandlers;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiException {

    private final String message;
    private final HttpStatus httpStatus;
    private final int errorCode;
    private final ZonedDateTime timestamp;
//    private final Throwable throwable;

    public ApiException(String message, HttpStatus httpStatus, ZonedDateTime timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
        this.errorCode = httpStatus.value();
    }

    public String getMessage() {
        return message;
    }



    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
