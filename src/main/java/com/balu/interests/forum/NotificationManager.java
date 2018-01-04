package com.balu.interests.forum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class NotificationManager {

	@Autowired
	JavaMailSender javaMailSender;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Async
	public void sendMail(String from, String to, String subject, String body) {

		System.out.println(Thread.currentThread().getName());
		SimpleMailMessage mail = new SimpleMailMessage();

		mail.setFrom(from);
		mail.setTo(to);
		mail.setSubject(subject);
		mail.setText(body);

		logger.info("Sending...");

		javaMailSender.send(mail);

		logger.info("Done!");
	}
}
