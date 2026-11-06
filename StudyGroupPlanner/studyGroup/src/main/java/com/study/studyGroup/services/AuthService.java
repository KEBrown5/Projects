package com.study.studyGroup.services;

import com.study.studyGroup.domain.UserLoginRequest;

public interface AuthService {
    String login(UserLoginRequest userLoginRequest);
}
