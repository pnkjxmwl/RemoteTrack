package com.example.RemoteTrack.controller;

import com.example.RemoteTrack.dto.JobApplicationsRequestDto;
import com.example.RemoteTrack.dto.JobApplicationsResponseDto;
import com.example.RemoteTrack.service.JobApplicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
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
            ){
        JobApplicationsResponseDto responseDto =null;
        try {
           responseDto =  jobApplicationsService.findApplicationById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  ResponseEntity.status(200).body(responseDto);
    }

    @PatchMapping("/job/{id}")
    public  ResponseEntity<JobApplicationsResponseDto> updateApplication(
        @PathVariable UUID id,
        @RequestBody JobApplicationsRequestDto requestDto
    ){
        JobApplicationsResponseDto responseDto= jobApplicationsService.updateApplication(id,requestDto);
        return  ResponseEntity.status(200).body(responseDto);
    }

    @DeleteMapping("/job/{id}")
    public ResponseEntity<Void> deleteApplication(
        @PathVariable UUID id
    ){
        jobApplicationsService.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }

}
