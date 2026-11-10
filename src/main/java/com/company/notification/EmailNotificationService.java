package com.company.notification;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class EmailNotificationService implements NotificationService{
    @Value("${notification.email.from:no-reply@example.com}")
    private String fromAddress;

    @Override
    public void sendNotification(String message) {
        System.out.println("[EMAIL] From: " + fromAddress + " | Message: " + message);
    }
}
