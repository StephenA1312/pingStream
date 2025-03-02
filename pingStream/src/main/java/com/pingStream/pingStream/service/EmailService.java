package com.pingStream.pingStream.service;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;
import java.util.*;

@Service
@Slf4j
public class EmailService {
    private final WebClient webClient;
    Dotenv dotenv = Dotenv.load();
    private final String API_KEY = dotenv.get("AHASEND_API_KEY");
    private final String API_URL = dotenv.get("AHASEND_API_URL");


    public EmailService() {
        log.info("Api key Last 4 is " + StringUtils.right(API_KEY, 4));
        log.info("Api url Last 4 is " + StringUtils.right(API_URL, 4));
        this.webClient = WebClient.builder()
                .baseUrl(API_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("X-Api-Key", API_KEY) // Correct header name
                .build();
    }

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
        log.info("Sending email to " + recipientEmail);
        Map<String, Object> requestPayload = new HashMap<>();

        // Sender
        Map<String, String> from = Map.of(
                "email", "info@pingstream.e13ven.xyz",
                "name", "Ping Stream"
        );
        requestPayload.put("from", from);
        // Recipients
        List<Map<String, String>> recipients = List.of(Map.of(
                "email", recipientEmail,
                "name", recipientEmail
        ));
        requestPayload.put("recipients", recipients);


        // Email Content
        Map<String, Object> content = new HashMap<>();
        content.put("subject", messageSubject);
        content.put("text_body", messageBody);
        //content.put("html_body", htmlBody);
        //content.put("reply_to", replyTo);
        content.put("headers", Map.of(
                "X-My-Mail-ID", "12345",
                "X-Comment-ID", "456789"
        ));

        requestPayload.put("content", content);
        try {
            String response = webClient.post()
                    .bodyValue(requestPayload)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block(); // Blocking call (can be made reactive if needed)

            log.info("Email sent successfully to {}: {}", recipientEmail, response);
            return true;
        } catch (Exception e) {
            log.error("Failed to send email to {}: {}", recipientEmail, e.getMessage());
            return false;
        }

    }
}
