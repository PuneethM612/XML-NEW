package com.bnp.pb.lynx.service.impl;

import com.bnp.pb.lynx.dto.StudentMarksDto;
import com.bnp.pb.lynx.model.ExamType;
import com.bnp.pb.lynx.model.Marks;
import com.bnp.pb.lynx.model.Student;
import com.bnp.pb.lynx.repository.MarksRepository;
import com.bnp.pb.lynx.service.MarksService;

import java.util.List;

// Remove all annotations for XML-based configuration
public class MarksServiceImpl implements MarksService {
    
    private final MarksRepository marksRepository;
    
    // Constructor injection will be configured in XML
    public MarksServiceImpl(MarksRepository marksRepository) {
        this.marksRepository = marksRepository;
    }
    
    public List<Marks> getAllMarks() {
        return marksRepository.findAll();
    }
    
    public Marks getMarksById(Long id) {
        return marksRepository.findById(id).orElse(null);
    }
    
    public List<Marks> getMarksByStudentAndSubject(Long studentId, Long subjectId) {
        return marksRepository.findByStudentAndSubject(studentId, subjectId);
    }
    
    public List<Marks> getMarksByExamType(ExamType examType) {
        return marksRepository.findByExamType(examType);
    }
    
    public List<Marks> getMarksByStudentAndExamType(Long studentId, ExamType examType) {
        return marksRepository.findByStudentAndExamType(studentId, examType);
    }
    
    public List<Marks> getMarksBySubjectAndExamType(Long subjectId, ExamType examType) {
        return marksRepository.findBySubjectAndExamType(subjectId, examType);
    }
    
    public Marks saveMarks(Marks marks) {
        return marksRepository.save(marks);
    }
    
    public void deleteMarks(Long id) {
        marksRepository.deleteById(id);
    }
    
    // Top Rankers Functionality
    public List<Student> getTop3Students() {
        return marksRepository.findTop3Students();
    }
    
    public List<Student> getTop3StudentsByExamType(String examType) {
        return marksRepository.findTop3StudentsByExamType(examType);
    }
    
    public List<StudentMarksDto> getStudentTotalMarksByExamType(String examType) {
        return marksRepository.getStudentTotalMarksByExamType(examType);
    }
    
    public List<StudentMarksDto> getStudentTotalMarks() {
        return marksRepository.getStudentTotalMarks();
    }
} 