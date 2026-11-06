package com.study.studyGroup.domain.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import com.study.studyGroup.domain.entities.StudySessionStatusEnum;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateStudySessionRequestDto {

    public UpdateStudySessionRequestDto() {

    }

    public UpdateStudySessionRequestDto(@NotNull(message = "Session ID must be provided") UUID sessionId,
            @NotBlank(message = "Session must have a name") @Length(max = 255, message = "Session name must be less than 255 characters") String name,
            @Length(max = 255, message = "Session description must be less than 255 characters") String description,
            @NotNull(message = "The session must have a start time") @FutureOrPresent(message = "Session start time must be in the future") LocalDateTime session_start,
            @NotNull(message = "The session must have an end time") LocalDateTime session_end,
            @NotBlank(message = "The session must have a location") String location,
            @NotBlank(message = "The session must have a status") StudySessionStatusEnum status) {

        this.sessionId = sessionId;
        this.name = name;
        this.description = description;
        this.session_start = session_start;
        this.session_end = session_end;
        this.location = location;
        this.status = status;
    }

    @NotNull(message = "Session ID must be provided")
    private UUID sessionId;

    @NotBlank(message = "Session must have a name")
    @Length(max = 255, message = "Session name must be less than 255 characters")
    private String name;

    @Length(max = 255, message = "Session description must be less than 255 characters")
    private String description;

    @NotNull(message = "The session must have a start time")
    @FutureOrPresent(message = "Session start time must be in the future")
    private LocalDateTime session_start;

    private LocalDateTime session_end;

    @NotBlank(message = "The session must have a location")
    private String location;

    @NotNull(message = "The session must have a status")
    private StudySessionStatusEnum status;

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
