package com.careerlaunch.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String company;

    private String location;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String requiredSkills;

    private String jobType;

    private String experience;

    private String applicationUrl;

    public Job() {
    }

    public Job(
            String title,
            String company,
            String location,
            String description,
            String requiredSkills,
            String jobType,
            String experience,
            String applicationUrl
    ) {
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setApplicationUrl(String applicationUrl) {
        this.applicationUrl = applicationUrl;
    }
}