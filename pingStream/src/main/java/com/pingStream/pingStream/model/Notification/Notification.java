package com.pingStream.pingStream.model.Notification;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Notification {
    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    private String recipientEmail;
    private String recipientPhoneNumber;
    private String recipientToken;

    private String messageSubject;
    private String messageBody;

    private LocalDateTime createdAt;
    private LocalDateTime deliveredAt;
    private String errorMessage;
}

