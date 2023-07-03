package com.projects.digitalpostmasterrest.common;

import com.projects.digitalpostmasterrest.dto.custom.MailReqDto;
import com.projects.digitalpostmasterrest.error.ErrorAlert;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MailService {

    public void sendMail(JavaMailSender mailSender, MailReqDto mailReqDto) {
        new Thread(() -> {
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("tgotpservice@gmail.com");
                message.setTo(mailReqDto.getTo());
                message.setSentDate(new Date());
                message.setSubject(mailReqDto.getSubject());
                message.setText(mailReqDto.getBody());
                mailSender.send(message);
            } catch (Exception e) {
                throw new ErrorAlert(e.getMessage(), "400");
            }
        }).start();
    }

}
