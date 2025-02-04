package com.saturnremorse.springbootmvc.controllers;


import com.saturnremorse.springbootmvc.dto.EmployeeDto;
import com.saturnremorse.springbootmvc.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/employees")
public class EmployeeController {


    @Autowired
    EmployeeService employeeService;


    @GetMapping("/{employeeId}")
    public EmployeeDto getEmployee(@PathVariable Long employeeId){
        return employeeService.getEmployeeById(employeeId);
    }
    @GetMapping(path = "/")
    public List<EmployeeDto> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeDto addNewEmployee(@RequestBody EmployeeDto inputEmployee){
        return employeeService.addEmployee(inputEmployee);
    }

    @DeleteMapping(path="/{employeeId}")
    public void deleteEmployeeById(@PathVariable Long employeeId){
        employeeService.deleteEmployeeById(employeeId);
    }
}
