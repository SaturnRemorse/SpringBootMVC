package com.saturnremorse.springbootmvc.exceptions;

public class ResourseNotFoundException extends RuntimeException{

    public ResourseNotFoundException(String message) {
        super(message);
    }
}
