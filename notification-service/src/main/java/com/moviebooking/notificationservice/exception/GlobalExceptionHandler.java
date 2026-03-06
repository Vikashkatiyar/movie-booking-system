package com.moviebooking.notificationservice.exception;

import com.moviebooking.notificationservice.dto.NotificationResponse;
import com.moviebooking.notificationservice.dto.NotificationStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotificationException.class)
    public ResponseEntity<NotificationResponse> handleNotificationException(NotificationException ex) {

        NotificationResponse response = NotificationResponse.builder()
                .status(NotificationStatus.FAILED)
                .message(ex.getMessage())
                .build();

        return ResponseEntity.internalServerError().body(response);
    }
}
