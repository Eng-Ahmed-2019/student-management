package com.ahmed.student_management.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service){
        this.service = service;
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<Page<StudentResponseDTO>> getAllStudents(
        @PageableDefault(
            size = 10,
            sort = "id",
            direction = Sort.Direction.ASC
        ) Pageable pageable
    ){
        return ResponseEntity.ok(this.service.getAllStudents(pageable));
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

    @PreAuthorize("hasRole('ADMIN')")
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
    public ResponseEntity<Page<StudentResponseDTO>> searchStudents(
        @RequestParam String name,
        @PageableDefault(
                    size = 10,
                    sort = "id",
                    direction = Sort.Direction.ASC
            ) Pageable pageable
    ){
        return ResponseEntity.ok(this.service.searchByName(name,pageable));
    }
    /*
    @GetMapping("/searchStudents")
    public List<StudentResponseDTO> searchStudents(
            @RequestParam String name
    ) {
        return this.service.searchByName(name);
    }*/
}