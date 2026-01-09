package com.example.RemoteTrack.dto;

import java.time.LocalDate;
import java.util.Date;

public class JobApplicationsRequestDto {

    private String company;
    private String position;
    private String location;
    private String status;
    private String job_link;
    private String notes;
    private LocalDate applied_on;

    public JobApplicationsRequestDto(String company, String position, String location, String status, String job_link, String notes, LocalDate applied_on) {
        this.company = company;
        this.position = position;
        this.location = location;
        this.status = status;
        this.job_link = job_link;
        this.notes = notes;
        this.applied_on = applied_on;
    }

    public JobApplicationsRequestDto() {
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJob_link() {
        return job_link;
    }

    public void setJob_link(String job_link) {
        this.job_link = job_link;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getApplied_on() {
        return applied_on;
    }

    public void setApplied_on(LocalDate applied_on) {
        this.applied_on = applied_on;
    }
}
