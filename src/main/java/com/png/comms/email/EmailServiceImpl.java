package com.png.comms.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@Component
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    public void sendSimpleMessage(Mail mail) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(mail.getTo());
            message.setSubject(mail.getSubject());
            message.setText(mail.getContent());

            emailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }


    @Override
    public void sendMessageWithAttachment(Mail mail) {
        try {
            String [] toList = mail.getToList();
            String to = mail.getTo();
            String [] ccList = mail.getCcList();
            String cc = mail.getCc();
            String [] bccList = mail.getBccList();
            String bcc = mail.getBcc();
            MimeMessage message = emailSender.createMimeMessage();
            // pass 'true' to the constructor to create a multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            if (to != null)
                helper.setTo(to);
            else if (toList != null)
                helper.setTo(toList);
            if (cc != null)
                helper.setCc(cc);
            else if (ccList != null)
                helper.setCc(ccList);
            if (bcc != null)
                helper.setBcc(bcc);
            else if(bccList != null)
                helper.setBcc(bccList);
            helper.setSubject(mail.getSubject());
            helper.setFrom(mail.getInternetAddressFrom());
            Context context = new Context();
            context.setVariables(mail.getTemplateModel());
            String html=springTemplateEngine.process("verify-email",context);
            helper.setText(html, true);

            String pathToAttachment = mail.getPathToAttachment();
            String attachmentFilename = mail.getAttachmentFilename();
            if (pathToAttachment!=null && attachmentFilename!=null) {
                FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
                helper.addAttachment(attachmentFilename, file);
            }
            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    @Async
    public void sendEmailAsync(String firstName, String email){
        try {
            Thread.sleep(10000);
            Mail mail = new Mail();
            HashMap<String, Object> modelMap = new HashMap<>();
            modelMap.put("name", firstName);
            mail.setFromAddress("web.maplesnmist@gmail.com");
            mail.setFromName("Maples 'N' Mist - Online");
            mail.setTemplateModel(modelMap);
            mail.setToList(new String[]{email});
            mail.setBcc("arindam.bandyopadhyay@gmail.com");
            mail.setSubject("Hi, " + firstName + " verify your email (Do Not Reply)");
            sendMessageWithAttachment(mail);
        } catch (Exception e){

        }
    }


}
