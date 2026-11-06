package com.study.studyGroup.domain;

import java.time.LocalDateTime;

public class CreateStudySessionRequest {

    public CreateStudySessionRequest() {

    }

    public CreateStudySessionRequest(String name, String description, LocalDateTime session_start, LocalDateTime session_end,
            String location) {
        this.name = name;
        this.description = description;
        this.session_start = session_start;
        this.session_end = session_end;
        this.location = location;
    }

    private String name;
    private String description;
    private LocalDateTime session_start;
    private LocalDateTime session_end;
    private String location;

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
}
 
