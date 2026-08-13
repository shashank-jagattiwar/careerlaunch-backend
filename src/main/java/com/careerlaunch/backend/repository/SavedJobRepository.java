package com.careerlaunch.backend.repository;

import com.careerlaunch.backend.entity.SavedJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SavedJobRepository extends JpaRepository<SavedJob, Long> {

    List<SavedJob> findByUserEmailOrderBySavedAtDesc(String email);

    Optional<SavedJob> findByUserEmailAndJobId(
            String email,
            Long jobId
    );

    boolean existsByUserEmailAndJobId(
            String email,
            Long jobId
    );

    void deleteByUserEmailAndJobId(
            String email,
            Long jobId
    );
}