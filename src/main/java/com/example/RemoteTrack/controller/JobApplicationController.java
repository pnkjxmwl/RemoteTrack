package com.example.RemoteTrack.controller;

import com.example.RemoteTrack.dto.JobApplicationsRequestDto;
import com.example.RemoteTrack.dto.JobApplicationsResponseDto;
import com.example.RemoteTrack.service.JobApplicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
}
