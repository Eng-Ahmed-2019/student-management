package com.ahmed.student_management.model;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
//import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "students")
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //@Column
    private int age;
    private String name;
    private String email;

    public Student(){}

    public Student(Long id,int age,String name,String email){
        this.id = id;
        this.age = age;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}