package com.study.studyGroup.domain.dtos;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateUserRequestDto {

    public UpdateUserRequestDto() {

    }

    public UpdateUserRequestDto(@NotNull(message = "User id must be provided") UUID id,
            @NotBlank(message = "Name must be provided") @Length(max = 50, message = "Name cannot exceed 50 characters") String name,
            @NotBlank(message = "Email must be provided") @Length(max = 100, message = "Email cannot exceed 100 characters") String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @NotNull(message = "User id must be provided")
    private UUID id;

    @NotBlank(message = "Name must be provided")
    @Length(max = 50, message = "Name cannot exceed 50 characters")
    private String name;

    @NotBlank(message = "Email must be provided")
    @Length(max = 100, message = "Email cannot exceed 100 characters")
    private String email;

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
