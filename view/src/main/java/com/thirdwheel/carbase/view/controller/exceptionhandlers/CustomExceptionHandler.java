package com.thirdwheel.carbase.view.controller.exceptionhandlers;

import com.thirdwheel.carbase.dao.excetions.CarbaseEntityNotFoundException;
import com.thirdwheel.carbase.dao.excetions.CarbaseIllegalArgumentException;
import com.thirdwheel.carbase.view.model.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(CarbaseIllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleCarbaseIllegalArgumentException(CarbaseIllegalArgumentException e) {
        return new ResponseEntity<>(new ExceptionResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CarbaseEntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleCarbaseEntityNotFoundException(CarbaseEntityNotFoundException e) {
        return new ResponseEntity<>(new ExceptionResponse(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>(new ExceptionResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
