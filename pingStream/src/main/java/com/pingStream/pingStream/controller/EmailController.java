package com.pingStream.pingStream.controller;

import com.pingStream.pingStream.model.Notification.EmailRequest;
import com.pingStream.pingStream.service.EmailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/email") // Base URL for email-related endpoints
@Tag(name = "Email API", description = "API for sending emails")
public class EmailController {
    private final EmailService emailService;
    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
        boolean success = emailService.send(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getBody());

        if (success) {
            return ResponseEntity.ok("Email sent successfully!");
        } else {
            return ResponseEntity.status(500).body("Failed to send email.");
        }
    }

    @GetMapping("/works")
    public String getWorks(){
        log.info("Testing here");
        return "API works Stephen!!";
    }

}
