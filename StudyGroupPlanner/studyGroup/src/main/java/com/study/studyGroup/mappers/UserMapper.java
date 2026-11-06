package com.study.studyGroup.mappers;

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

public interface UserMapper {
    ListAllUsersResponseDto toListAllUsersResponseDto(User user);

    GetUserByIdResponseDto toGetUserByIdResponseDto(User user);

    CreateUserRequest fromCreateUserRequestDto(CreateUserRequestDto createUserRequestDto);

    CreateUserResponseDto toCreateUserResponseDto(User user);

    UpdateUserRequest fromUpdateUserRequestDto(UpdateUserRequestDto updateUserRequestDto);

    UpdateUserResponseDto toUpdateUserResponseDto(User user);

    UpdatePasswordRequest fromUpdatePasswordRequestDto(UpdatePasswordRequestDto updatePasswordRequestDto);

    UpdatePasswordResponseDto toUpdatePasswordResponseDto(User user);

    UserSummaryDto ToUserSummaryDto(User user);

    // UserLoginRequest fromUserLoginRequestDto(UserLoginRequestDto userLoginRequestDto);

    // UserLoginResponseDto toUserLoginResponseDto(String token);

    // GetCurrentUserResponseDto tpGetCurrentUserResponseDto(User user);
}
