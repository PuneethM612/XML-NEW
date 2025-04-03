package com.example.marksmgmt.controller;

import com.example.marksmgmt.model.Subject;
import com.example.marksmgmt.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/subjects")
public class SubjectController {
    
    private final SubjectService subjectService;
    
    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }
    
    @GetMapping
    public String getAllSubjects(Model model) {
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "subject/list";
    }
    
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("subject", new Subject());
        return "subject/form";
    }
    
    @PostMapping("/create")
    public String createSubject(@Valid @ModelAttribute("subject") Subject subject,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "subject/form";
        }
        
        if (subjectService.isSubjectNameExists(subject.getName())) {
            result.rejectValue("name", "error.subject", "Subject name already exists");
            return "subject/form";
        }
        
        subjectService.saveSubject(subject);
        redirectAttributes.addFlashAttribute("message", "Subject created successfully!");
        return "redirect:/subjects";
    }
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Subject subject = subjectService.getSubjectById(id);
        if (subject == null) {
            return "redirect:/subjects";
        }
        model.addAttribute("subject", subject);
        return "subject/form";
    }
    
    @PostMapping("/edit/{id}")
    public String updateSubject(@PathVariable Long id,
                              @Valid @ModelAttribute("subject") Subject subject,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "subject/form";
        }
        
        Subject existingSubject = subjectService.getSubjectById(id);
        if (!existingSubject.getName().equals(subject.getName()) &&
            subjectService.isSubjectNameExists(subject.getName())) {
            result.rejectValue("name", "error.subject", "Subject name already exists");
            return "subject/form";
        }
        
        subject.setId(id);
        subjectService.saveSubject(subject);
        redirectAttributes.addFlashAttribute("message", "Subject updated successfully!");
        return "redirect:/subjects";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteSubject(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        subjectService.deleteSubject(id);
        redirectAttributes.addFlashAttribute("message", "Subject deleted successfully!");
        return "redirect:/subjects";
    }
    
    @GetMapping("/search")
    public String searchSubjects(@RequestParam(required = false) String name, Model model) {
        if (name != null && !name.isEmpty()) {
            model.addAttribute("subjects", subjectService.getSubjectsByNameContaining(name));
        } else {
            model.addAttribute("subjects", subjectService.getAllSubjects());
        }
        model.addAttribute("searchTerm", name);
        return "subject/list";
    }
} 