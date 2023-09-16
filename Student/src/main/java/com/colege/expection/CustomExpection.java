package com.colege.expection;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class CustomExpection extends Exception{
    private final HttpStatus status;

    public CustomExpection(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
