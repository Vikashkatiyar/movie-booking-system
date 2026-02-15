package com.moviebooking.userservice.mapper;

import com.moviebooking.userservice.dto.UserRequest;
import com.moviebooking.userservice.dto.UserResponse;
import com.moviebooking.userservice.model.Users;

import java.util.Objects;

public class UserMapper {

    public static Users mapToUser(UserRequest userRequest) {
        if(Objects.isNull(userRequest)) {
            return new Users();
        }

        return Users.builder()
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .email(userRequest.getEmail())
                .roles(userRequest.getRoles())
                .build();
    }

    public static UserResponse mapToUserResponse(Users user) {
        if(Objects.isNull(user)) {
            return new UserResponse();
        }

        return UserResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles())
                .build();
    }
}
