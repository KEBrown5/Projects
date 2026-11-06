package com.study.studyGroup.domain;

import java.util.UUID;

public class UpdatePasswordRequest {

    public UpdatePasswordRequest() {

    }

    public UpdatePasswordRequest(UUID id, String password, String confirmPassword) {
        this.id = id;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    private UUID id;
    private String password;
    private String confirmPassword;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
