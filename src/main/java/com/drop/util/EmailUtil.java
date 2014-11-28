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

import com.drop.dto.ForgetPasswordDTO;
import com.drop.exception.DropException;

@Component
public class EmailUtil {
	
	private static final Logger logger = Logger.getLogger(EmailUtil.class);
	
	private static VelocityEngine velocityEngine;
	
	private static String templateFileFolder;	
	
	private static Properties emailConfig;
	
	private static Properties velocityConfig;
	
	@Autowired
	public EmailUtil(
			@Qualifier("velocityEngine") VelocityEngine velocityEngine,
			@Value("velocitytemplates") String templateFileFolder,
			@Qualifier("emailConfig") Properties emailConfig,
			@Qualifier("velocityConfig") Properties velocityConfig) {
		
		EmailUtil.velocityEngine = velocityEngine;
		EmailUtil.templateFileFolder = templateFileFolder;
		EmailUtil.emailConfig = emailConfig;
		EmailUtil.velocityConfig = velocityConfig;
	}
	
	/**
	 * Method to send email
	 * 
	 * @param subject
	 *            email subject
	 * @param recipeintEmail
	 * @param emailtype
	 *            based on the email type template file and model will be set
	 * @param object
	 *            Modal object for template file
	 * @param fileName
	 *            If there is any file for attachment
	 */
	public static void sendEmail(String subject, String recipeintEmail,
			String emailtype, Object object, String fileName) {

		logger.info("Sending Email to " + recipeintEmail);

		String senderEmailID = emailConfig.getProperty("mail.username");
		boolean isEmailSent = true;
		Map<String,Object> model = createTemplateModel(emailtype, object);
		String templateFilePath = getTemplateFilePath(emailtype);

		String messageBody = VelocityEngineUtils.mergeTemplateIntoString(
				velocityEngine, templateFilePath, model);

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
		// props.put("mail.smtp.socketFactory.port", emailServerPort);
		// props.put("mail.smtp.socketFactory.class",
		// "javax.net.ssl.SSLSocketFactory");
		// props.put("mail.smtp.socketFactory.fallback", "true");

		try {
			Authenticator auth = new SMTPAuthenticator();
			javax.mail.Session session = javax.mail.Session.getInstance(props,
					auth);

			MimeMessage message = new MimeMessage(session);
			message.setSubject(subject);
			message.setFrom(new InternetAddress(senderEmailID));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					recipeintEmail));
			message.setSentDate(new Date());

			if (fileName == null || fileName.length() <= 0) {
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
			logger.error("Email Sending failed to " + recipeintEmail + " "
					+ DropUtil.getExceptionDescriptionString(e));
		} catch (Exception mex) {
			isEmailSent = false;
			if (templateFilePath.length() <= 0) {
				logger.error("Template file not found fot email type "
						+ emailtype);
			} else {
				logger.error("Email Sending failed to " + recipeintEmail + " "
						+ DropUtil.getExceptionDescriptionString(mex));
			}
		}

		if (isEmailSent) {
			logger.info("Email sent to " + recipeintEmail);
		} else {
			throw new DropException();
		}
	}
	
	private static class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(
					emailConfig.getProperty("mail.username"),
					emailConfig.getProperty("mail.password"));
		}
	}

	/**
	 *  Set template model for email
	 * @param emailtype
	 * @param object
	 * @return
	 */
	public static Map<String, Object> createTemplateModel(String emailtype,
			Object object) {
		Map<String, Object> model = new HashMap<String, Object>();

		if (emailtype.equals(velocityConfig.getProperty("resend.password"))) {
			ForgetPasswordDTO forgetPasswordDTO = (ForgetPasswordDTO) object;
			model.put("forgetPasswordDTO", forgetPasswordDTO);
		}

		return model;
	}

	/** Method to create velocity template file path based on email type
	 * @param emailtype
	 * @return
	 */
	public static String getTemplateFilePath(String emailtype) {
		logger.info("Inside getTemplateFilePath......");

		StringBuffer templateFilePath = new StringBuffer(templateFileFolder);
		templateFilePath.append("/");

		if (emailtype.equals(velocityConfig.getProperty("resend.password"))) {
			templateFilePath.append(velocityConfig
					.getProperty("resend.password.template.name"));
		}

		templateFilePath.append(".vm");

		logger.info("Exiting getTemplateFilePath......");
		return templateFilePath.toString();
	}
}
