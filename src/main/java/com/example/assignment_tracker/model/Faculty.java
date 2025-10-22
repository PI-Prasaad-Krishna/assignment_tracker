package com.example.assignment_tracker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;
    private String department;

    // One Faculty can create Many Assignments
    @OneToMany(mappedBy = "faculty")
    private List<Assignment> assignments;

    // One Faculty can manage Many Projects
    @OneToMany(mappedBy = "faculty")
    private List<Project> projects;
}
