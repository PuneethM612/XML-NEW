package com.example.marksmgmt.repository;

import com.example.marksmgmt.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    
    Subject findByName(@Param("name") String name);
    
    List<Subject> findByNameContaining(@Param("name") String name);
} 