package com.pingStream.pingStream.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    public String sendEmail(String recipientEmail, String messageSubject, String messageBody) {
        validateEmailDetails(recipientEmail, messageSubject, messageBody);

        boolean emailSent = send(recipientEmail, messageSubject, messageBody);

        return emailSent ? "Email sent successfully" : "Failed to send email";
    }

    public void validateEmailDetails(String recipientEmail, String messageSubject, String messageBody) {
        validateNotEmpty(recipientEmail, "Email address cannot be empty");
        validateNotEmpty(messageSubject, "Message subject cannot be empty");
        validateNotEmpty(messageBody, "Message body cannot be empty");
    }

    public void validateNotEmpty(String value, String errorMessage) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public boolean send(String recipientEmail, String messageSubject, String messageBody) {
        //Implement actual sending logic here.
        log.info("Sending email to " + recipientEmail);

        return true;
    }
}
