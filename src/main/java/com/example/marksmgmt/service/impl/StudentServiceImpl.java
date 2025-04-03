package com.example.marksmgmt.service.impl;

import com.example.marksmgmt.model.Student;
import com.example.marksmgmt.repository.StudentRepository;
import com.example.marksmgmt.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    
    private final StudentRepository studentRepository;
    
    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Student getStudentByRollNumber(String rollNumber) {
        return studentRepository.findByRollNumber(rollNumber);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Student> getStudentsByNameContaining(String name) {
        return studentRepository.findByNameContaining(name);
    }
    
    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
    
    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean isRollNumberExists(String rollNumber) {
        return studentRepository.findByRollNumber(rollNumber) != null;
    }
} 