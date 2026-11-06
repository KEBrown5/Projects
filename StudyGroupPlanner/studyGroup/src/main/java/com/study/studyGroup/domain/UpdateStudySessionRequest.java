package com.study.studyGroup.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import com.study.studyGroup.domain.entities.StudySessionStatusEnum;

public class UpdateStudySessionRequest {

    public UpdateStudySessionRequest() {
        
    }

    public UpdateStudySessionRequest(UUID id, String name, String description, LocalDateTime session_start, LocalDateTime session_end,
            String location, StudySessionStatusEnum status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.session_start = session_start;
        this.session_end = session_end;
        this.location = location;
        this.status = status;
    }

    private UUID id;
    private String name;
    private String description;
    private LocalDateTime session_start;
    private LocalDateTime session_end;
    private String location;
    private StudySessionStatusEnum status;

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

    
}
