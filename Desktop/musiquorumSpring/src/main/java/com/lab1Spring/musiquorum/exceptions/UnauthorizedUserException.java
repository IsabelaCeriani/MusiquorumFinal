package com.lab1Spring.musiquorum.exceptions;

public class UnauthorizedUserException extends RuntimeException{

    public UnauthorizedUserException(String message) {
        super(message);
    }

}
