package com.png.comms.email;

import org.springframework.mail.SimpleMailMessage;

import java.sql.Timestamp;

public interface EmailService {
    void sendSimpleMessage(Mail mail);

    void sendMessageWithAttachment(Mail mail, String mailTemplate);

    void sendEmailAsyncWithAttachment(String firstName, String email, String emailTemplate);

    void sendValidationEmailAsync(String baseUrl, String firstName, String email, Timestamp validUptoTimestamp, String validationCode);

    void sendForgotPasswordEmailAsync(String baseUrl, String firstName, String email, Timestamp validUptoTimestamp,
                                      String validationCode);
}
