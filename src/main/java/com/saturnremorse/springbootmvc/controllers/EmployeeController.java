package com.saturnremorse.springbootmvc.controllers;


import com.saturnremorse.springbootmvc.dto.EmployeeDto;
import com.saturnremorse.springbootmvc.entities.EmployeeEntity;
import com.saturnremorse.springbootmvc.repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="/employees")
public class EmployeeController {


    @Autowired
    EmployeeRepo employeeRepo;


    @GetMapping("/{employeeId}")
    public EmployeeEntity getEmployee(@PathVariable Long employeeId){
        return employeeRepo.findById(employeeId).orElse(null);
    }
    @GetMapping(path = "/")
    public List<EmployeeEntity> getAllEmployees(){
        return employeeRepo.findAll();
    }

    @PostMapping
    public EmployeeEntity addNewEmployee(@RequestBody EmployeeEntity inputEmployee){
        return employeeRepo.save(inputEmployee);
    }

    @DeleteMapping(path="/{employeeId}")
    public void deleteEmployeeById(@PathVariable Long employeeId){
        employeeRepo.deleteById(employeeId);
    }
}
