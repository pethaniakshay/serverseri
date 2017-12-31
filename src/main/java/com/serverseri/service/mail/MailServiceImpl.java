package com.serverseri.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MailServiceImpl implements MailService{

  @Autowired
  JavaMailSender javaMailSender;

  @Override
  @Async
  public void sendMail(String from,String to, String subject, String body) {
    SimpleMailMessage mail = new SimpleMailMessage();
    mail.setFrom(from);
    mail.setTo(to);
    mail.setSubject(subject);
    mail.setText(body);
    log.info("Sending...............");
    javaMailSender.send(mail);
    log.info("Done!.............");


  }

}
