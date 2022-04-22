package com.resumeparser.rdocs.server.parser;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.resumeparser.rdocs.server.constants.CommonConstants;

public class GenderExtractor {
	private static final Logger LOGGER = Logger.getLogger(GenderExtractor.class.getName());

	private static final Pattern GENDER_REGEX = Pattern.compile("([^a-z]|^)(fe){0,1}male{1}([^a-z])", Pattern.CASE_INSENSITIVE);
	
	public static String parseGender(Long fileId, String content) throws Exception {
		try {
			String gender = CommonConstants.STATUS.NOTAVAILABLE;

			Matcher matcher = GENDER_REGEX.matcher(content);

		    if (matcher.find()) {
		      gender = matcher.group(); 
		    }
			
			if(gender.toLowerCase().contains("female")) {
				gender = "Female";
			}
			else if(gender.toLowerCase().contains("male")) {
				gender = "Male";
			}

			return gender;
		}
		catch (Exception exp) {
			LOGGER.log(Level.SEVERE, "Exception while parsing gender for resume : "+fileId, exp);
			throw exp;
		}
	}
}
