package com.bnp.pb.lynx.service;

import com.bnp.pb.lynx.dto.StudentMarksDto;
import com.bnp.pb.lynx.model.ExamType;
import com.bnp.pb.lynx.model.Marks;
import com.bnp.pb.lynx.model.Student;

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