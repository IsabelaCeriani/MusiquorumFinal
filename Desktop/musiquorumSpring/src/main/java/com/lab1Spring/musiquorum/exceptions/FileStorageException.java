package com.lab1Spring.musiquorum.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INSUFFICIENT_STORAGE)

public class FileStorageException extends RuntimeException {
    public FileStorageException(String s) {
    }
}
