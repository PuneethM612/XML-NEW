package com.example.marksmgmt.controller;

import com.example.marksmgmt.dto.StudentMarksDto;
import com.example.marksmgmt.model.ExamType;
import com.example.marksmgmt.model.Marks;
import com.example.marksmgmt.model.Student;
import com.example.marksmgmt.model.Subject;
import com.example.marksmgmt.service.MarksService;
import com.example.marksmgmt.service.StudentService;
import com.example.marksmgmt.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/marks")
public class MarksController {
    
    private final MarksService marksService;
    private final StudentService studentService;
    private final SubjectService subjectService;
    
    @Autowired
    public MarksController(MarksService marksService, StudentService studentService, SubjectService subjectService) {
        this.marksService = marksService;
        this.studentService = studentService;
        this.subjectService = subjectService;
    }
    
    @GetMapping
    public String getAllMarks(Model model) {
        model.addAttribute("marksList", marksService.getAllMarks());
        return "marks/list";
    }
    
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("marks", new Marks());
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("subjects", subjectService.getAllSubjects());
        model.addAttribute("examTypes", ExamType.values());
        return "marks/form";
    }
    
    @PostMapping("/create")
    public String createMarks(@Valid @ModelAttribute("marks") Marks marks,
                             BindingResult result,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "marks/form";
        }
        
        marksService.saveMarks(marks);
        redirectAttributes.addFlashAttribute("message", "Marks created successfully!");
        return "redirect:/marks";
    }
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
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
    
    @PostMapping("/edit/{id}")
    public String updateMarks(@PathVariable Long id,
                             @Valid @ModelAttribute("marks") Marks marks,
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
    
    @GetMapping("/delete/{id}")
    public String deleteMarks(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        marksService.deleteMarks(id);
        redirectAttributes.addFlashAttribute("message", "Marks deleted successfully!");
        return "redirect:/marks";
    }
    
    // Top Rankers Functionality
    @GetMapping("/topRankers")
    public String showTopRankers(@RequestParam(required = false) String examType, Model model) {
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