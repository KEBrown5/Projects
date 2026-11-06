package com.study.studyGroup.domain;

import java.util.UUID;

public class DeleteStudySessionRequest {
    public DeleteStudySessionRequest() {

    }

    public DeleteStudySessionRequest(UUID sessionId) {
        this.sessionId = sessionId;
    }

    private UUID sessionId;

    public UUID getSessionId() {
        return sessionId;
    }

    public void setSessionId(UUID sessionId) {
        this.sessionId = sessionId;
    }
}
