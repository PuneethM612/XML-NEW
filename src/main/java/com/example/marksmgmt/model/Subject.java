package com.example.marksmgmt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class Subject {
    
    private Long id;
    private String name;
    private List<Marks> marksList = new ArrayList<>();
    
    public Subject() {
    }
    
    public Subject(String name) {
        this.name = name;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<Marks> getMarksList() {
        return marksList;
    }
    
    public void setMarksList(List<Marks> marksList) {
        this.marksList = marksList;
    }
    
    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
} 