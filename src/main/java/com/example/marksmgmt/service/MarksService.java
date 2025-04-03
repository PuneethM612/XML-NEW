package com.example.marksmgmt.service;

import com.example.marksmgmt.dto.StudentMarksDto;
import com.example.marksmgmt.model.ExamType;
import com.example.marksmgmt.model.Marks;
import com.example.marksmgmt.model.Student;

import java.util.List;

public interface MarksService {
    
    List<Marks> getAllMarks();
    
    Marks getMarksById(Long id);
    
    List<Marks> getMarksByStudentAndSubject(Long studentId, Long subjectId);
    
    List<Marks> getMarksByExamType(ExamType examType);
    
    List<Marks> getMarksByStudentAndExamType(Long studentId, ExamType examType);
    
    List<Marks> getMarksBySubjectAndExamType(Long subjectId, ExamType examType);
    
    Marks saveMarks(Marks marks);
    
    void deleteMarks(Long id);
    
    // Top Rankers Functionality
    List<Student> getTop3Students();
    
    List<Student> getTop3StudentsByExamType(String examType);
    
    List<StudentMarksDto> getStudentTotalMarksByExamType(String examType);
    
    List<StudentMarksDto> getStudentTotalMarks();
} 