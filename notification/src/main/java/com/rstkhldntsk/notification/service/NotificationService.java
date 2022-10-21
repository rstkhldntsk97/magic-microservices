package com.rstkhldntsk.notification.service;

import com.rstkhldntsk.client.notification.NotificationRequest;
import com.rstkhldntsk.notification.model.Notification;
import com.rstkhldntsk.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record NotificationService(NotificationRepository notificationRepository) {

    public void send(NotificationRequest notificationRequest) {
        var notification = Notification.builder()
                .toCustomerId(notificationRequest.toCustomerId())
                .toCustomerEmail(notificationRequest.toCustomerEmail())
                .sender("rstkhldntsk")
                .message(notificationRequest.message())
                .sentAt(LocalDateTime.now())
                .build();
        notificationRepository.saveAndFlush(notification);
    }

}
