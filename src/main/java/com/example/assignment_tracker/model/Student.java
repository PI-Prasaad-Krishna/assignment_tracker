package com.example.assignment_tracker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity // Tells JPA this is a database table
@Data   // From Lombok: creates getters, setters, toString, etc.
@NoArgsConstructor // From Lombok: creates a default no-argument constructor
public class Student {
    @Id // Marks this as the Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increments the ID
    private Long id;
    
    private String name;
    private String email;
    private String department;

    // Establishes the relationship: One Student can have Many Submissions
    @OneToMany(mappedBy = "student") 
    private List<Submission> submissions;
}
