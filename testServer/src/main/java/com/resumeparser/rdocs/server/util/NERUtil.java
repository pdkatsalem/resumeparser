package com.resumeparser.rdocs.server.util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections4.map.SingletonMap;

public class NERUtil {
	private static final Logger LOGGER = Logger.getLogger(NERUtil.class.getName());

	private static final Pattern ORG_REGEX =
			Pattern.compile(".+(((technologies)|(solutions)|(corp)|(tech)|(corporation)|(communications)|(inc)|(limited)|(ltd)|(services)|(systems))(\\.){0,1})$",
			Pattern.CASE_INSENSITIVE);

	public static Boolean isOrganization(String orgText) {
		Boolean isOrg = false;

		try {
			Matcher matcher = ORG_REGEX.matcher(orgText);

			if(matcher.find()) {
				isOrg = true;
			}
		}
		catch (Exception exp) {
			LOGGER.log(Level.SEVERE, "Exception while checking if text is org for : "+orgText, exp);
		}

		return isOrg;
	}

	public static SingletonMap<String, String> parseNameFromTokens(ArrayList<SingletonMap<String, String>> tokenList, String email) {
		SingletonMap<String, String> nameDetails;

		try {
			String key = "";
			String value = "0";

			if(email.isBlank()) {
				return null;
			}

			int accuracy = 0;
			
			Boolean cont = false;

			for(SingletonMap<String, String> tokenMap: tokenList) {
				String[] tokenSplit = tokenMap.getKey().toLowerCase().split("\\.");
				String ner = tokenMap.getValue().split("-")[0];
				String tag = tokenMap.getValue().split("-")[1];

				for(String token: tokenSplit) {
					if(email.contains(token) && 
							(ner.equals("O") || ner.equals("PERSON") || ner.equals("MISC")) && 
							(tag.equals("NN") || tag.equals("NNP")) &&
							token.length() > 3)
					{
						key += tokenMap.getKey() + " ";
						accuracy += 30;
						cont = true;
					} else if(ner.equals("PERSON") && !token.equalsIgnoreCase("email") && !token.equalsIgnoreCase("mob")) {
						key +=tokenMap.getKey() +" ";
						accuracy += 25;
						cont = true;
					} else {
						cont = false;
					}
				}
				
				if(!cont && !key.isBlank()) {
					break;
				}
			}

			if(key.isBlank()) {
				key = "name";
			}

			value = ""+accuracy;

			nameDetails = new SingletonMap<String, String>(key.trim(), value);

			return nameDetails;
		}
		catch (Exception exp) {
			LOGGER.log(Level.SEVERE, "Exception while parsing name from list of tokens", exp);
		}

		return null;
	}

	public static int getAccuracy(Hashtable<String, String> resumeData) {
		int accuracy = 0;



		return accuracy;
	}
}
