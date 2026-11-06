package com.study.studyGroup.domain.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.study.studyGroup.domain.entities.StudySessionStatusEnum;
import com.study.studyGroup.domain.entities.User;

public class ListStudySessionResponseDto {

    public ListStudySessionResponseDto() {

    } 

    public ListStudySessionResponseDto(UUID sessionId, String name, String description, LocalDateTime session_start,
            LocalDateTime session_end, String location, StudySessionStatusEnum status, UserSummaryDto organizer,
            List<UserSummaryDto> attendees) {
        this.sessionId = sessionId;
        this.name = name;
        this.description = description;
        this.session_start = session_start;
        this.session_end = session_end;
        this.location = location;
        this.status = status;
        this.organizer = organizer;
        this.attendees = attendees;
    }

    private UUID sessionId;
    private String name;
    private String description;
    private LocalDateTime session_start;
    private LocalDateTime session_end;
    private String location;
    private StudySessionStatusEnum status;
    private UserSummaryDto organizer;
    private List<UserSummaryDto> attendees;

    public UUID getSessionId() {
        return sessionId;
    }

    public void setSessionId(UUID id) {
        this.sessionId = id;
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

    public UserSummaryDto getOrganizer() {
        return organizer;
    }

    public void setOrganizer(UserSummaryDto organizer) {
        this.organizer = organizer;
    }

    public List<UserSummaryDto> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<UserSummaryDto> attendees) {
        this.attendees = attendees;
    }

}

