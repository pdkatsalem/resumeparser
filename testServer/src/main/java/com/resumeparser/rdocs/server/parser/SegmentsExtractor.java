package com.resumeparser.rdocs.server.parser;

import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.resumeparser.rdocs.server.util.SegmentUtil;

public class SegmentsExtractor {
	private static final Logger LOGGER = Logger.getLogger(SegmentsExtractor.class.getName());
	
	public static Hashtable<String, String> parseFileSegments(Long fileId, String content) throws Exception {
		try {
			Hashtable<String, String> segmentDetails = new Hashtable<String, String>();
			
			String[] lines = content.split("\\r?\\n");
			
			String header = "";
			
			for(String line:lines) {
				String lineHeader;
				
				if((lineHeader = SegmentUtil.getHeader(line)) != null && !lineHeader.isBlank()) {
					header = lineHeader;
					LOGGER.log(Level.INFO, "HEADERS : "+header);
				} else if(header != null && !header.isBlank()) {
//					LOGGER.log(Level.INFO, line);
				}
			}
			
			return segmentDetails;
		} catch (Exception exp) {
			LOGGER.log(Level.SEVERE, "Exception while parsing segments in file for resume : "+fileId, exp);
			throw exp;
		}
	}
}
