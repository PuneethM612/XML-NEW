package com.example.marksmgmt.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Marks {
    
    private Long id;
    private Student student;
    private Subject subject;
    private Double marks;
    private ExamType examType;
    
    public Marks() {
    }
    
    public Marks(Student student, Subject subject, Double marks, ExamType examType) {
        this.student = student;
        this.subject = subject;
        this.marks = marks;
        this.examType = examType;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Student getStudent() {
        return student;
    }
    
    public void setStudent(Student student) {
        this.student = student;
    }
    
    public Subject getSubject() {
        return subject;
    }
    
    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    
    public Double getMarks() {
        return marks;
    }
    
    public void setMarks(Double marks) {
        this.marks = marks;
    }
    
    public ExamType getExamType() {
        return examType;
    }
    
    public void setExamType(ExamType examType) {
        this.examType = examType;
    }
    
    @Override
    public String toString() {
        return "Marks{" +
                "id=" + id +
                ", student=" + student.getName() +
                ", subject=" + subject.getName() +
                ", marks=" + marks +
                ", examType=" + examType +
                '}';
    }
} 