package com.example.assignment_tracker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String description;
    private LocalDate dueDate; // Uses Java's modern date type

    // Establishes the relationship: Many Assignments can belong to One Faculty
    @ManyToOne(fetch = FetchType.LAZY) // LAZY = don't load the Faculty object until we ask for it
    @JoinColumn(name = "faculty_id") // This creates the 'faculty_id' foreign key column
    private Faculty faculty;

    // One Assignment can have Many Submissions
    @OneToMany(mappedBy = "assignment")
    private List<Submission> submissions;
}
