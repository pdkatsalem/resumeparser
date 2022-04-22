package com.resumeparser.rdocs.server.parser;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.resumeparser.rdocs.server.constants.CommonConstants;

public class EmailExtractor {
	private static final Logger LOGGER = Logger.getLogger(EmailExtractor.class.getName());
	
	private static final Pattern EMAIL_REGEX =
		      Pattern.compile("[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}", Pattern.CASE_INSENSITIVE);
	
	public static String extractEmail(Long id, String text) throws Exception {
		try {
			String email = CommonConstants.STATUS.NOTAVAILABLE;
			
			Matcher matcher = EMAIL_REGEX.matcher(text);

		    if (matcher.find()) {
		      email = matcher.group(); 
		    }
		    
			return email;
		}
		catch (Exception exp) {
			LOGGER.log(Level.SEVERE, "Exception while parsing email from text for the resume - "+id, exp);
			throw exp;
		}
	}
}
