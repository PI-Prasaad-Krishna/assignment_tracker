package com.example.assignment_tracker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

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
    private Student student;

    // Many Submissions can be for One Assignment
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;
}
