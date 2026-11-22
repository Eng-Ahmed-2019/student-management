package com.ahmed.student_management.dto;

import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public class StudentRequestDTO {
    @NotNull
    @Min(value = 16, message = "Age must be at least 16")
    //@Max(value = 50, message = "Age must be lessthan or equal 50")
    private int age;

    @NotBlank(message = "name is required")
    @Size(min = 3, message = "Name must be at least 3 characters")
    private String name;

    @Email(message = "invalid format")
    @NotBlank(message = "email is required")
    private String email;

    public StudentRequestDTO(){}

    public StudentRequestDTO(int age,String name,String email){
        this.age = age;
        this.name = name;
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}