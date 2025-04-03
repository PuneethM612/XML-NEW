package com.bnpp.pb.lynx.repository;

import com.bnpp.pb.lynx.dto.StudentMarksDto;
import com.bnpp.pb.lynx.model.ExamType;
import com.bnpp.pb.lynx.model.Marks;
import com.bnpp.pb.lynx.model.Student;
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