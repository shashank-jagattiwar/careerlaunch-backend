package com.careerlaunch.backend.service;

import com.careerlaunch.backend.entity.Job;
import com.careerlaunch.backend.entity.SavedJob;
import com.careerlaunch.backend.entity.User;
import com.careerlaunch.backend.exception.JobNotFoundException;
import com.careerlaunch.backend.repository.JobRepository;
import com.careerlaunch.backend.repository.SavedJobRepository;
import com.careerlaunch.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavedJobService {

    private final SavedJobRepository savedJobRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    public SavedJobService(
            SavedJobRepository savedJobRepository,
            UserRepository userRepository,
            JobRepository jobRepository
    ) {
        this.savedJobRepository = savedJobRepository;
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
    }

    // ============================
    // SAVE JOB
    // ============================

    public void saveJob(String email, Long jobId) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found with email: " + email
                        )
                );

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new JobNotFoundException(
                                "Job not found with id: " + jobId
                        )
                );

        if (savedJobRepository.existsByUserEmailAndJobId(
                email,
                jobId
        )) {

            throw new RuntimeException(
                    "Job is already saved"
            );
        }

        SavedJob savedJob = new SavedJob(user, job);

        savedJobRepository.save(savedJob);
    }

    // ============================
    // GET MY SAVED JOBS
    // ============================

    public List<SavedJob> getMySavedJobs(String email) {

        return savedJobRepository
                .findByUserEmailOrderBySavedAtDesc(email);
    }

    // ============================
    // REMOVE SAVED JOB
    // ============================

    public void removeSavedJob(
            String email,
            Long jobId
    ) {

        if (!savedJobRepository.existsByUserEmailAndJobId(
                email,
                jobId
        )) {

            throw new RuntimeException(
                    "Saved job not found"
            );
        }

        savedJobRepository.deleteByUserEmailAndJobId(
                email,
                jobId
        );
    }
}