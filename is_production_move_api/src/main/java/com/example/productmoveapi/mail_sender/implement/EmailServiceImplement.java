package com.example.productmoveapi.mail_sender.implement;

import com.example.productmoveapi.mail_sender.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author Binh Nguyen Thai at 11:07 on 05/12/2022
 */
@Service
public class EmailServiceImplement implements EmailService {

  private final JavaMailSender mailSender;

  public EmailServiceImplement(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Override
  public void sendEmail(String from, String to, String subject, String body) {
    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    simpleMailMessage.setFrom(from);
    simpleMailMessage.setTo(to);
    simpleMailMessage.setSubject(subject);
    simpleMailMessage.setText(body);
    mailSender.send(simpleMailMessage);
  }
}
