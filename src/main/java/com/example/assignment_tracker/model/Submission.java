package com.example.assignment_tracker.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String fileUrl; // URL to the submitted file (e.g., in S3 or local storage)
    private LocalDate submittedDate;
    
    @Enumerated(EnumType.STRING) // Stores the enum as a string ("PENDING", "SUBMITTED")
    private SubmissionStatus status;

    // Many Submissions can belong to One Student
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    @ToString.Exclude
    @JsonBackReference("student-submissions")
    private Student student;

    // Many Submissions can be for One Assignment
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id")
    @ToString.Exclude
    @JsonBackReference("assignment-submissions")
    private Assignment assignment;
}
