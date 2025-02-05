package com.saturnremorse.springbootmvc.advices;

import com.saturnremorse.springbootmvc.exceptions.ResourseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourseNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(ResourseNotFoundException exception){
        ApiError error = new ApiError(HttpStatus.NOT_FOUND, exception.getMessage(),null);
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleInternalServerError(Exception exception){
        ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(),null);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleBadRequestError(MethodArgumentNotValidException exception){
        List<String> errors = exception.getBindingResult().getAllErrors().stream().map(error -> error.getDefaultMessage()).toList();
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST, "input validation errors",errors);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
