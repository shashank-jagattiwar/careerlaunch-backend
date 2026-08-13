 package com.careerlaunch.backend.controller;

import com.careerlaunch.backend.dto.request.JobRequest;
import com.careerlaunch.backend.dto.response.JobResponse;
import com.careerlaunch.backend.service.JobService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    // ============================
    // GET ALL JOBS
    // ============================

    @GetMapping
    public ResponseEntity<List<JobResponse>> getAllJobs() {

        return ResponseEntity.ok(
                jobService.getAllJobs()
        );
    }

    // ============================
    // GET JOB BY ID
    // ============================

    @GetMapping("/{id}")
    public ResponseEntity<JobResponse> getJobById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                jobService.getJobById(id)
        );
    }

    // ============================
    // CREATE JOB
    // ============================

    @PostMapping
    public ResponseEntity<JobResponse> createJob(
            @Valid @RequestBody JobRequest request
    ) {

        return ResponseEntity.ok(
                jobService.createJob(request)
        );
    }

    // ============================
    // UPDATE JOB
    // ============================

    @PutMapping("/{id}")
    public ResponseEntity<JobResponse> updateJob(
            @PathVariable Long id,
            @Valid @RequestBody JobRequest request
    ) {

        return ResponseEntity.ok(
                jobService.updateJob(id, request)
        );
    }

    // ============================
    // DELETE JOB
    // ============================

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(
            @PathVariable Long id
    ) {

        jobService.deleteJob(id);

        return ResponseEntity.ok(
                "Job deleted successfully"
        );
    }
}

