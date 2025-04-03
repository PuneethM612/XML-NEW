package com.bnp.pb.lynx.controller;

import com.bnp.pb.lynx.model.Student;
import com.bnp.pb.lynx.service.StudentService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

public class StudentController {
    
    private final StudentService studentService;
    
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "student/list";
    }
    
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        return "student/form";
    }
    
    public String createStudent(Student student,
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
    
    public String showEditForm(Long id, Model model) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        return "student/form";
    }
    
    public String updateStudent(Long id,
                              Student student,
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
    
    public String deleteStudent(Long id, RedirectAttributes redirectAttributes) {
        studentService.deleteStudent(id);
        redirectAttributes.addFlashAttribute("message", "Student deleted successfully!");
        return "redirect:/students";
    }
    
    public String searchStudents(String name, Model model) {
        if (name != null && !name.isEmpty()) {
            model.addAttribute("students", studentService.getStudentsByNameContaining(name));
        } else {
            model.addAttribute("students", studentService.getAllStudents());
        }
        model.addAttribute("searchTerm", name);
        return "student/list";
    }
} 