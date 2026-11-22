package com.ahmed.student_management.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

//@Getter
//@AllArgsConstructor
public class StudentResponseDTO {
    @JsonProperty
    private Long id;

    @JsonProperty
    private int age;

    @JsonProperty
    private String name;

    @JsonProperty
    private String email;

    public StudentResponseDTO(){}

    public StudentResponseDTO(Long id, int age, String name, String email) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.email = email;
    }
}