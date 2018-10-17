package com.png.comms.email;

import org.springframework.mail.SimpleMailMessage;

import java.sql.Timestamp;

public interface EmailService {
    void sendSimpleMessage(Mail mail);
    void sendMessageWithAttachment(Mail mail);
    void sendEmailAsync(String firstName, String email);
    void sendValidationEmailAsync(String firstName, String email, Timestamp validUptoTimestamp, String validationCode);
}
