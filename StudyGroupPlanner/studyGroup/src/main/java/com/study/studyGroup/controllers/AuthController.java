package com.study.studyGroup.controllers;

import static com.study.studyGroup.util.JwtUtil.parseUserId;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.studyGroup.domain.UserLoginRequest;
import com.study.studyGroup.domain.dtos.GetCurrentUserResponseDto;
import com.study.studyGroup.domain.dtos.UserLoginRequestDto;
import com.study.studyGroup.domain.dtos.UserLoginResponseDto;
import com.study.studyGroup.domain.entities.User;
import com.study.studyGroup.mappers.AuthMapper;
import com.study.studyGroup.mappers.UserMapper;
import com.study.studyGroup.services.AuthService;
import com.study.studyGroup.services.UserService;

@CrossOrigin 
@RestController 
@RequestMapping (path = "/api/v1/auth")
public class AuthController {
    private final UserService userService;
    private final UserMapper userMapper;

    private final AuthService authService;
    private final AuthMapper authMapper;

    public AuthController(UserService userService, UserMapper userMapper, AuthService authService, AuthMapper authMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.authService = authService;
        this.authMapper = authMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        UserLoginRequest userLoginRequest = authMapper.fromUserLoginRequestDto(userLoginRequestDto);

        String token = userService.login(userLoginRequest);

        UserLoginResponseDto userLoginResponseDto = authMapper.toUserLoginResponseDto(token);

        return ResponseEntity.ok(userLoginResponseDto);
    }

    @GetMapping("/getCurrentUser")
    public ResponseEntity<GetCurrentUserResponseDto> getCurrentUser(@AuthenticationPrincipal Jwt jwt) {
        UUID userId = parseUserId(jwt);

        User user = userService.getUserById(userId);

        GetCurrentUserResponseDto getCurrentUserResponseDto = authMapper.toGetCurrentUserResponseDto(user);

        return ResponseEntity.ok(getCurrentUserResponseDto);
    }
}
