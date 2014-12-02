package com.drop.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.drop.exception.DropException;

public class DropUtil {
	
	private static final Logger logger = Logger.getLogger(DropUtil.class);

	/*
	 * Method returns the stack trace of exception in string format. Used for
	 * logging of exception.
	 */
	public static String getExceptionDescriptionString(Exception e) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		e.printStackTrace(printWriter);
		return stringWriter.toString();
	}
	
	/* Method will return comma separated string of Errors
	 * @param result
	 * @return
	 */
	public static String getErrorString(BindingResult result) {
		StringBuilder builder = new StringBuilder("");
        List<FieldError> fieldErrors = result.getFieldErrors();
        int size = fieldErrors.size();
        int counter = 0;
        
        for (FieldError fieldError : fieldErrors) {
        	 counter++;
	         builder.append(fieldError.getDefaultMessage());
	         if(counter < size) {
	        	 builder.append(",");
	         }
        }
        return builder.toString();   
	}
	
	public static String getIPAddress(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		return ipAddress;
	}
	
	/**
	 * Utility method to convert a String to date
	 * 
	 * @param str
	 *            Date string to convert
	 * @param formatString
	 *            format like 'MM-dd-yyyy'
	 * @return
	 * @throws ParseException
	 */
	public static Date convertStringToDate(String str, String formatString) {
		Date date = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat(formatString);
			date = (Date) dateFormat.parse(str);
		} catch (ParseException e) {
			logger.fatal(getExceptionDescriptionString(e));
			throw new DropException();
		}
		return date;
	}
}
