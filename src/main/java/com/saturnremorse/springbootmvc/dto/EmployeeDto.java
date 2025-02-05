package com.saturnremorse.springbootmvc.dto;

import com.saturnremorse.springbootmvc.annotation.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeDto {

    private Long employee_id;

    @NotBlank(message = "name cannot be blank")
    @Size(min =3, max = 10, message = "length of name should be between 3 - 10")
    private String name;

    @Email(message = "email should be in correct format")
    private String email;

    @Max(value = 80, message = "Age cannot be less than 80")
    @Min(value = 18, message = "Age cannot be less than 18")
    private Integer age;

    //@Pattern(regexp = "^(ADMIN|USER)$" , message = "role of employee can be ADMIN or USER")
    @NotBlank(message = "role of employee cannot be blank")
    @EmployeeRoleValidation
    private String role;

    @NotNull
    @Positive(message = "salary cannot be negative")
    @Digits(integer = 6, fraction = 2, message = "salary should be in format xxxx.yy")
    @DecimalMin(value = "100.50")
    @DecimalMax(value = "10000.99")
    private Double salary;

    @PastOrPresent(message = "date of joining cannot be in future")
    private LocalDate dateOfJoining;
    private Boolean isActive;
}
