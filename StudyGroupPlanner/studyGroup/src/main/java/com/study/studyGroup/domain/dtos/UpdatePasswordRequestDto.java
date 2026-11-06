package com.study.studyGroup.domain.dtos;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdatePasswordRequestDto {

    public UpdatePasswordRequestDto() {

    }

    public UpdatePasswordRequestDto(@NotNull(message = "ID cannot be empty") UUID id,
            @NotBlank(message = "Password cannot be empty") @Length(message = "Password cannot exceed 50 characters") String password,
            @NotBlank(message = "confirmPassword cannot be empty") @Length(message = "confirmPassword cannot exceed 50 characters") String confirmPassword) {

        this.id = id;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    @NotNull (message = "ID cannot be empty")
    private UUID id;

    @NotBlank(message = "Password cannot be empty")
    @Length(message = "Password cannot exceed 50 characters")
    private String password;

    @NotBlank(message = "confirmPassword cannot be empty")
    @Length(message = "Password cannot exceed 50 characters")
    private String confirmPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
