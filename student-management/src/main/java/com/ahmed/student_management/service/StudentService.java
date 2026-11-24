package com.ahmed.student_management.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import com.ahmed.student_management.model.Student;
import com.ahmed.student_management.dto.StudentRequestDTO;
import com.ahmed.student_management.dto.StudentResponseDTO;
import com.ahmed.student_management.repository.StudentRepository;
import com.ahmed.student_management.exception.StudentNotFoundException;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository){
        this.repository = repository;
    }

    public StudentResponseDTO createStudent(StudentRequestDTO dto){
        Student student = new Student(
            null,
            dto.getAge(),
            dto.getName(),
            dto.getEmail()
        );

        Student s = repository.saveAndFlush(student);

        return toResponseDTO(s);
    }

    // public List<StudentResponseDTO> getAllStudents(){
    //     return repository.findAll().stream().map(this::tResponseDTO).toList();
    // }
    public Page<StudentResponseDTO> getAllStudents(Pageable pageable)
    {
        return repository.findAll(pageable).map(this::toResponseDTO);
    }

    public StudentResponseDTO getStudentById(Long id){
        Student s = repository.findById(id)
                                .orElseThrow(()->{
                                    throw new StudentNotFoundException("Student not found");
                                });

        return toResponseDTO(s);
    }

    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO dto){
        Student s = repository.findById(id)
                                .orElseThrow(()->{
                                    throw new StudentNotFoundException("Student not found");
                                });
        
        s.setAge(dto.getAge());
        s.setName(dto.getName());
        s.setEmail(dto.getEmail());

        Student saveStudent = repository.saveAndFlush(s);

        return toResponseDTO(saveStudent);
    }

    public String deleteStudent(Long id){
        Student s = repository.findById(id)
                                .orElseThrow(()->{
                                    throw new StudentNotFoundException("Student not found");
                                });

        repository.delete(s);

        return "Student deleted successfully";
    }

    // public List<StudentResponseDTO> searchByName(String name){
    //     return repository.findByNameContainingIgnoreCase(name)
    //                 .stream()
    //                 .map(this::toResponseDTO)
    //                 .toList();
    // }
    public Page<StudentResponseDTO> searchByName(String name, Pageable pageable)
    {

        return repository
                .findByNameContainingIgnoreCase(name, pageable)
                .map(this::toResponseDTO);
    }

    private StudentResponseDTO toResponseDTO(Student student){
        return new StudentResponseDTO(
            student.getId(),
            student.getAge(),
            student.getName(),
            student.getEmail()
        );
    }
}