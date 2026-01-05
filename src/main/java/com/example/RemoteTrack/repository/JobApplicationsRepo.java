package com.example.RemoteTrack.repository;

import com.example.RemoteTrack.entity.JobApplications;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobApplicationsRepo extends JpaRepository<JobApplications, UUID> {
}
