package com.example.marksmgmt.util;

import com.example.marksmgmt.model.Subject;
import com.example.marksmgmt.service.SubjectService;

import java.util.Arrays;
import java.util.List;

// Remove all annotations for XML-based configuration
public class DataInitializer {
    
    private final SubjectService subjectService;
    
    // Constructor injection will be configured in XML
    public DataInitializer(SubjectService subjectService) {
        this.subjectService = subjectService;
    }
    
    public void initialize() {
        // Check if we already have subjects
        if (subjectService.getAllSubjects().isEmpty()) {
            List<String> defaultSubjects = Arrays.asList(
                "Mathematics",
                "Physics",
                "Chemistry",
                "Biology",
                "Computer Science",
                "English",
                "History",
                "Geography"
            );
            
            for (String subjectName : defaultSubjects) {
                if (!subjectService.isSubjectNameExists(subjectName)) {
                    Subject subject = new Subject(subjectName);
                    subjectService.saveSubject(subject);
                }
            }
        }
    }
} 