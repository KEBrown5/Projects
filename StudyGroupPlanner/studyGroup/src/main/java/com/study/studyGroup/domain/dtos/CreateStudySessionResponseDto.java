package com.study.studyGroup.domain.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.study.studyGroup.domain.entities.StudySessionStatusEnum;

public class CreateStudySessionResponseDto {
    
    public CreateStudySessionResponseDto() {
        
    }

    public CreateStudySessionResponseDto(UUID id, String name, String description, LocalDateTime session_start, LocalDateTime session_end,
            String location, StudySessionStatusEnum status, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.session_start = session_start;
        this.session_end = session_end;
        this.location = location;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    private UUID id;
    private String name;
    private String description;
    private LocalDateTime session_start;
    private LocalDateTime session_end;
    private String location;
    private StudySessionStatusEnum status;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getSessionStart() {
        return session_start;
    }

    public void setSessionStart(LocalDateTime session_start) {
        this.session_start = session_start;
    }

    public LocalDateTime getSessionEnd() {
        return session_end;
    }

    public void setSessionEnd(LocalDateTime session_end) {
        this.session_end = session_end;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public StudySessionStatusEnum getStatus() {
        return status;
    }

    public void setStatus(StudySessionStatusEnum status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdatedAt() {
        return updated_at;
    }

    public void setUpdatedAt(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}
