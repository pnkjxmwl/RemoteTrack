package com.example.RemoteTrack.service;


import com.example.RemoteTrack.dto.JobApplicationsRequestDto;
import com.example.RemoteTrack.dto.JobApplicationsResponseDto;
import com.example.RemoteTrack.entity.JobApplications;
import com.example.RemoteTrack.repository.JobApplicationsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

}
