package com.drop.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class DropUtil {

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
}
