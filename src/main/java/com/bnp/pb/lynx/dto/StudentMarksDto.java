package com.bnp.pb.lynx.dto;

public class StudentMarksDto {
    
    private Long studentId;
    private String studentName;
    private String rollNumber;
    private Double totalMarks;
    
    public StudentMarksDto() {
    }
    
    public StudentMarksDto(Long studentId, String studentName, String rollNumber, Double totalMarks) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.rollNumber = rollNumber;
        this.totalMarks = totalMarks;
    }
    
    public Long getStudentId() {
        return studentId;
    }
    
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    
    public String getStudentName() {
        return studentName;
    }
    
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public String getRollNumber() {
        return rollNumber;
    }
    
    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }
    
    public Double getTotalMarks() {
        return totalMarks;
    }
    
    public void setTotalMarks(Double totalMarks) {
        this.totalMarks = totalMarks;
    }
    
    @Override
    public String toString() {
        return "StudentMarksDto{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", rollNumber='" + rollNumber + '\'' +
                ", totalMarks=" + totalMarks +
                '}';
    }
} 