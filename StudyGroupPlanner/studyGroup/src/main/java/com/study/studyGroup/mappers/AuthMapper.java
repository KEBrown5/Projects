package com.study.studyGroup.mappers;

import com.study.studyGroup.domain.UserLoginRequest;
import com.study.studyGroup.domain.dtos.GetCurrentUserResponseDto;
import com.study.studyGroup.domain.dtos.UserLoginRequestDto;
import com.study.studyGroup.domain.dtos.UserLoginResponseDto;
import com.study.studyGroup.domain.entities.User;

public interface AuthMapper {
    UserLoginRequest fromUserLoginRequestDto(UserLoginRequestDto userLoginRequestDto);

    UserLoginResponseDto toUserLoginResponseDto(String token);

    GetCurrentUserResponseDto toGetCurrentUserResponseDto(User user);
}
