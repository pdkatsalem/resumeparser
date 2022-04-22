package com.resumeparser.rdocs.server.util;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.resumeparser.rdocs.server.constants.SegmentConstants;

public class SegmentUtil {
	private static final Logger LOGGER = Logger.getLogger(SegmentUtil.class.getName());
	
	public static String getHeader(String text) {
		try {
			if(text.length() > 50) {
				return null;
			}
			
			Iterator<String> headerIter = SegmentConstants.HEADERS.STOPWORDS.iterator();
			
			while(headerIter.hasNext()) {
				String curHeader = headerIter.next();
				
				if(text.trim().replaceAll(":", "").trim().equalsIgnoreCase(curHeader)) {
					return curHeader;
				}
			}
			
			return null;
		}
		catch (Exception exp) {
			LOGGER.log(Level.SEVERE, "Exception while check if text is header", exp);
			throw exp;
		}
	}
}
