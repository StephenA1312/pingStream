package com.pingStream.pingStream.service;

import org.springframework.stereotype.Service;

@Service
public class PushNotificationService {

    public String sendPushNotification(String recipientToken, String messageBody) {
        validatePushNotificationDetails(recipientToken, messageBody);

        boolean smsSent = send(recipientToken, messageBody);

        return smsSent ? "Push notification sent successfully" : "Failed to send push notification";
    }

    private boolean send(String recipientToken, String messageBody) {
        //Implement actual sending logic here.
        return true;
    }

    private void validatePushNotificationDetails(String recipientToken, String messageBody) {
        validateNotEmpty(recipientToken, "Recipient token cannot be empty");
        validateNotEmpty(messageBody, "Message body cannot be empty");
    }

    private void validateNotEmpty(String messageBody, String messageBodyCannotBeEmpty) {
        if (messageBody == null || messageBody.trim().isEmpty()) {
            throw new IllegalArgumentException(messageBodyCannotBeEmpty);
        }
    }
}
