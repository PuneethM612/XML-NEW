package com.example.marksmgmt.service.impl;

import com.example.marksmgmt.dto.StudentMarksDto;
import com.example.marksmgmt.model.ExamType;
import com.example.marksmgmt.model.Marks;
import com.example.marksmgmt.model.Student;
import com.example.marksmgmt.repository.MarksRepository;
import com.example.marksmgmt.service.MarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MarksServiceImpl implements MarksService {
    
    private final MarksRepository marksRepository;
    
    @Autowired
    public MarksServiceImpl(MarksRepository marksRepository) {
        this.marksRepository = marksRepository;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Marks> getAllMarks() {
        return marksRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Marks getMarksById(Long id) {
        return marksRepository.findById(id).orElse(null);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Marks> getMarksByStudentAndSubject(Long studentId, Long subjectId) {
        return marksRepository.findByStudentAndSubject(studentId, subjectId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Marks> getMarksByExamType(ExamType examType) {
        return marksRepository.findByExamType(examType);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Marks> getMarksByStudentAndExamType(Long studentId, ExamType examType) {
        return marksRepository.findByStudentAndExamType(studentId, examType);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Marks> getMarksBySubjectAndExamType(Long subjectId, ExamType examType) {
        return marksRepository.findBySubjectAndExamType(subjectId, examType);
    }
    
    @Override
    public Marks saveMarks(Marks marks) {
        return marksRepository.save(marks);
    }
    
    @Override
    public void deleteMarks(Long id) {
        marksRepository.deleteById(id);
    }
    
    // Top Rankers Functionality
    @Override
    @Transactional(readOnly = true)
    public List<Student> getTop3Students() {
        return marksRepository.findTop3Students();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Student> getTop3StudentsByExamType(String examType) {
        return marksRepository.findTop3StudentsByExamType(examType);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<StudentMarksDto> getStudentTotalMarksByExamType(String examType) {
        return marksRepository.getStudentTotalMarksByExamType(examType);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<StudentMarksDto> getStudentTotalMarks() {
        return marksRepository.getStudentTotalMarks();
    }
} 