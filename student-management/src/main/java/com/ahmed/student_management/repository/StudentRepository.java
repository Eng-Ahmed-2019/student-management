package com.ahmed.student_management.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ahmed.student_management.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long>{
    
    List<Student>findByNameContainingIgnoreCase(String name);

    Page<Student>findByNameContainingIgnoreCase(String name, Pageable pageable);
}