package com.example.marksmgmt.service.impl;

import com.example.marksmgmt.model.Student;
import com.example.marksmgmt.repository.StudentRepository;
import com.example.marksmgmt.service.StudentService;

import java.util.List;

// Remove all annotations for XML-based configuration
public class StudentServiceImpl implements StudentService {
    
    private final StudentRepository studentRepository;
    
    // Constructor injection will be configured in XML
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }
    
    public Student getStudentByRollNumber(String rollNumber) {
        return studentRepository.findByRollNumber(rollNumber);
    }
    
    public List<Student> getStudentsByNameContaining(String name) {
        return studentRepository.findByNameContaining(name);
    }
    
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
    
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
    
    public boolean isRollNumberExists(String rollNumber) {
        return studentRepository.findByRollNumber(rollNumber) != null;
    }
} 