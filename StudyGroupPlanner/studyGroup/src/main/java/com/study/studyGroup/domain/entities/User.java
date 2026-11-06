package com.study.studyGroup.domain.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    @Email(message = "Invalid email format")
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    // List of sessions created by the user
    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL)
    private List<StudySession> organizedSessions = new ArrayList<>();

    // Sessions attended by the user
    @ManyToMany(mappedBy = "attendees")
    private List<StudySession> attendedSessions = new ArrayList<>();

    public User() {

    }

    public User(UUID id, String name, @Email(message = "Invalid email format") String email, String password,
            List<StudySession> organizedSessions, List<StudySession> attendedSessions) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.organizedSessions = organizedSessions;
        this.attendedSessions = attendedSessions;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public List<StudySession> getOrganizedSessions() {
        return organizedSessions;
    }

    public void setOrganizedSessions(List<StudySession> organizedSessions) {
        this.organizedSessions = organizedSessions;
    }

    public List<StudySession> getAttendedSessions() {
        return attendedSessions;
    }

    public void setAttendedSessions(List<StudySession> attendedSessions) {
        this.attendedSessions = attendedSessions;
    } 
}
