package com.study.studyGroup.domain.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "studySessions")
public class StudySession {

    public StudySession() {
        
    }

    public StudySession(UUID id, String name, String description, LocalDateTime session_start, LocalDateTime session_end, String location,
            StudySessionStatusEnum status, LocalDateTime created_at, LocalDateTime updated_at, User organizer,
            List<User> attendees) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.session_start = session_start;
        this.session_end = session_end;
        this.location = location;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.organizer = organizer;
        this.attendees = attendees;
    }

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "session_start")
    private LocalDateTime session_start;

    @Column(name = "session_end")
    private LocalDateTime session_end;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StudySessionStatusEnum status;

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime created_at;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updated_at;

    // Organizer of study session
    @ManyToOne 
    @JoinColumn(name = "organizer_id", nullable = false)
    private User organizer;

    // Users attending the session
    @ManyToMany 
    @JoinTable(
        name = "study_session_attendees",
        joinColumns = @JoinColumn(name = "study_session_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> attendees = new ArrayList<>();

    // Possibly add max attendee limit?

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

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public List<User> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<User> attendees) {
        this.attendees = attendees;
    }
}
