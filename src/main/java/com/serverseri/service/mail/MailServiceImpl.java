package com.serverseri.service.mail;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
    try {
      SimpleMailMessage mail = new SimpleMailMessage();
      mail.setFrom(from);
      mail.setTo(to);
      mail.setSubject(subject);
      mail.setText(body);
      javaMailSender.send(mail);
      log.info("Mail sent to the : "+ to);
    } catch(Exception e) {
      log.error("Error: ", e);
    }
  }

  @Override
  @Async
  public void sendHTMLMail(String from,String to, String subject, String body) {
    try {
      MimeMessage mimeMessage = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false,"utf-8");
      mimeMessage.setContent(body, "text/html");
      helper.setFrom(from);
      helper.setTo(to);
      helper.setSubject(subject);
      helper.setText(body,true);
      javaMailSender.send(mimeMessage);
      log.info("Mail sent to the : "+ to);
    } catch(Exception e) {
      log.error("Error: ", e);
    }
  }
}
