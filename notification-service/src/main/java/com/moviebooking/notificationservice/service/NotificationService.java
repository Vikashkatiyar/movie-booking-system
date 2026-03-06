package com.moviebooking.notificationservice.service;

import com.moviebooking.notificationservice.dto.NotificationRequest;
import com.moviebooking.notificationservice.dto.NotificationResponse;

public interface NotificationService {
    NotificationResponse sendBookingNotification(NotificationRequest request);
}
