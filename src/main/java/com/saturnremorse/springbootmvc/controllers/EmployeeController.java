package com.saturnremorse.springbootmvc.controllers;


import com.saturnremorse.springbootmvc.dto.EmployeeDto;
import com.saturnremorse.springbootmvc.exceptions.ResourseNotFoundException;
import com.saturnremorse.springbootmvc.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path="/employees")
public class EmployeeController {


    @Autowired
    EmployeeService employeeService;


    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Long employeeId){
        Optional<EmployeeDto> employeeDto = employeeService.getEmployeeById(employeeId);
        return employeeDto.map(employeeDto1 -> new ResponseEntity<>(employeeDto1, HttpStatus.OK))
                .orElseThrow(() -> new ResourseNotFoundException("Employee Not found for id "+employeeId));

    }

    @GetMapping(path = "/")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> employeeDtos =  employeeService.getAllEmployees();
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> addNewEmployee(@RequestBody @Valid EmployeeDto inputEmployee){
        EmployeeDto employeeDto =  employeeService.addEmployee(inputEmployee);
        return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
    }

    @DeleteMapping(path="/{employeeId}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Long employeeId){
         boolean val = employeeService.deleteEmployeeById(employeeId);
         if(val){
             return new ResponseEntity<>("Employee with id "+employeeId+" deleted",HttpStatus.OK );
         }
        return new ResponseEntity<>("Employee with id "+employeeId+" not found",HttpStatus.NOT_FOUND );

    }

    @PutMapping(path = "/{employeeId}")
    public EmployeeDto updateEmployeeById(@RequestBody @Valid EmployeeDto inputDto, @PathVariable Long employeeId){
        return employeeService.updateEmployeeById(inputDto, employeeId);
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDto> updatePartialEmployee(@RequestBody Map<String, Object> updates, @PathVariable Long employeeId){
        EmployeeDto employeeDto =  employeeService.updatePartialEmployee(updates,employeeId);
        if(employeeDto ==null){
            return new ResponseEntity<>(employeeDto, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }
}
