package com.pingStream.pingStream.service;

import org.springframework.stereotype.Service;

@Service
public class SmsService {

    public String sendSms(String recipientPhoneNumber, String messageBody) {
        validateSmsDetails(recipientPhoneNumber, messageBody);

        boolean smsSent = send(recipientPhoneNumber, messageBody);

        return smsSent ? "SMS sent successfully" : "Failed to send SMS";
    }

    private boolean send(String recipientPhoneNumber, String messageBody) {
        //Implement actual sending logic here.
        return true;
    }

    private void validateSmsDetails(String recipientPhoneNumber, String messageBody) {
        validateNotEmpty(recipientPhoneNumber, "Phone number cannot be empty");
        validateNotEmpty(messageBody, "Message body cannot be empty");

    }

    private void validateNotEmpty(String boday, String messageBodyCannotBeEmpty) {
        if (boday == null || boday.trim().isEmpty()) {
            throw new IllegalArgumentException(messageBodyCannotBeEmpty);
        }
    }
}
