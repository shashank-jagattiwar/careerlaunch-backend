package com.careerlaunch.backend.controller;

import com.careerlaunch.backend.entity.SavedJob;
import com.careerlaunch.backend.service.SavedJobService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/saved-jobs")
public class SavedJobController {

    private final SavedJobService savedJobService;

    public SavedJobController(SavedJobService savedJobService) {
        this.savedJobService = savedJobService;
    }

    // ============================
    // SAVE JOB
    // ============================

    @PostMapping("/{jobId}")
    public ResponseEntity<String> saveJob(
            @PathVariable Long jobId,
            Authentication authentication
    ) {

        String email = authentication.getName();

        savedJobService.saveJob(email, jobId);

        return ResponseEntity.ok(
                "Job saved successfully"
        );
    }

    // ============================
    // GET MY SAVED JOBS
    // ============================

    @GetMapping
    public ResponseEntity<List<SavedJob>> getMySavedJobs(
            Authentication authentication
    ) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                savedJobService.getMySavedJobs(email)
        );
    }

    // ============================
    // REMOVE SAVED JOB
    // ============================

    @DeleteMapping("/{jobId}")
    public ResponseEntity<String> removeSavedJob(
            @PathVariable Long jobId,
            Authentication authentication
    ) {

        String email = authentication.getName();

        savedJobService.removeSavedJob(
                email,
                jobId
        );

        return ResponseEntity.ok(
                "Job removed from saved jobs"
        );
    }
}