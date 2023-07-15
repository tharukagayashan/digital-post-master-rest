package com.projects.digitalpostmasterrest.common;

import com.projects.digitalpostmasterrest.dto.custom.MailReqDto;
import com.projects.digitalpostmasterrest.error.ErrorAlert;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Date;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(MailReqDto mailReqDto) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setSubject(mailReqDto.getSubject());
            messageHelper.setFrom("tgotpservice@gmail.com");
            messageHelper.setTo(mailReqDto.getTo());
            messageHelper.setSentDate(new Date());
            message.setContent(mailReqDto.getBody(),"text/html");
            mailSender.send(message);
        } catch (Exception e) {
            throw new ErrorAlert(e.getMessage(), "400");
        }
    }

}
