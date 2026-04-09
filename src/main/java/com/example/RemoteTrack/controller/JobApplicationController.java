package com.example.RemoteTrack.controller;

import com.example.RemoteTrack.dto.JobApplicationsRequestDto;
import com.example.RemoteTrack.dto.JobApplicationsResponseDto;
import com.example.RemoteTrack.service.JobApplicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class JobApplicationController {

    @Autowired
    private JobApplicationsService jobApplicationsService;

    @PostMapping("/job")
    public ResponseEntity<JobApplicationsResponseDto> createJobApplication(
            @RequestBody JobApplicationsRequestDto requestDto,
            @AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        System.out.println("Creating job application for user: " + userId);
        JobApplicationsResponseDto responseDto = jobApplicationsService.createApplication(requestDto, userId);
        return ResponseEntity.status(201).body(responseDto);
    }

    @GetMapping("/job/{id}")
    public ResponseEntity<JobApplicationsResponseDto> findApplicationById(
            @PathVariable UUID id,
            @AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        JobApplicationsResponseDto responseDto = jobApplicationsService.findApplicationById(id, userId);
        return ResponseEntity.status(200).body(responseDto);
    }

    @PatchMapping("/job/{id}")
    public ResponseEntity<JobApplicationsResponseDto> updateApplication(
            @PathVariable UUID id,
            @RequestBody JobApplicationsRequestDto requestDto,
            @AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        JobApplicationsResponseDto responseDto = jobApplicationsService.updateApplication(id, requestDto, userId);
        return ResponseEntity.status(200).body(responseDto);
    }

    @DeleteMapping("/job/{id}")
    public ResponseEntity<Void> deleteApplication(
            @PathVariable UUID id,
            @AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        jobApplicationsService.deleteApplication(id, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/job")
    public ResponseEntity<List<JobApplicationsResponseDto>> findApplicationByFilter(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) LocalDate fromDate,
            @RequestParam(required = false) LocalDate toDate,
            @AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        List<JobApplicationsResponseDto> responseDtoList = jobApplicationsService.findApplications(search, status,
                fromDate, toDate, userId);
        return ResponseEntity.status(200).body(responseDtoList);
    }

}
