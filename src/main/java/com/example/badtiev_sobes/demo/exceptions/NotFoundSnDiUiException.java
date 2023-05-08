package com.example.badtiev_sobes.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundSnDiUiException extends RuntimeException{
    public NotFoundSnDiUiException(String message) {
        super(message);
    }
}
