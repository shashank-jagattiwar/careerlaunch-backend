 package com.careerlaunch.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class JobRequest {

    @NotBlank(message = "Job title is required")
    @Size(
            max = 100,
            message = "Job title must not exceed 100 characters"
    )
    private String title;

    @NotBlank(message = "Company name is required")
    @Size(
            max = 100,
            message = "Company name must not exceed 100 characters"
    )
    private String company;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Job description is required")
    @Size(
            min = 20,
            max = 5000,
            message = "Description must be between 20 and 5000 characters"
    )
    private String description;

    // Optional
    private String requiredSkills;

    @NotBlank(message = "Job type is required")
    private String jobType;

    // Optional
    private String experience;

    // Optional
    // If provided, it must start with http:// or https://
    @Pattern(
            regexp = "^$|^(https?://).+",
            message = "Application URL must start with http:// or https://"
    )
    private String applicationUrl;

    public JobRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getApplicationUrl() {
        return applicationUrl;
    }

    public void setApplicationUrl(String applicationUrl) {
        this.applicationUrl = applicationUrl;
    }
}

