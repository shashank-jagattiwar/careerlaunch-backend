package com.careerlaunch.backend.dto.response;

public class JobResponse {

    private Long id;
    private String title;
    private String company;
    private String location;
    private String description;
    private String requiredSkills;
    private String jobType;
    private String experience;
    private String applicationUrl;

    public JobResponse() {
    }

    public JobResponse(
            Long id,
            String title,
            String company,
            String location,
            String description,
            String requiredSkills,
            String jobType,
            String experience,
            String applicationUrl
    ) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.location = location;
        this.description = description;
        this.requiredSkills = requiredSkills;
        this.jobType = jobType;
        this.experience = experience;
        this.applicationUrl = applicationUrl;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public String getJobType() {
        return jobType;
    }

    public String getExperience() {
        return experience;
    }

    public String getApplicationUrl() {
        return applicationUrl;
    }
}