package com.school.web.models;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;

@Service
public class email_service 
{
	@Autowired
	private JavaMailSender mailsender;
	
	
	@Async
	public void sendEmailAssignment(String to, String title, String description, String endDate) {
	    try {
	        MimeMessage message = mailsender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
	        helper.setTo(to);
	        helper.setSubject("New Assignment: " + title);
	        helper.setText("<p><b>Description:</b> " + description + "</p>" +
	                       "<p><b>Due Date:</b> " + endDate + "</p>", true);
	        mailsender.send(message);
	    } catch (MessagingException e) 
	    {
	        e.printStackTrace(); 
	    }
	}
	
	@Async
	public void sendEmailRemind(String to,String title)
	{
		try
		{
			MimeMessage message=mailsender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(message,true);
			helper.setTo(to);
			helper.setSubject("Assignment Reminder: "+title);
			helper.setText("<p><b>Reminder for Assignment complete it before enddate</b></p>", true);
			mailsender.send(message);			
		}
		catch(MessagingException e)
		{
	        e.printStackTrace(); 
		}
	}
	@Async
	public void sendEmailWithAttachment(String to,String subject,String body,File attachment)
	{
		try
		{
			MimeMessage message =mailsender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(message,true);
			
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, true);
			
			FileSystemResource file=new FileSystemResource(attachment);
			helper.addAttachment(file.getFilename(), file);
			mailsender.send(message);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
