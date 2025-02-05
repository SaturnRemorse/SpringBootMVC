package com.saturnremorse.springbootmvc.services;

import com.saturnremorse.springbootmvc.dto.EmployeeDto;
import com.saturnremorse.springbootmvc.entities.EmployeeEntity;
import com.saturnremorse.springbootmvc.exceptions.ResourseNotFoundException;
import com.saturnremorse.springbootmvc.repositories.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    ModelMapper modelMapper;


    public Optional<EmployeeDto> getEmployeeById(Long empId){
        return employeeRepo.findById(empId).map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDto.class));
    }

    public List<EmployeeDto> getAllEmployees(){
        List<EmployeeEntity> employeeEntities = employeeRepo.findAll();
        return employeeEntities.stream().map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDto.class)).collect(Collectors.toList());
    }

    public EmployeeDto addEmployee(EmployeeDto employeeDto){
        EmployeeEntity savedEmployee = employeeRepo.save(modelMapper.map(employeeDto, EmployeeEntity.class));
        return modelMapper.map(savedEmployee, EmployeeDto.class);

    }

    public boolean deleteEmployeeById(Long employeeId){
        boolean isExists = employeeRepo.existsById(employeeId);
        if(!isExists){
            throw new ResourseNotFoundException("Employee with id "+employeeId+" does not exists");
        }
        employeeRepo.deleteById(employeeId);
        return true;

    }

    public EmployeeDto updateEmployeeById(EmployeeDto inputDto, Long employeeId) {
        boolean isExists = employeeRepo.existsById(employeeId);
        if(!isExists){
            throw new ResourseNotFoundException("Employee with id "+employeeId+" does not exists");
        }
        EmployeeEntity inputEntity = modelMapper.map(inputDto, EmployeeEntity.class);
        inputEntity.setEmployee_id(employeeId);
        return modelMapper.map(employeeRepo.save(inputEntity),EmployeeDto.class);

    }

    public EmployeeDto updatePartialEmployee(Map<String, Object> updates, Long employeeId) {
        boolean isExists = employeeRepo.existsById(employeeId);
        if(!isExists){
            throw new ResourseNotFoundException("Employee with id "+employeeId+" does not exists");
        }
        EmployeeEntity employeeEntity = employeeRepo.findById(employeeId).get();
        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated,employeeEntity,value);
        });
        return modelMapper.map(employeeRepo.save(employeeEntity),EmployeeDto.class);
    }
}
