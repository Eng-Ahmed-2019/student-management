package com.ahmed.student_management.exception;

public class StudentNotFoundException extends RuntimeException{

    public StudentNotFoundException(String m){
        super(m);
    }
}