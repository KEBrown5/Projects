package com.study.studyGroup.domain.dtos;

import java.util.UUID;

public class CreateUserResponseDto {

    public CreateUserResponseDto() {

    }

    public CreateUserResponseDto(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    UUID id;
    String name;
    String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
