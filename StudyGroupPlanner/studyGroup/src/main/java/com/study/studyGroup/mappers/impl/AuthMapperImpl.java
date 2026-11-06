package com.study.studyGroup.mappers.impl;

import org.springframework.stereotype.Component;

import com.study.studyGroup.domain.UserLoginRequest;
import com.study.studyGroup.domain.dtos.GetCurrentUserResponseDto;
import com.study.studyGroup.domain.dtos.UserLoginRequestDto;
import com.study.studyGroup.domain.dtos.UserLoginResponseDto;
import com.study.studyGroup.domain.entities.User;
import com.study.studyGroup.mappers.AuthMapper;

@Component 
public class AuthMapperImpl implements AuthMapper{
    @Override 
    public UserLoginRequest fromUserLoginRequestDto(UserLoginRequestDto userLoginRequestDto) {
        return new UserLoginRequest(
            userLoginRequestDto.getEmail(),
            userLoginRequestDto.getPassword()
        );
    }

    @Override 
    public UserLoginResponseDto toUserLoginResponseDto(String token) {
        return new UserLoginResponseDto(
            token
        );
    }

    @Override 
    public GetCurrentUserResponseDto toGetCurrentUserResponseDto(User user) {
        return new GetCurrentUserResponseDto(
            user.getId(),
            user.getName(),
            user.getEmail()
        );
    }
}
