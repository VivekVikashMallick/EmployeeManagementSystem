package com.company.notification;

import org.springframework.stereotype.Component;

@Component("smsNotificationService")
public class SmsNotificationService implements NotificationService{
    @Override
    public void sendNotification(String message) {
        System.out.println("[SMS] Message: " + message);
    }
}
