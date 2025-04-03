package com.example.marksmgmt.service;

import com.example.marksmgmt.model.Student;

import java.util.List;

public interface StudentService {
    
    List<Student> getAllStudents();
    
    Student getStudentById(Long id);
    
    Student getStudentByRollNumber(String rollNumber);
    
    List<Student> getStudentsByNameContaining(String name);
    
    Student saveStudent(Student student);
    
    void deleteStudent(Long id);
    
    boolean isRollNumberExists(String rollNumber);
} 