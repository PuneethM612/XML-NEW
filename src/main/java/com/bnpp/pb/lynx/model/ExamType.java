package com.bnpp.pb.lynx.model;

public enum ExamType {
    MIDTERM("Midterm"),
    FINAL("Final"),
    ASSIGNMENT("Assignment"),
    PROJECT("Project"),
    QUIZ("Quiz");
    
    private final String displayName;
    
    ExamType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
} 