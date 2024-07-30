package com.example.lab6validation.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
@Data
@AllArgsConstructor
public class Employee {
    @NotEmpty(message = "ID cannot be null")
    @Size(min=3, message = "ID length must be more than 2 characters")
    private String id;
    @NotEmpty(message ="Name cannot be null")
    @Size(min=5,message="Name length must be more than 4 characters")
    @Pattern(regexp="^[a-zA-Z]*$")
    private String name;
    @NotNull(message="Age cannot be null")
    @Positive(message="Age must be a number")
    @Min(value = 26,message ="Age must be more than 25")
    private int age;
    @Email
    @NotEmpty(message="Email cannot be null")
    private String email;
    @NotEmpty(message ="Phone number cannot be null")
    @Pattern(regexp = "(\\05|0)[0-9]{9}")
    private String phoneNumber;
   // @Phone(name="phone",required=false,maxSize=10)
    @NotEmpty(message= " Position cannot be null")
    @Pattern(regexp ="^(Supervisor|Coordinator)$")
    private String position;
    @AssertFalse
    private Boolean onLeave;
    @NotNull(message="Hire date cannot be null")
    @PastOrPresent()
    @JsonFormat(pattern="yyyy-MM-dd")
    //pattern for date
   // @Pattern(regexp = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$")
    private LocalDate hireDate;
    @NotNull(message = "Annual leave cannot be null")
    @Positive(message = "Annual leave must be positive number")
    private int annualLeave;


}
