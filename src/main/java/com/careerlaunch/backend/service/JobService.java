package com.careerlaunch.backend.service;

import com.careerlaunch.backend.dto.request.JobRequest;
import com.careerlaunch.backend.dto.response.JobResponse;
import com.careerlaunch.backend.entity.Job;
import com.careerlaunch.backend.exception.JobNotFoundException;
import com.careerlaunch.backend.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    // ============================
    // CREATE JOB
    // ============================

    public JobResponse createJob(JobRequest request) {

        Job job = new Job();

        job.setTitle(request.getTitle());
        job.setCompany(request.getCompany());
        job.setLocation(request.getLocation());
        job.setDescription(request.getDescription());
        job.setRequiredSkills(request.getRequiredSkills());
        job.setJobType(request.getJobType());
        job.setExperience(request.getExperience());
        job.setApplicationUrl(request.getApplicationUrl());

        Job savedJob = jobRepository.save(job);

        return mapToResponse(savedJob);
    }

    // ============================
    // GET ALL JOBS
    // ============================

    public List<JobResponse> getAllJobs() {

        return jobRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ============================
    // GET JOB BY ID
    // ============================

    public JobResponse getJobById(Long id) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() ->
                        new JobNotFoundException(
                                "Job not found with id: " + id
                        )
                );

        return mapToResponse(job);
    }
   //
    //this methos declared the job updates
    public JobResponse updateJob(
            Long id,
            JobRequest request
    ) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() ->
                        new JobNotFoundException(
                                "Job not found with id: " + id
                        )
                );

        job.setTitle(request.getTitle());
        job.setCompany(request.getCompany());
        job.setLocation(request.getLocation());
        job.setDescription(request.getDescription());
        job.setRequiredSkills(request.getRequiredSkills());
        job.setJobType(request.getJobType());
        job.setExperience(request.getExperience());
        job.setApplicationUrl(request.getApplicationUrl());

        Job updatedJob = jobRepository.save(job);

        return mapToResponse(updatedJob);
    }


    // ============================
    // DELETE JOB
    // ============================

    public void deleteJob(Long id) {

        if (!jobRepository.existsById(id)) {
            throw new JobNotFoundException(
                    "Job not found with id: " + id
            );
        }

        jobRepository.deleteById(id);
    }

    // ============================
    // ENTITY → RESPONSE
    // ============================

    private JobResponse mapToResponse(Job job) {

        return new JobResponse(
                job.getId(),
                job.getTitle(),
                job.getCompany(),
                job.getLocation(),
                job.getDescription(),
                job.getRequiredSkills(),
                job.getJobType(),
                job.getExperience(),
                job.getApplicationUrl()
        );
    }
}