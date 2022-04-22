package com.resumeparser.rdocs.server.parser;

import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SocialProfilesExtractor {
	private static final Logger LOGGER = Logger.getLogger(SocialProfilesExtractor.class.getName());

	private static final Pattern URL_PATTERN_REGEX = Pattern.compile("(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
			+ "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
			+ "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
			Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

	public static Hashtable<String, String> parseSocialProfiles(Long fileId, String content) throws Exception {
		try {
			Hashtable<String, String> socialProfiles = new Hashtable<String, String>();

			Matcher matcher = URL_PATTERN_REGEX.matcher(content);

			int index = 1;
			
			if (matcher.find()) {
				String url = matcher.group();
				
				if(url.contains("linkedin")) {
					socialProfiles.put("linkedinProfile", url);
				}
				else if(url.contains("facebook")) {
					socialProfiles.put("facebookProfile", url);
				}
				else if(url.contains("twitter")) {
					socialProfiles.put("twitterProfile", url);
				}
				else if(url.contains("github")) {
					socialProfiles.put("githubProfile", url);
				}
				else if(url.contains("gitlab")) {
					socialProfiles.put("gitlabProfile", url);
				}
				else if(url.contains("blog")) {
					socialProfiles.put("blogProfile", url);
				}
				else if(url.contains("indeed")) {
					socialProfiles.put("indeedProfile", url);
				}
				else {
					socialProfiles.put("otherProfile"+index++, url);
				}
			}

			return socialProfiles;
		}
		catch (Exception exp) {
			LOGGER.log(Level.SEVERE, "Exception while parsing social profiles for resume : "+fileId, exp);
			throw exp;
		}
	}
}
