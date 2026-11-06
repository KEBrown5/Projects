package com.study.studyGroup.domain.dtos;

import jakarta.validation.constraints.NotBlank;

public class UserLoginRequestDto {
    public UserLoginRequestDto() {

    }

    public UserLoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @NotBlank (message = "Email cannot be null")
    private String email;

    @NotBlank (message = "Password cannot be null")
    private String password;

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
