package com.study.studyGroup.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.study.studyGroup.domain.UserLoginRequest;
import com.study.studyGroup.domain.entities.User;
import com.study.studyGroup.repositories.UserRepository;
import com.study.studyGroup.services.AuthService;
import com.study.studyGroup.util.JwtUtil;

@Service 
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;

    @Autowired 
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired 
    private JwtUtil jwtUtil;

    // Validates credentials and returns a cookie with a jwt on success
    @Override
    public String login(UserLoginRequest userLoginRequest) {
        User user = userRepository.findByEmail(userLoginRequest.getEmail()).orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())) {
            throw new Error("Invalid password");
        }

        String token = jwtUtil.generateToken(user);

        // ResponseCookie cookie = ResponseCookie
        //     .from("access_token", token)
        //     // .httpOnly(true)
        //     .httpOnly(false) // httpOnly for now during testing. CHANGE TO TRUE WHEN HOSTED WITH HTTPS
        //     .secure(true)
        //     .sameSite("Lax")
        //     .path("/")
        //     .maxAge(Duration.ofHours(1))
        //     .build();

        // return cookie;

        return token;
    }
}
