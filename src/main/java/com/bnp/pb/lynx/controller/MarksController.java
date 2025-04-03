package com.bnp.pb.lynx.controller;

import com.bnp.pb.lynx.dto.StudentMarksDto;
import com.bnp.pb.lynx.model.ExamType;
import com.bnp.pb.lynx.model.Marks;
import com.bnp.pb.lynx.model.Student;
import com.bnp.pb.lynx.model.Subject;
import com.bnp.pb.lynx.service.MarksService;
import com.bnp.pb.lynx.service.StudentService;
import com.bnp.pb.lynx.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

public class MarksController {
    
    private final MarksService marksService;
    private final StudentService studentService;
    private final SubjectService subjectService;
    
    public MarksController(MarksService marksService, StudentService studentService, SubjectService subjectService) {
        this.marksService = marksService;
        this.studentService = studentService;
        this.subjectService = subjectService;
    }
    
    public String getAllMarks(Model model) {
        model.addAttribute("marksList", marksService.getAllMarks());
        return "marks/list";
    }
    
    public String showCreateForm(Model model) {
        model.addAttribute("marks", new Marks());
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("subjects", subjectService.getAllSubjects());
        model.addAttribute("examTypes", ExamType.values());
        return "marks/form";
    }
    
    public String createMarks(Marks marks,
                            BindingResult result,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "marks/form";
        }
        
        marksService.saveMarks(marks);
        redirectAttributes.addFlashAttribute("message", "Marks created successfully!");
        return "redirect:/marks";
    }
    
    public String showEditForm(Long id, Model model) {
        Marks marks = marksService.getMarksById(id);
        if (marks == null) {
            return "redirect:/marks";
        }
        model.addAttribute("marks", marks);
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("subjects", subjectService.getAllSubjects());
        model.addAttribute("examTypes", ExamType.values());
        return "marks/form";
    }
    
    public String updateMarks(Long id,
                            Marks marks,
                            BindingResult result,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "marks/form";
        }
        
        marks.setId(id);
        marksService.saveMarks(marks);
        redirectAttributes.addFlashAttribute("message", "Marks updated successfully!");
        return "redirect:/marks";
    }
    
    public String deleteMarks(Long id, RedirectAttributes redirectAttributes) {
        marksService.deleteMarks(id);
        redirectAttributes.addFlashAttribute("message", "Marks deleted successfully!");
        return "redirect:/marks";
    }
    
    // Top Rankers Functionality
    public String showTopRankers(String examType, Model model) {
        List<StudentMarksDto> topStudents;
        
        if (examType != null && !examType.isEmpty()) {
            topStudents = marksService.getStudentTotalMarksByExamType(examType);
            model.addAttribute("filteredByExam", examType);
        } else {
            topStudents = marksService.getStudentTotalMarks();
        }
        
        model.addAttribute("topStudents", topStudents);
        model.addAttribute("examTypes", ExamType.values());
        return "marks/topRankers";
    }
} 