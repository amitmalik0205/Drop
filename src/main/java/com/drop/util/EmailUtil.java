package com.drop.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

@Component
public class EmailUtil {
	
	private static final Logger logger = Logger.getLogger(EmailUtil.class);
	
	@Autowired
	@Qualifier("velocityEngine")
	private static VelocityEngine velocityEngine;
	
	@Value("velocitytemplates")
	private static String templateFileFolder;	
	
	@Autowired
	@Qualifier("emailConfig")
	private static Properties emailConfig;
	
	@Autowired
	@Qualifier("velocityConfig")
	private static Properties velocityConfig;
	
	final static String  senderEmailID = emailConfig.getProperty("mail.username"); 
	final static String senderPassword = emailConfig.getProperty("mail.password");
	
	public static void sendEmail(String subject, String recipeintEmail, String emailtype, Object object, String fileName) {
		
		logger.info("Sending Email to "+recipeintEmail);
		
		boolean isEmailSent = true;
		Map model = createTemplateModel(emailtype, object);
		String templateFilePath = getTemplateFilePath(emailtype);
		
		String messageBody = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateFilePath, model);	
		
		final String emailSMTPserver = emailConfig.getProperty("mail.host");
		final String emailServerPort = emailConfig.getProperty("mail.port");

		Properties props = new Properties();
		
		props.put("mail.smtp.user", senderEmailID);
		props.put("mail.smtp.host", emailSMTPserver);
		props.put("mail.smtp.port", emailServerPort);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.socketFactory.port", emailServerPort);
//		props.put("mail.smtp.socketFactory.class",
//				"javax.net.ssl.SSLSocketFactory");
//		props.put("mail.smtp.socketFactory.fallback", "true");

		try {
				Authenticator auth = new SMTPAuthenticator();
				javax.mail.Session session = javax.mail.Session.getInstance(props,auth);
	
				MimeMessage message = new MimeMessage(session);
				message.setSubject(subject);
				message.setFrom(new InternetAddress(senderEmailID));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipeintEmail));
				message.setSentDate(new Date());
				
				if(fileName == null || fileName.length() <= 0) {
					message.setText(messageBody);
				} else {
					MimeBodyPart messagePart = new MimeBodyPart();
		            messagePart.setText(messageBody);
					
		            MimeBodyPart attachmentPart = new MimeBodyPart();
		            FileDataSource fileDataSource = new FileDataSource(fileName) {
		                @Override
		                public String getContentType() {
		                    return "application/octet-stream";
		                }
		            };
		            attachmentPart.setDataHandler(new DataHandler(fileDataSource));
		            attachmentPart.setFileName(fileDataSource.getName());
		 
		            Multipart multipart = new MimeMultipart();
		            multipart.addBodyPart(messagePart);
		            multipart.addBodyPart(attachmentPart);
		 
		            message.setContent(multipart);
				}
				Transport.send(message);
		} catch (MessagingException e) {
			isEmailSent = false;
			logger.error("Email Sending failed to "+recipeintEmail+" "+DropUtil.getExceptionDescriptionString(e));
		} catch (Exception mex) {
			isEmailSent = false;
			if(templateFilePath.length() <= 0) {
				logger.error("Template file not found fot email type "+emailtype);
			} else {
				logger.error("Email Sending failed to "+recipeintEmail+" "+DropUtil.getExceptionDescriptionString(mex));
			}
		}
		if(isEmailSent) {
			logger.info("Email sent to "+recipeintEmail);
		}
	}
	
	private static class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(senderEmailID, senderPassword);
		}
	}

	public static Map<String, Object> createTemplateModel(String emailtype, Object object) {
		Map<String, Object> model = new HashMap<String, Object>();
		/*if (emailtype.equals(PropertiesFileReaderUtil.getVelocityTemplateProperties("resend.password"))) {
			ForgetPasswordDto forgetPasswordDto = (ForgetPasswordDto)object;
			model.put("forgetPasswordDto", forgetPasswordDto);
		}else if(emailtype.equals(PropertiesFileReaderUtil.getVelocityTemplateProperties("send.passcode"))){
			SendPasscodeDto sendPasscodeDto = (SendPasscodeDto)object;
			model.put("sendPasscodeDto", sendPasscodeDto);
		}*/
		return model;
	}

	public static String getTemplateFilePath(String emailtype) {
		logger.info("Inside getTemplateFilePath......");
		StringBuffer templateFilePath = new StringBuffer(templateFileFolder);
		templateFilePath.append("/");
		if (emailtype.equals(velocityConfig.getProperty("resend.password"))) {
			templateFilePath.append(velocityConfig.getProperty("resend.password.template.name"));
		}else if(emailtype.equals(velocityConfig.getProperty("send.passcode"))){
			templateFilePath.append(velocityConfig.getProperty("send.passcode.template.name"));
		}
		templateFilePath.append(".vm");
		logger.info("Exiting getTemplateFilePath......");
		return templateFilePath.toString();
	}

	
	public static VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	public static void setVelocityEngine(VelocityEngine velocityEngine) {
		EmailUtil.velocityEngine = velocityEngine;
	}

	public static String getTemplateFileFolder() {
		return templateFileFolder;
	}

	public static void setTemplateFileFolder(String templateFileFolder) {
		EmailUtil.templateFileFolder = templateFileFolder;
	}
}
