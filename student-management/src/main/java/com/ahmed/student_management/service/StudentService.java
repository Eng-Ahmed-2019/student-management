package com.ahmed.student_management.service;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import com.ahmed.student_management.model.Student;
import com.ahmed.student_management.dto.StudentRequestDTO;
import com.ahmed.student_management.dto.StudentResponseDTO;
import com.ahmed.student_management.exception.StudentNotFoundException;

@Service
public class StudentService {

    private Long nextId = 1L;
    private final List<Student> students = new ArrayList<>();

    public StudentResponseDTO createStudent(StudentRequestDTO dto){
        Student student = new Student(
            nextId++,
            dto.getAge(),
            dto.getName(),
            dto.getEmail()
        );

        students.add(student);

        return tResponseDTO(student);
    }

    public List<StudentResponseDTO> getAllStudents(){
        return students
                .stream()
                .map(this::tResponseDTO)
                .toList()
            ;
    }

    public StudentResponseDTO getStudentById(Long id){
        Student student = students
                                .stream()
                                .filter(s->s.getId().equals(id))
                                .findFirst()
                                .orElseThrow(() ->
                                    new StudentNotFoundException("Student not found")
                                );

        return tResponseDTO(student);
    }

    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO dto){
        Student student = students
                                .stream()
                                .filter(s->s.getId().equals(id))
                                .findFirst()
                                .orElseThrow(()->
                                new StudentNotFoundException("Student not found")
                            );
        
        student.setAge(dto.getAge());
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());

        return tResponseDTO(student);
    }

    public String deleteStudent(Long id){
        Student student = students
                                .stream()
                                .filter(s->s.getId().equals(id))
                                .findFirst()
                                .orElseThrow(()->
                                new StudentNotFoundException("Student not found")
                            );

        boolean result = students.remove(student);
        if (!result) {
            return "Faild process";
        }

        return "Student deleted successfully";
    }

    public List<StudentResponseDTO> searchByName(String name){
        return students
                        .stream()
                        .filter(s->s.getName().toLowerCase().contains(name.toLowerCase()))
                        .map(this::tResponseDTO)
                        .toList();
    }

    private StudentResponseDTO tResponseDTO(Student student){
        return new StudentResponseDTO(
            student.getId(),
            student.getAge(),
            student.getName(),
            student.getEmail()
        );
    }
}