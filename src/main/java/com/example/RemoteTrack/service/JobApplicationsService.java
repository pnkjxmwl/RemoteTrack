package com.example.RemoteTrack.service;


import com.example.RemoteTrack.dto.JobApplicationsRequestDto;
import com.example.RemoteTrack.dto.JobApplicationsResponseDto;
import com.example.RemoteTrack.entity.JobApplications;
import com.example.RemoteTrack.exception.ResourceNotFoundException;
import com.example.RemoteTrack.repository.JobApplicationsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class JobApplicationsService {
    @Autowired
    private JobApplicationsRepo jobApplicationsRepo;

    public JobApplicationsResponseDto createApplication(
            JobApplicationsRequestDto requestDto
    ) {
        JobApplications entity = new JobApplications();
        entity.setCompany(requestDto.getCompany());
        entity.setPosition(requestDto.getPosition());
        entity.setLocation(requestDto.getLocation());
        entity.setStatus(requestDto.getStatus());
        entity.setJob_link(requestDto.getJob_link());
        entity.setNotes(requestDto.getNotes());
        entity.setApplied_on(requestDto.getApplied_on());


        JobApplications savedEntity = jobApplicationsRepo.save(entity);

        JobApplicationsResponseDto responseDto = new JobApplicationsResponseDto();
        responseDto.setId(savedEntity.getId());
        responseDto.setCompany(savedEntity.getCompany());
        responseDto.setPosition(savedEntity.getPosition());
        responseDto.setLocation(savedEntity.getLocation());
        responseDto.setStatus(savedEntity.getStatus());
        responseDto.setJob_link(savedEntity.getJob_link());
        responseDto.setNotes(savedEntity.getNotes());
        responseDto.setApplied_on(savedEntity.getApplied_on());
        responseDto.setCreated_at(savedEntity.getCreated_at());
        responseDto.setUpdated_at(savedEntity.getUpdated_at());

        return responseDto;
    }

    public JobApplicationsResponseDto findApplicationById(UUID id) {

        Optional<JobApplications> application = jobApplicationsRepo.findById(id);
        JobApplicationsResponseDto responseDto = new JobApplicationsResponseDto();

        try {
            responseDto.setId(application.get().getId());
            responseDto.setCompany(application.get().getCompany());
            responseDto.setPosition(application.get().getPosition());
            responseDto.setLocation(application.get().getLocation());
            responseDto.setStatus(application.get().getStatus());
            responseDto.setJob_link(application.get().getJob_link());
            responseDto.setNotes(application.get().getNotes());
            responseDto.setApplied_on(application.get().getApplied_on());
            responseDto.setCreated_at(application.get().getCreated_at());
            responseDto.setUpdated_at(application.get().getUpdated_at());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return responseDto;
    }

    public JobApplicationsResponseDto updateApplication(UUID id, JobApplicationsRequestDto requestDto) {
        JobApplications application = jobApplicationsRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));

        application.setCompany(requestDto.getCompany());
        application.setPosition(requestDto.getPosition());
        application.setLocation(requestDto.getLocation());
        application.setNotes(requestDto.getNotes());
        application.setApplied_on(requestDto.getApplied_on());
        application.setStatus(requestDto.getStatus());
        application.setJob_link(requestDto.getJob_link());

        JobApplications savedEntity= jobApplicationsRepo.save(application);

        JobApplicationsResponseDto responseDto = new JobApplicationsResponseDto();
        responseDto.setId(savedEntity.getId());
        responseDto.setCompany(savedEntity.getCompany());
        responseDto.setPosition(savedEntity.getPosition());
        responseDto.setLocation(savedEntity.getLocation());
        responseDto.setStatus(savedEntity.getStatus());
        responseDto.setJob_link(savedEntity.getJob_link());
        responseDto.setNotes(savedEntity.getNotes());
        responseDto.setApplied_on(savedEntity.getApplied_on());
        responseDto.setCreated_at(savedEntity.getCreated_at());
        responseDto.setUpdated_at(savedEntity.getUpdated_at());

        return  responseDto;
    }

}
