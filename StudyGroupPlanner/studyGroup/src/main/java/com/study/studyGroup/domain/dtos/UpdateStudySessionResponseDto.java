package com.study.studyGroup.domain.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.study.studyGroup.domain.entities.StudySessionStatusEnum;

public class UpdateStudySessionResponseDto {

    public UpdateStudySessionResponseDto() {
        
    }

    public UpdateStudySessionResponseDto(UUID sessionId, String name, String description, LocalDateTime session_start,
            LocalDateTime session_end, String location, StudySessionStatusEnum status, LocalDateTime createdAt,
            LocalDateTime updatedAt) {

        this.sessionId = sessionId;
        this.name = name;
        this.description = description;
        this.session_start = session_start;
        this.session_end = session_end;
        this.location = location;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private UUID sessionId;
    private String name;
    private String description;
    private LocalDateTime session_start;
    private LocalDateTime session_end;
    private String location;
    private StudySessionStatusEnum status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UUID getSessionId() {
        return sessionId;
    }

    public void setSessionId(UUID sessionId) {
        this.sessionId = sessionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getSession_start() {
        return session_start;
    }

    public void setSession_start(LocalDateTime session_start) {
        this.session_start = session_start;
    }

    public LocalDateTime getSession_end() {
        return session_end;
    }

    public void setSession_end(LocalDateTime session_end) {
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
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
