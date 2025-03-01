//add package
package com.pingStream.pingStream.service;

import org.springframework.stereotype.Service;

import com.pingStream.pingStream.model.Notification;

@Service
public class NotificationService {
    
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private PushNotificationService pushNotificationService;
    
    @Transactional
    public UUID sendNotification(Notification notification) {
        notification.setId(UUID.randomUUID());
        notification.setStatus(NotificationStatus.PENDING);
        notification.setCreatedAt(LocalDateTime.now());
        notificationRepository.save(notification);
        
        processNotification(notification);
        return notification.getId();
    }

    private void processNotification(Notification notification) {
        try {
            switch (notification.getType()) {
                case EMAIL:
                    emailService.sendEmail(notification.getRecipientEmail(), notification.getMessageSubject(), notification.getMessageBody());
                    break;
                case SMS:
                    smsService.sendSms(notification.getRecipientPhoneNumber(), notification.getMessageBody());
                    break;
                case PUSH:
                    pushNotificationService.sendPushNotification(notification.getRecipientToken(), notification.getMessageBody());
                    break;
            }

            notification.setStatus(NotificationStatus.SENT);
            notification.setDeliveredAt(LocalDateTime.now());
        } catch (Exception e) {
            notification.setStatus(NotificationStatus.FAILED);
            notification.setErrorMessage(e.getMessage());
            // You might want to log the error here
            throw new NotificationProcessingException("Failed to process notification", e);
        }
    }
    notificationRepository.save(notification);
}