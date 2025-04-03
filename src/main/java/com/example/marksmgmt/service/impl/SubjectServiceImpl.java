package com.example.marksmgmt.service.impl;

import com.example.marksmgmt.model.Subject;
import com.example.marksmgmt.repository.SubjectRepository;
import com.example.marksmgmt.service.SubjectService;

import java.util.List;

// Remove all annotations for XML-based configuration
public class SubjectServiceImpl implements SubjectService {
    
    private final SubjectRepository subjectRepository;
    
    // Constructor injection will be configured in XML
    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }
    
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }
    
    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id).orElse(null);
    }
    
    public Subject getSubjectByName(String name) {
        return subjectRepository.findByName(name);
    }
    
    public List<Subject> getSubjectsByNameContaining(String name) {
        return subjectRepository.findByNameContaining(name);
    }
    
    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }
    
    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }
    
    public boolean isSubjectNameExists(String name) {
        return subjectRepository.findByName(name) != null;
    }
} 