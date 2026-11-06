package com.study.studyGroup.domain.dtos;

import java.util.UUID;

public class RegisterForStudySessionRequestDto {
    private UUID sessionId;
    
    public RegisterForStudySessionRequestDto() {
        
    }

    public RegisterForStudySessionRequestDto(UUID sessionId) {
        this.sessionId = sessionId;
    }

    public UUID getSessionId() {
        return sessionId;
    }

    public void setSessionId(UUID sessionId) {
        this.sessionId = sessionId;
    }
}
