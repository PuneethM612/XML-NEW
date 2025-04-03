package com.example.marksmgmt.repository;

import com.example.marksmgmt.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    
    Student findByRollNumber(@Param("rollNumber") String rollNumber);
    
    List<Student> findByNameContaining(@Param("name") String name);
} 