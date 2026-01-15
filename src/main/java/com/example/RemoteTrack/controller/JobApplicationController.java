package com.example.RemoteTrack.controller;

import com.example.RemoteTrack.dto.JobApplicationsRequestDto;
import com.example.RemoteTrack.dto.JobApplicationsResponseDto;
import com.example.RemoteTrack.service.JobApplicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class JobApplicationController {

    @Autowired
    private JobApplicationsService jobApplicationsService;

    @PostMapping("/job")
    public ResponseEntity<JobApplicationsResponseDto> createJobApplication(
            @RequestBody JobApplicationsRequestDto requestDto
    ) {
        JobApplicationsResponseDto responseDto = jobApplicationsService.createApplication(requestDto);
        return ResponseEntity.status(201).body(responseDto);
    }

    @GetMapping("/job/{id}")
    public ResponseEntity<JobApplicationsResponseDto> findApplicationById(
            @PathVariable UUID id
    ) {
        JobApplicationsResponseDto responseDto = null;
        try {
            responseDto = jobApplicationsService.findApplicationById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(200).body(responseDto);
    }

    @PatchMapping("/job/{id}")
    public ResponseEntity<JobApplicationsResponseDto> updateApplication(
            @PathVariable UUID id,
            @RequestBody JobApplicationsRequestDto requestDto
    ) {
        JobApplicationsResponseDto responseDto = jobApplicationsService.updateApplication(id, requestDto);
        return ResponseEntity.status(200).body(responseDto);
    }

    @DeleteMapping("/job/{id}")
    public ResponseEntity<Void> deleteApplication(
            @PathVariable UUID id
    ) {
        jobApplicationsService.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/job")
    public ResponseEntity<List<JobApplicationsResponseDto>> findApplicationByFilter(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) LocalDate fromDate,
            @RequestParam(required = false) LocalDate toDate
    ) {
        List<JobApplicationsResponseDto> responseDtoList = jobApplicationsService.findApplications(search, status, fromDate, toDate);
        return ResponseEntity.status(200).body(responseDtoList);
    }
}
