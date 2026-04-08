package com.example.RemoteTrack.repository;

import com.example.RemoteTrack.entity.JobApplications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

public interface JobApplicationsRepo extends JpaRepository<JobApplications, UUID> {

    @Query("""
            SELECT j FROM JobApplications j
            WHERE j.userId = ?1
            AND (?2 IS NULL OR j.status = ?2)
            AND (
                ?3 IS NULL OR
                j.company LIKE (CONCAT(CAST(?3 AS text), '%')) OR
                j.position LIKE (CONCAT(CAST(?3 AS text),'%'))
            )
            AND (?4 IS NULL OR j.appliedOn >= ?4)
            AND (?5 IS NULL OR j.appliedOn <= ?5)
            """)
    List<JobApplications> searchJobs(String userId, String status, String search, LocalDate fromDate, LocalDate toDate);
    
    Optional<JobApplications> findByIdAndUserId(UUID id, String userId);
}
