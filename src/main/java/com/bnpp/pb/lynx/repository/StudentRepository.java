package com.bnpp.pb.lynx.repository;

import com.bnpp.pb.lynx.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// XML-based configuration for repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    // Named queries defined in repository-orm.xml
    Student findByRollNumber(String rollNumber);
    
    List<Student> findByNameContaining(String name);
} 