package com.bnp.pb.lynx.controller;

import com.bnp.pb.lynx.model.Subject;
import com.bnp.pb.lynx.service.SubjectService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class SubjectController {
    
    private final SubjectService subjectService;
    
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }
    
    public String getAllSubjects(Model model) {
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "subject/list";
    }
    
    public String showCreateForm(Model model) {
        model.addAttribute("subject", new Subject());
        return "subject/form";
    }
    
    public String createSubject(Subject subject,
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
    
    public String showEditForm(Long id, Model model) {
        Subject subject = subjectService.getSubjectById(id);
        if (subject == null) {
            return "redirect:/subjects";
        }
        model.addAttribute("subject", subject);
        return "subject/form";
    }
    
    public String updateSubject(Long id,
                              Subject subject,
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
    
    public String deleteSubject(Long id, RedirectAttributes redirectAttributes) {
        subjectService.deleteSubject(id);
        redirectAttributes.addFlashAttribute("message", "Subject deleted successfully!");
        return "redirect:/subjects";
    }
    
    public String searchSubjects(String name, Model model) {
        if (name != null && !name.isEmpty()) {
            model.addAttribute("subjects", subjectService.getSubjectsByNameContaining(name));
        } else {
            model.addAttribute("subjects", subjectService.getAllSubjects());
        }
        model.addAttribute("searchTerm", name);
        return "subject/list";
    }
} 