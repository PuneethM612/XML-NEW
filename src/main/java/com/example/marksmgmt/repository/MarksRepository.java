package com.example.marksmgmt.repository;

import com.example.marksmgmt.dto.StudentMarksDto;
import com.example.marksmgmt.model.ExamType;
import com.example.marksmgmt.model.Marks;
import com.example.marksmgmt.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MarksRepository extends JpaRepository<Marks, Long> {
    
    List<Marks> findByStudentAndSubject(
            @Param("studentId") Long studentId, 
            @Param("subjectId") Long subjectId);
    
    List<Marks> findByExamType(@Param("examType") ExamType examType);
    
    List<Marks> findByStudentAndExamType(
            @Param("studentId") Long studentId, 
            @Param("examType") ExamType examType);
    
    List<Marks> findBySubjectAndExamType(
            @Param("subjectId") Long subjectId, 
            @Param("examType") ExamType examType);
    
    // Special Queries for Top Rankers
    List<Student> findTop3Students();
    
    List<Student> findTop3StudentsByExamType(@Param("examType") String examType);
    
    List<StudentMarksDto> getStudentTotalMarksByExamType(@Param("examType") String examType);
    
    List<StudentMarksDto> getStudentTotalMarks();
} 