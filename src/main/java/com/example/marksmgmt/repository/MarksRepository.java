package com.example.marksmgmt.repository;

import com.example.marksmgmt.dto.StudentMarksDto;
import com.example.marksmgmt.model.ExamType;
import com.example.marksmgmt.model.Marks;
import com.example.marksmgmt.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// XML-based configuration for repository
public interface MarksRepository extends JpaRepository<Marks, Long> {
    
    // Named queries defined in repository-orm.xml
    List<Marks> findByStudentAndSubject(Long studentId, Long subjectId);
    
    List<Marks> findByExamType(ExamType examType);
    
    List<Marks> findByStudentAndExamType(Long studentId, ExamType examType);
    
    List<Marks> findBySubjectAndExamType(Long subjectId, ExamType examType);
    
    // Special Queries for Top Rankers
    List<Student> findTop3Students();
    
    List<Student> findTop3StudentsByExamType(String examType);
    
    List<StudentMarksDto> getStudentTotalMarksByExamType(String examType);
    
    List<StudentMarksDto> getStudentTotalMarks();
} 