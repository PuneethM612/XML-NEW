package com.bnpp.pb.lynx.repository;

import com.bnpp.pb.lynx.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// XML-based configuration for repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    
    // Named queries defined in repository-orm.xml
    Subject findByName(String name);
    
    List<Subject> findByNameContaining(String name);
} 