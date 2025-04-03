package com.example.marksmgmt.service.impl;

import com.example.marksmgmt.model.Subject;
import com.example.marksmgmt.repository.SubjectRepository;
import com.example.marksmgmt.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {
    
    private final SubjectRepository subjectRepository;
    
    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id).orElse(null);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Subject getSubjectByName(String name) {
        return subjectRepository.findByName(name);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Subject> getSubjectsByNameContaining(String name) {
        return subjectRepository.findByNameContaining(name);
    }
    
    @Override
    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }
    
    @Override
    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean isSubjectNameExists(String name) {
        return subjectRepository.findByName(name) != null;
    }
} 