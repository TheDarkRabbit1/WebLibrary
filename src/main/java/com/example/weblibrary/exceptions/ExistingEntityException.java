package com.example.weblibrary.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ExistingEntityException extends RuntimeException{
    public ExistingEntityException (String message){
        super(message);
    }
}
