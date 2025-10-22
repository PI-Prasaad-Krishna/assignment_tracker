package com.example.assignment_tracker.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignment_tracker.model.Submission;
import com.example.assignment_tracker.service.SubmissionService;

/**
 * REST Controller for managing Submissions.
 * Exposes endpoints at /api/submissions
 */
@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    /**
     * POST /api/submissions
     * Creates a new submission.
     * Request body:
     * {
     * "assignmentId": 1,
     * "studentId": 1,
     * "fileUrl": "http://example.com/submission/file.pdf"
     * }
     * @param payload The request body containing submission info.
     * @return The created submission.
     */
    @PostMapping
    public ResponseEntity<Submission> submitAssignment(@RequestBody Map<String, Object> payload) {
        // FIX: Cast directly from Number to avoid unnecessary String conversion
        Long assignmentId = ((Number) payload.get("assignmentId")).longValue();
        Long studentId = ((Number) payload.get("studentId")).longValue();
        String fileUrl = payload.get("fileUrl").toString();

        Submission newSubmission = submissionService.submitAssignment(assignmentId, studentId, fileUrl);
        return new ResponseEntity<>(newSubmission, HttpStatus.CREATED);
    }

    /**
     * GET /api/submissions/assignment/{assignmentId}
     * Gets all submissions for a specific assignment.
     * @param assignmentId The ID of the assignment.
     * @return A list of submissions.
     */
    @GetMapping("/assignment/{assignmentId}")
    public List<Submission> getSubmissionsForAssignment(@PathVariable Long assignmentId) {
        return submissionService.getSubmissionsForAssignment(assignmentId);
    }

    /**
     * GET /api/submissions/student/{studentId}
     * Gets all submissions from a specific student.
     * @param studentId The ID of the student.
     * @return A list of submissions.
     */
    @GetMapping("/student/{studentId}")
    public List<Submission> getSubmissionsByStudent(@PathVariable Long studentId) {
        return submissionService.getSubmissionsByStudent(studentId);
    }

    /**
     * GET /api/submissions
     * Gets all submissions.
     * @return A list of all submissions.
     */
    @GetMapping
    public List<Submission> getAllSubmissions() {
        return submissionService.getAllSubmissions();
    }

    /**
     * PATCH /api/submissions/{id}/grade
     * Marks a submission as graded.
     * @param id The ID of the submission.
     * @return The updated (graded) submission.
     */
    @PatchMapping("/{id}/grade")
    public ResponseEntity<Submission> gradeSubmission(@PathVariable Long id) {
        Submission gradedSubmission = submissionService.gradeSubmission(id);
        return ResponseEntity.ok(gradedSubmission);
    }
}

