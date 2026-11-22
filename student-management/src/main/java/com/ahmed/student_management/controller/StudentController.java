package com.ahmed.student_management.controller;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import com.ahmed.student_management.dto.StudentRequestDTO;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.ahmed.student_management.dto.StudentResponseDTO;
import com.ahmed.student_management.service.StudentService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service){
        this.service = service;
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents(){
        return ResponseEntity.ok(this.service.getAllStudents());
    }
    /*
    @GetMapping("/getAllStudents")
    public List<StudentResponseDTO> getAllStudents() {
        return this.service.getAllStudents();
    }*/

    @GetMapping("/getById/{id}")
    public ResponseEntity<StudentResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.service.getStudentById(id));
    }
    /*
    @GetMapping("/getById/{id}")
    public StudentResponseDTO getStudentById(
            @PathVariable Long id
    ) {
        return this.service.getStudentById(id);
    }
    */

    @PostMapping("/createStudent")
    public ResponseEntity<StudentResponseDTO> createStudent
    (@Valid @RequestBody StudentRequestDTO dto){
        return ResponseEntity
        .status(201)
        .body(this.service.createStudent(dto));
    }
    /*
    @PostMapping("/createStudent")
    public StudentResponseDTO createStudent(
            @RequestBody StudentRequestDTO request
    ) {
        return this.service.createStudent(request);
    }*/

    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent
    (@PathVariable Long id, @Valid @RequestBody StudentRequestDTO dto){
        return ResponseEntity.ok(this.service.updateStudent(id, dto));
    }
    /*
    @PutMapping("/updateStudent/{id}")
    public StudentResponseDTO updateStudent(
            @PathVariable Long id,
            @RequestBody StudentRequestDTO request
    ) {
        return this.service.updateStudent(id, request);
    }*/

    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<String>deleteStudent(@PathVariable Long id){
        return ResponseEntity.ok(this.service.deleteStudent(id));
    }
    /*
    @DeleteMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable Long id) {
        return this.service.deleteStudent(id);
    }*/

    @GetMapping("/searchStudents")
    public ResponseEntity<List<StudentResponseDTO>> searchStudents(
        @RequestParam String name
    ){
        return ResponseEntity.ok(this.service.searchByName(name));
    }
    /*
    @GetMapping("/searchStudents")
    public List<StudentResponseDTO> searchStudents(
            @RequestParam String name
    ) {
        return this.service.searchByName(name);
    }*/
}