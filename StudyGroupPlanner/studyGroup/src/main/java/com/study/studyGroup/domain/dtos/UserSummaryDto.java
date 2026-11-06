package com.study.studyGroup.domain.dtos;

import java.util.UUID;

// Shallow user DTO (No session list or sensitive fields)
public class UserSummaryDto {
    
    public UserSummaryDto() {

    }

    public UserSummaryDto(String name, String email, UUID userId) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    private UUID userId;
    private String name;
    private String email;
    
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
}
