package com.png.comms.email;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    void sendSimpleMessage(Mail mail);
    void sendMessageWithAttachment(Mail mail);
    void sendEmailAsync(String firstName, String email);
}
