package com.study.studyGroup.mappers.impl;

import org.springframework.stereotype.Component;

import com.study.studyGroup.domain.CreateUserRequest;
import com.study.studyGroup.domain.UpdatePasswordRequest;
import com.study.studyGroup.domain.UpdateUserRequest;
import com.study.studyGroup.domain.UserLoginRequest;
import com.study.studyGroup.domain.dtos.CreateUserRequestDto;
import com.study.studyGroup.domain.dtos.CreateUserResponseDto;
import com.study.studyGroup.domain.dtos.GetCurrentUserResponseDto;
import com.study.studyGroup.domain.dtos.GetUserByIdResponseDto;
import com.study.studyGroup.domain.dtos.ListAllUsersResponseDto;
import com.study.studyGroup.domain.dtos.UpdatePasswordRequestDto;
import com.study.studyGroup.domain.dtos.UpdatePasswordResponseDto;
import com.study.studyGroup.domain.dtos.UpdateUserRequestDto;
import com.study.studyGroup.domain.dtos.UpdateUserResponseDto;
import com.study.studyGroup.domain.dtos.UserLoginRequestDto;
import com.study.studyGroup.domain.dtos.UserLoginResponseDto;
import com.study.studyGroup.domain.dtos.UserSummaryDto;
import com.study.studyGroup.domain.entities.User;
import com.study.studyGroup.mappers.UserMapper;

@Component 
public class UserMapperImpl implements UserMapper {

    @Override 
    public ListAllUsersResponseDto toListAllUsersResponseDto(User user) {
        return new ListAllUsersResponseDto(
            user.getId(),
            user.getName(),
            user.getEmail()
        );
    }

    @Override 
    public GetUserByIdResponseDto toGetUserByIdResponseDto(User user) {
        return new GetUserByIdResponseDto(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getPassword()
        );
    }

    @Override 
    public CreateUserRequest fromCreateUserRequestDto(CreateUserRequestDto createUserRequestDto) {
        return new CreateUserRequest(
            createUserRequestDto.getName(),
            createUserRequestDto.getEmail(),
            createUserRequestDto.getPassword()
        );
    }

    @Override 
    public CreateUserResponseDto toCreateUserResponseDto(User user) {
        return new CreateUserResponseDto(
            user.getId(),
            user.getName(),
            user.getEmail()
        );
    }

    @Override 
    public UpdateUserRequest fromUpdateUserRequestDto(UpdateUserRequestDto updateUserRequestDto) {
        return new UpdateUserRequest(
            updateUserRequestDto.getId(),
            updateUserRequestDto.getName(),
            updateUserRequestDto.getEmail()
        );
    }

    @Override 
    public UpdateUserResponseDto toUpdateUserResponseDto(User user) {
        return new UpdateUserResponseDto(
            user.getId(),
            user.getName(),
            user.getEmail()
        );
    }

    @Override 
    public UpdatePasswordRequest fromUpdatePasswordRequestDto(UpdatePasswordRequestDto updatePasswordRequestDto) {
        return new UpdatePasswordRequest(
            updatePasswordRequestDto.getId(),
            updatePasswordRequestDto.getPassword(),
            updatePasswordRequestDto.getConfirmPassword()
        );
    }

    @Override 
    public UpdatePasswordResponseDto toUpdatePasswordResponseDto(User user) {
        return new UpdatePasswordResponseDto(
            user.getId()
        );
    }

    @Override 
    public UserSummaryDto ToUserSummaryDto(User user) {
        return new UserSummaryDto(
            user.getName(),
            user.getEmail(),
            user.getId()
        );
    }

}
