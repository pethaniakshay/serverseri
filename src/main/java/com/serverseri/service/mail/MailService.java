package com.serverseri.service.mail;

public interface MailService {

  void sendMail(String from,String to, String subject, String body);

  void sendHTMLMail(String from,String to, String subject, String body);

}
