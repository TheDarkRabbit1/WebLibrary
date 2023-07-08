package com.example.weblibrary.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InsertionException extends RuntimeException{
    public InsertionException(String message){
        super(message);
    }
}