package com.colege.validation;

import com.colege.exception.CustomException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ObjectValidator {
    private final Validator validator;
    @SneakyThrows
    public <T> T validate(T object){
        Set<ConstraintViolation<T>> error = validator.validate(object);
        if(error.isEmpty()){
            return object;
        }else{
            String message = error.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", "));
            throw new CustomException(message, HttpStatus.BAD_REQUEST);
        }

    }
}
