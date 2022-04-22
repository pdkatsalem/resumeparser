package com.resumeparser.rdocs.server.util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommonUtil {
	private static final Logger LOGGER = Logger.getLogger(CommonUtil.class.getName());
	
	public static Hashtable getHashtableFromString(String hashData) throws Exception {
		Hashtable hashDetails = new Hashtable();
		try {
			if(hashData.startsWith("{") && hashData.endsWith("}")) {
				hashData = hashData.replace("{", "");
				hashData = hashData.replace("}", "");
				
				String[] datas = hashData.split(",");
				
				for(String data:datas) {
					String[] dataval = data.split(":");
					
					hashDetails.put(dataval[0].replaceAll("\"", ""), dataval[1].replaceAll("\"", ""));
				}
			}
			
			LOGGER.log(Level.INFO, "Parsed hash details : "+hashDetails);
		}
		catch (Exception exp) {
			LOGGER.log(Level.SEVERE, "Exception while getting hash details from string for data : "+hashData, exp);
			throw exp;
		}
		return hashDetails;
	}
	
	public static Integer isArrayElementInString(String element, List<String> contents) throws Exception {
		try {
			int isContained = 0;
			
			for(String curString:contents) {
				if(element.toLowerCase().contains(curString.toLowerCase())) {
					isContained++;
					
					if(element.toLowerCase().startsWith(curString.toLowerCase())) {
						isContained++;
					}
				}
			}
			
			return isContained;
		}
		catch (Exception exp) {
			LOGGER.log(Level.SEVERE, "Exception while checking if array element is in string : " + element, exp);
			throw exp;
		}
	}
}
