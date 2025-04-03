package com.bnpp.pb.lynx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Remove all annotations for XML-based configuration
public class HomeController {
    
    // Methods will be mapped in XML configuration
    public String home() {
        return "home";
    }
    
    public String error() {
        return "error/general";
    }
} 