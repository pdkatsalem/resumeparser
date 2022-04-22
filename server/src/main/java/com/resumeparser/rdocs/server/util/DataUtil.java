package com.resumeparser.rdocs.server.util;

import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.resumeparser.rdocs.server.constants.CommonConstants;

public class DataUtil {
	private static final Logger LOGGER = Logger.getLogger(DataUtil.class.getName());
	
	public static Hashtable fillEmptyValues(Hashtable resumeDetails) {
		Hashtable resumeData = new Hashtable();
		
		if(!resumeDetails.containsKey("candidateName")) {
			resumeData.put("candidateName", CommonConstants.STATUS.NOTAVAILABLE);
		}
		
		if(!resumeDetails.containsKey("fatherName")) {
			resumeData.put("fatherName", CommonConstants.STATUS.NOTAVAILABLE);
		}
		
		if(!resumeDetails.containsKey("motherName")) {
			resumeData.put("motherName", CommonConstants.STATUS.NOTAVAILABLE);
		}
		
		if(!resumeDetails.containsKey("gender")) {
			resumeData.put("gender", CommonConstants.STATUS.NOTAVAILABLE);
		}
		
		if(!resumeDetails.containsKey("emailId")) {
			resumeData.put("emailId", CommonConstants.STATUS.NOTAVAILABLE);
		}
		
		if(!resumeDetails.containsKey("phoneNumber")) {
			resumeData.put("phoneNumber", CommonConstants.STATUS.NOTAVAILABLE);
		}
		
		if(!resumeDetails.containsKey("maritalStatus")) {
			resumeData.put("maritalStatus", CommonConstants.STATUS.NOTAVAILABLE);
		}
		
		if(!resumeDetails.containsKey("nationality")) {
			resumeData.put("nationality", CommonConstants.STATUS.NOTAVAILABLE);
		}
		
		if(!resumeDetails.containsKey("dateOfBirth")) {
			resumeData.put("dateOfBirth", CommonConstants.STATUS.NOTAVAILABLE);
		}
		
		if(!resumeDetails.containsKey("skillSets")) {
			resumeData.put("skillSets", CommonConstants.STATUS.NOTAVAILABLE);
		}
		
		if(!resumeDetails.containsKey("workProfiles")) {
			resumeData.put("workProfiles", CommonConstants.STATUS.NOTAVAILABLE);
		}
		
		if(!resumeDetails.containsKey("educationalDetails")) {
			resumeData.put("educationalDetails", CommonConstants.STATUS.NOTAVAILABLE);
		}
		
		if(!resumeDetails.containsKey("socialProfiles")) {
			resumeData.put("socialProfiles", CommonConstants.STATUS.NOTAVAILABLE);
		}
		
		if(!resumeDetails.containsKey("totalWorkExperience")) {
			resumeData.put("totalWorkExperience", CommonConstants.STATUS.NOTAVAILABLE);
		}
		
		resumeData.putAll(resumeDetails);
		
		if(resumeDetails.containsKey("candidateName")) {
			
			String name = resumeDetails.get("candidateName").toString();
			
			if(name.equalsIgnoreCase("name")) {
				resumeData.put("candidateName", CommonConstants.STATUS.NOTAVAILABLE);
			}
		}
		
		return resumeData;
	}
}
