package com.study.studyGroup.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseCookie;

import com.study.studyGroup.domain.CreateUserRequest;
import com.study.studyGroup.domain.UpdatePasswordRequest;
import com.study.studyGroup.domain.UpdateUserRequest;
import com.study.studyGroup.domain.UserLoginRequest;
import com.study.studyGroup.domain.entities.User;

public interface UserService {
    Page<User> listAllUsers(Pageable pageable);

    User getUserById(UUID id);

    User findByEmail(String email);

    User createUser(CreateUserRequest request);

    User updateUser(UpdateUserRequest request, UUID id);

    User updatePassword(UpdatePasswordRequest request, UUID id);

    void deleteUserById(UUID id);

    String login(UserLoginRequest userLoginRequest);
}
