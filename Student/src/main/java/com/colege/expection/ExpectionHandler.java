package com.colege.expection;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.UnknownHostException;
@Slf4j
@RestControllerAdvice
public class ExpectionHandler {
    @ExceptionHandler(CustomExpection.class)
    public ResponseEntity<ExpectionResponse> handleBusinessRuleExpection(HttpServletRequest request, CustomExpection ex){
        ExpectionResponse response =
                new ExpectionResponse(request.getRequestURI(),ex.getStatus(),ex.getMessage());
        return new ResponseEntity<>(response, ex.getStatus());
    }
    @ExceptionHandler(UnknownHostException.class)
    public ResponseEntity<ExpectionResponse> handleUnknownHostExpection(HttpServletRequest request, UnknownHostException ex){
        ExpectionResponse response =
                new ExpectionResponse(request.getRequestURI(),HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.PARTIAL_CONTENT);
    }
}
