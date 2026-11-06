package com.study.studyGroup.domain.dtos;

public class UserLoginResponseDto {

    public UserLoginResponseDto() {

    }

    public UserLoginResponseDto(String token) {
        this.token = token;
    }

    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
