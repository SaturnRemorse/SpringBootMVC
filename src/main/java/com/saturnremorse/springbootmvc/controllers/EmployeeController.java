package com.saturnremorse.springbootmvc.controllers;


import com.saturnremorse.springbootmvc.dto.EmployeeDto;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path="/employees")
public class EmployeeController {

    @GetMapping("/{employeeId}")
    public EmployeeDto getEmployee(@PathVariable Long employeeId){
        return new EmployeeDto(employeeId,"rengoku", "rengoku@hashira.com",23, LocalDate.of(2022,12,13), true);
    }
    @GetMapping(path = "/")
    public String getAllEmployees(@RequestParam(required = false) Integer age,
                                  @RequestParam(required = false) String sortBy){
        return "Age is "+age+" and sort By is "+sortBy;
    }

    @PostMapping
    public EmployeeDto addNewEmployee(@RequestBody EmployeeDto employeeDto){
        return new EmployeeDto(employeeDto.getEmployee_id(), employeeDto.getName(),employeeDto.getEmail(),employeeDto.getAge(), employeeDto.getDateOfJoining(),employeeDto.getIsActive());
    }

    @DeleteMapping(path="/{employeeId}")
    public String deleteEmployeeById(@PathVariable Long employeeId){
        return "deleted employee "+ employeeId;
    }
}
