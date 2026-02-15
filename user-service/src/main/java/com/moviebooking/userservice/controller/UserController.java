package com.moviebooking.userservice.controller;


import com.moviebooking.userservice.dto.AuthRequest;
import com.moviebooking.userservice.dto.AuthResponse;
import com.moviebooking.userservice.dto.UserRequest;
import com.moviebooking.userservice.dto.UserResponse;
import com.moviebooking.userservice.mapper.UserMapper;
import com.moviebooking.userservice.model.Users;
import com.moviebooking.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;


    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest userRequest) {
        Users registeredUser = service.register(userRequest);
        return new ResponseEntity<>(UserMapper.mapToUserResponse(registeredUser), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) {
        String verifiedToken = service.verify(authRequest);
        return ResponseEntity.ok(AuthResponse.builder()
                .token(verifiedToken)
                .tokenType("Bearer")
                .build());
    }
}
