package com.saturnremorse.springbootmvc.advices;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiError {

    private HttpStatus status;
    private String errorMessage;
    private List<String> suberrors;
}
