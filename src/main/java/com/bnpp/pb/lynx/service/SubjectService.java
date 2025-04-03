package com.bnpp.pb.lynx.service;

import com.bnpp.pb.lynx.model.Subject;

import java.util.List;

public interface SubjectService {
    
    List<Subject> getAllSubjects();
    
    Subject getSubjectById(Long id);
    
    Subject getSubjectByName(String name);
    
    List<Subject> getSubjectsByNameContaining(String name);
    
    Subject saveSubject(Subject subject);
    
    void deleteSubject(Long id);
    
    boolean isSubjectNameExists(String name);
} 