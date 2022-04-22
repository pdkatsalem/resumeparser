package com.resumeparser.rdocs.server.parser;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.i18n.phonenumbers.PhoneNumberMatch;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.resumeparser.rdocs.server.constants.CommonConstants;

public class PhoneNumberExtractor {
	private static final Logger LOGGER = Logger.getLogger(PhoneNumberExtractor.class.getName());

	private static final Pattern PHONE_NUMBER_REGEX = Pattern.compile("\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})");

	public static String parsePhoneNumber(Long id, String content) throws Exception {
		try {
			PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

			String parsedPhoneNumber = CommonConstants.STATUS.NOTAVAILABLE;
			String[] lines = content.split("\\r?\\n");

			for (String line : lines) {
				Iterable<PhoneNumberMatch> numbers = phoneNumberUtil.findNumbers(line, null);

				if (numbers.iterator().hasNext()) {
					PhoneNumberMatch phoneNumberMatch = numbers.iterator().next();

					String phoneNumber = phoneNumberMatch.rawString();
					String cleanPhoneNumber = cleanUp(phoneNumber);

					parsedPhoneNumber = cleanPhoneNumber;
					
					return parsedPhoneNumber;
				} else {
					if(!(parsedPhoneNumber = extractPhoneNumberRegex(id, line)).equals(CommonConstants.STATUS.NOTAVAILABLE)) {
						return parsedPhoneNumber;
					}
				}
			}
			
			return parsedPhoneNumber;
		}
		catch (Exception exp) {
			LOGGER.log(Level.SEVERE, "Exception while extracting phone number for the resume : "+id, exp);
			throw exp;
		}
	}

	private static String cleanUp(String phoneNumber) {
		if (phoneNumber == null) return "";
		return phoneNumber.replaceAll("[^+0-9]", "");
	}

	private static String extractPhoneNumberRegex(Long id, String content) throws Exception {
		try {
			String phoneNumber = CommonConstants.STATUS.NOTAVAILABLE;

//			String[] lines = content.split("\\r?\\n");

//			for (String line : lines) {
				Matcher matcher = PHONE_NUMBER_REGEX.matcher(content.replaceAll(" ", ""));
				
				if (matcher.find()) {
					phoneNumber = matcher.group();
				}
//			}

			return phoneNumber;
		}
		catch (Exception exp) {
			LOGGER.log(Level.SEVERE, "Exception while parsing phone number from text using regex for the resume - "+id, exp);
			throw exp;
		}
	}
}
