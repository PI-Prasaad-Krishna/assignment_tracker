package com.example.assignment_tracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.assignment_tracker.model.Submission;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    
    // Finds all submissions for a specific assignment
    List<Submission> findByAssignmentId(Long assignmentId);

    // Finds all submissions from a specific student
    List<Submission> findByStudentId(Long studentId);
}
