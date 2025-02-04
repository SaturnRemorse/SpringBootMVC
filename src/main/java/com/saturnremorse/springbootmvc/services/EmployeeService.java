package com.saturnremorse.springbootmvc.services;

import com.saturnremorse.springbootmvc.dto.EmployeeDto;
import com.saturnremorse.springbootmvc.entities.EmployeeEntity;
import com.saturnremorse.springbootmvc.repositories.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    ModelMapper modelMapper;


    public EmployeeDto getEmployeeById(Long empId){
        EmployeeEntity employee = employeeRepo.findById(empId).orElse(null);
        return modelMapper.map(employee,EmployeeDto.class);

    }

    public List<EmployeeDto> getAllEmployees(){
        List<EmployeeEntity> employeeEntities = employeeRepo.findAll();
        return employeeEntities.stream().map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDto.class)).collect(Collectors.toList());
    }

    public EmployeeDto addEmployee(EmployeeDto employeeDto){
        EmployeeEntity savedEmployee = employeeRepo.save(modelMapper.map(employeeDto, EmployeeEntity.class));
        return modelMapper.map(savedEmployee, EmployeeDto.class);

    }

    public void deleteEmployeeById(Long id){
        employeeRepo.deleteById(id);

    }
}
