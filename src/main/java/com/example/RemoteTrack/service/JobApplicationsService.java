package com.example.RemoteTrack.service;


import com.example.RemoteTrack.dto.JobApplicationsRequestDto;
import com.example.RemoteTrack.dto.JobApplicationsResponseDto;
import com.example.RemoteTrack.entity.JobApplications;
import com.example.RemoteTrack.exception.ResourceNotFoundException;
import com.example.RemoteTrack.repository.JobApplicationsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

        JobApplications savedEntity = jobApplicationsRepo.save(application);

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

    public List<JobApplicationsResponseDto> findApplications(
        String search,
        String status,
        LocalDate fromDate,
        LocalDate toDate
    ){
//        System.out.println(fromDate+ " , " +toDate);
        List<JobApplications> jobs = jobApplicationsRepo.searchJobs(status,search,fromDate,toDate);
        List<JobApplicationsResponseDto> jobsResposneList = new ArrayList<>();
        for(JobApplications job:jobs){
            JobApplicationsResponseDto jobdto= new JobApplicationsResponseDto();
            jobdto.setId(job.getId());
            jobdto.setCompany(job.getCompany());
            jobdto.setPosition(job.getPosition());
            jobdto.setLocation(job.getLocation());
            jobdto.setStatus(job.getStatus());
            jobdto.setJob_link(job.getJob_link());
            jobdto.setNotes(job.getNotes());
            jobdto.setApplied_on(job.getApplied_on());
            jobdto.setCreated_at(job.getCreated_at());
            jobdto.setUpdated_at(job.getUpdated_at());
            jobsResposneList.add(jobdto);
        }
        return  jobsResposneList;
    }

    public void deleteApplication(UUID id) {
        JobApplications application = jobApplicationsRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));
        jobApplicationsRepo.delete(application);
    }


}
