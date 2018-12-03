package com.png.services;

import com.png.comms.email.EmailService;
import com.png.comms.email.Mail;
import com.png.data.dto.otpvalidation.EmailOtpValidationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtpGeneratorService {
    @Autowired
    private EmailService emailService;
    public EmailOtpValidationDto generateEmailOpt(EmailOtpValidationDto emailOtp){
        emailOtp.createOtp(4);
        Mail mail = new Mail();
        mail.setTo(emailOtp.getEmail());
        mail.setSubject("Email OTP Validation");
        mail.setContent(String.format("Hi, Your OTP to validate this email address is : %s , from team Maples N Mist",emailOtp.getOtp()));
        emailService.sendSimpleMessage(mail);
        return emailOtp;
    }
}
