package com.example.marksmgmt.controller;

import com.example.marksmgmt.model.Student;
import com.example.marksmgmt.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/students")
public class StudentController {
    
    private final StudentService studentService;
    
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    
    @GetMapping
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "student/list";
    }
    
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        return "student/form";
    }
    
    @PostMapping("/create")
    public String createStudent(@Valid @ModelAttribute("student") Student student,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "student/form";
        }
        
        if (studentService.isRollNumberExists(student.getRollNumber())) {
            result.rejectValue("rollNumber", "error.student", "Roll number already exists");
            return "student/form";
        }
        
        studentService.saveStudent(student);
        redirectAttributes.addFlashAttribute("message", "Student created successfully!");
        return "redirect:/students";
    }
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        return "student/form";
    }
    
    @PostMapping("/edit/{id}")
    public String updateStudent(@PathVariable Long id,
                              @Valid @ModelAttribute("student") Student student,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "student/form";
        }
        
        Student existingStudent = studentService.getStudentById(id);
        if (!existingStudent.getRollNumber().equals(student.getRollNumber()) &&
            studentService.isRollNumberExists(student.getRollNumber())) {
            result.rejectValue("rollNumber", "error.student", "Roll number already exists");
            return "student/form";
        }
        
        student.setId(id);
        studentService.saveStudent(student);
        redirectAttributes.addFlashAttribute("message", "Student updated successfully!");
        return "redirect:/students";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        studentService.deleteStudent(id);
        redirectAttributes.addFlashAttribute("message", "Student deleted successfully!");
        return "redirect:/students";
    }
    
    @GetMapping("/search")
    public String searchStudents(@RequestParam(required = false) String name, Model model) {
        if (name != null && !name.isEmpty()) {
            model.addAttribute("students", studentService.getStudentsByNameContaining(name));
        } else {
            model.addAttribute("students", studentService.getAllStudents());
        }
        model.addAttribute("searchTerm", name);
        return "student/list";
    }
} 