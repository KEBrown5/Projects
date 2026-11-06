package com.study.studyGroup.domain.dtos;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public class CreateUserRequestDto {

    public CreateUserRequestDto() {
    }

    public CreateUserRequestDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @NotBlank(message = "Name cannot be null")
    @Length(max = 50, message = "Name cannot exceed 50 characters")
    public String name;

    @NotBlank(message = "Email cannot be null")
    @Length(max = 100, message = "Email cannot exceed 100 characters")
    public String email;

    @NotBlank (message = "Password cannot be null")
    @Length(max = 50, message = "Password cannot exceed 50 characters")
    public String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
