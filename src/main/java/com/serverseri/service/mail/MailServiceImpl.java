package com.serverseri.service.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
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
		
		logger.info("Sending...............");
		
		javaMailSender.send(mail);
		
		logger.info("Done!.............");
		
		
	}

}
