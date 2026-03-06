package com.moviebooking.notificationservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationRequest {

    @NotBlank
    private String userId;

    @NotBlank
    private String bookingId;

    @Email
    private String email;
}
