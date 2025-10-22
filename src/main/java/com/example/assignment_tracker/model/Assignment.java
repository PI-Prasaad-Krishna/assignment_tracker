package com.example.assignment_tracker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
    @ManyToOne(fetch = FetchType.EAGER) // Changed to EAGER loading
    @JoinColumn(name = "faculty_id") // This creates the 'faculty_id' foreign key column
    @ToString.Exclude
    @JsonBackReference("faculty-assignments")
    private Faculty faculty;

    // One Assignment can have Many Submissions
    @OneToMany(mappedBy = "assignment")
    @ToString.Exclude
    @JsonManagedReference
    private List<Submission> submissions;
}
