package com.example.assignment_tracker.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.assignment_tracker.model.Assignment;
import com.example.assignment_tracker.model.Student;
import com.example.assignment_tracker.model.Submission;
import com.example.assignment_tracker.model.SubmissionStatus;
import com.example.assignment_tracker.repository.AssignmentRepository;
import com.example.assignment_tracker.repository.StudentRepository;
import com.example.assignment_tracker.repository.SubmissionRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    /**
     * Allows a student to submit an assignment.
     * @param assignmentId The ID of the assignment.
     * @param studentId The ID of the student.
     * @param fileUrl The URL of the uploaded file.
     * @return The new submission.
     */
    public Submission submitAssignment(Long assignmentId, Long studentId, String fileUrl) {
        // Find the student
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));

        // Find the assignment
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new EntityNotFoundException("Assignment not found with id: " + assignmentId));

        // Check if the due date has passed
        if (LocalDate.now().isAfter(assignment.getDueDate())) {
            // You could throw an exception or just mark it as LATE
            // For now, we'll just throw an error
            throw new IllegalStateException("The deadline for this assignment has passed.");
        }

        // Create the new submission
        Submission submission = new Submission();
        submission.setStudent(student);
        submission.setAssignment(assignment);
        submission.setFileUrl(fileUrl);
        submission.setSubmittedDate(LocalDate.now());
        submission.setStatus(SubmissionStatus.SUBMITTED);

        return submissionRepository.save(submission);
    }

    /**
     * Gets all submissions for a specific assignment.
     * @param assignmentId The ID of the assignment.
     * @return A list of submissions.
     */
    public List<Submission> getSubmissionsForAssignment(Long assignmentId) {
        return submissionRepository.findByAssignmentId(assignmentId);
    }

    /**
     * Gets all submissions by a specific student.
     * @param studentId The ID of the student.
     * @return A list of submissions.
     */
    public List<Submission> getSubmissionsByStudent(Long studentId) {
        return submissionRepository.findByStudentId(studentId);
    }

    /**
     * Gets all submissions.
     * @return A list of all submissions.
     */
    public List<Submission> getAllSubmissions() {
        return submissionRepository.findAll();
    }

    /**
     * Allows faculty to grade a submission.
     * @param submissionId The ID of the submission to grade.
     * @return The graded submission.
     */
    public Submission gradeSubmission(Long submissionId) {
        // Find the submission
        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new EntityNotFoundException("Submission not found with id: " + submissionId));

        // Update its status
        submission.setStatus(SubmissionStatus.GRADED);
        
        return submissionRepository.save(submission);
    }
}
