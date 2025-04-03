package com.bnp.pb.lynx.service.impl;

import com.bnp.pb.lynx.model.Subject;
import com.bnp.pb.lynx.repository.SubjectRepository;
import com.bnp.pb.lynx.service.SubjectService;

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