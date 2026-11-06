package com.study.studyGroup.services.impl;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.study.studyGroup.domain.CreateUserRequest;
import com.study.studyGroup.domain.UpdatePasswordRequest;
import com.study.studyGroup.domain.UpdateUserRequest;
import com.study.studyGroup.domain.UserLoginRequest;
import com.study.studyGroup.domain.entities.User;
import com.study.studyGroup.repositories.UserRepository;
import com.study.studyGroup.services.UserService;
import com.study.studyGroup.util.JwtUtil;

@Service 
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired 
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired 
    private JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(CreateUserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        
        String password = passwordEncoder.encode(request.getPassword());
        user.setPassword(password);

        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(UUID id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
        }
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        return userOptional.orElse(null);
    }

    @Override
    public Page<User> listAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getUserById(UUID id) {
        Optional<User> userOptional = userRepository.findById(id);
        
        return userOptional.orElse(null);
    }

    @Override 
    public User updatePassword(UpdatePasswordRequest request, UUID id) {
        if (request.getId() == null) {
            throw new Error("Request must have a valid ID");
        }

        if (!request.getId().equals(id)) {
            throw new Error("Request ID and User ID must match");
        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new Error("Passwords do not match");
        }

        User user = userRepository.findById(id).orElseThrow(() -> new Error("User not found with ID: " + id.toString()));

        String password = passwordEncoder.encode(request.getPassword());
        user.setPassword(password);
        
        return userRepository.save(user);
    }

    // Takes in a request with updated user info and the id of the user to be updated
    @Override
    public User updateUser(UpdateUserRequest request, UUID userId) {
        if (request.getId() == null) {
            throw new Error("Request must have a valid ID");
        }

        if (!request.getId().equals(userId)) {
            throw new Error("Request ID and User ID must match");
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new Error("User not found with ID: " + userId.toString()));

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        return userRepository.save(user);
    } 

    // Validates credentials and returns a cookie with a jwt on success
    @Override
    public String login(UserLoginRequest userLoginRequest) {
        User user = userRepository.findByEmail(userLoginRequest.getEmail()).orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())) {
            throw new Error("Invalid password");
        }

        String token = jwtUtil.generateToken(user);

        return token;
    }
}
