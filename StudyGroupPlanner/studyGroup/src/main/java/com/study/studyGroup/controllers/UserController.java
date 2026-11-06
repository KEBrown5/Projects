package com.study.studyGroup.controllers;

import static com.study.studyGroup.util.JwtUtil.parseUserId;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import com.study.studyGroup.domain.entities.User;
import com.study.studyGroup.mappers.UserMapper;
import com.study.studyGroup.services.UserService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@CrossOrigin 
@RestController 
@RequestMapping (path = "/api/v1/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/allUsers") 
    public ResponseEntity<Page<ListAllUsersResponseDto>> listAllUsers(Pageable pageable) {
        Page<User> users = userService.listAllUsers(pageable);

        return ResponseEntity.ok(users.map(userMapper::toListAllUsersResponseDto));
    }

    // TODO: Maybe edit this one out? Or change it for security reasons
    @GetMapping("/{id}")
    public ResponseEntity<GetUserByIdResponseDto> getUserById(@PathVariable UUID id) {
        User user = userService.getUserById(id);

        return ResponseEntity.ok(userMapper.toGetUserByIdResponseDto(user));
    }

    @PostMapping("/createUser") 
    public ResponseEntity<CreateUserResponseDto> createUser(@Valid @RequestBody CreateUserRequestDto createUserRequestDto) {
        CreateUserRequest createUserRequest = userMapper.fromCreateUserRequestDto(createUserRequestDto);

        User user = userService.createUser(createUserRequest);

        CreateUserResponseDto createUserResponseDto = userMapper.toCreateUserResponseDto(user);

        return new ResponseEntity<>(createUserResponseDto, HttpStatus.CREATED);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UpdateUserResponseDto> updateUserById(@AuthenticationPrincipal Jwt jwt, 
        @Valid @RequestBody UpdateUserRequestDto updateUserRequestDto) {

        UpdateUserRequest updateUserRequest = userMapper.fromUpdateUserRequestDto(updateUserRequestDto);
        UUID userId = parseUserId(jwt);

        User user = userService.updateUser(updateUserRequest, userId);

        UpdateUserResponseDto updateUserResponseDto = userMapper.toUpdateUserResponseDto(user);

        return new ResponseEntity<>(updateUserResponseDto, HttpStatus.OK);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<UpdatePasswordResponseDto> updateUserPasswordById(@AuthenticationPrincipal Jwt jwt, 
        @Valid @RequestBody UpdatePasswordRequestDto updatePasswordRequestDto) {
        
        UpdatePasswordRequest updatePasswordRequest = userMapper.fromUpdatePasswordRequestDto(updatePasswordRequestDto);
        UUID userId = parseUserId(jwt);

        User user = userService.updatePassword(updatePasswordRequest, userId);

        UpdatePasswordResponseDto updatePasswordResponseDto = userMapper.toUpdatePasswordResponseDto(user);

        return new ResponseEntity<>(updatePasswordResponseDto, HttpStatus.OK);
    }

    // @DeleteMapping ("/deleteUser/{id}")
    // public ResponseEntity<Void> deleteUserById(@PathVariable UUID id) {
    //     userService.deleteUserById(id);

    //     return ResponseEntity.noContent().build();
    // }
}
