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

public interface JobApplicationsRepo extends JpaRepository<JobApplications, UUID> {

    @Query("""
            SELECT j FROM JobApplications j
            WHERE (?1 IS NULL OR j.status = ?1)
            AND (
                ?2 IS NULL OR
                j.company LIKE (CONCAT(CAST(?2 AS text), '%')) OR
                j.position LIKE (CONCAT(CAST(?2 AS text),'%'))
            )
            """)
    List<JobApplications> searchJobs(String status, String search, LocalDate fromDate, LocalDate toDate);
}
