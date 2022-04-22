package com.resumeparser.rdocs.server.constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SegmentConstants {
	public static class HEADERS {
		public static final String OBJECTIVE = "OBJECTIVE";
		public static final String CAREEROBJECTIVE = "CAREER OBJECTIVE";
		public static final String CAREEROBJECTIVES = "CAREER OBJECTIVES";
		public static final String PROFESSIONALSTATEMENT = "PROFESSIONAL STATEMENT";
		public static final String SUMMARY = "SUMMARY";
		public static final String PROFILESUMMARY = "PROFILE SUMMARY";
		public static final String PROFESSIONALSUMMARY = "PROFESSIONAL SUMMARY";
		public static final String PROFESSIONALOBJECTIVE = "PROFESSIONAL OBJECTIVE";
		public static final String PERSONALSTATEMENT = "PERSONAL STATEMENT";
		
		public static final String EDUCATION = "EDUCATION";
		public static final String ACADEMICDETAILS = "ACADEMIC DETAILS";
		public static final String ACADEMICPROFILE = "ACADEMIC PROFILE";
		public static final String ACADEMICRECORD = "ACADEMIC RECORD";
		public static final String EDUCATIONALQUALIFICATION = "EDUCATIONAL QUALIFICATION";
		public static final String QUALIFICATIONS = "QUALIFICATIONS";
		public static final String QUALIFICATION = "QUALIFICATION";
		public static final String EDUCATIONALBACKGROUND = "EDUCATIONAL BACKGROUND";
		public static final String EDUCATIONALCREDENTIALS = "EDUCATIONAL CREDENTIALS";
		public static final String EDUCATIONALDETAILS = "EDUCATIONAL DETAILS";
		
		public static final String EXPERIENCE = "EXPERIENCE";
		public static final String EXPERIENCEDETAILS = "EXPERIENCE DETAILS";
		public static final String WORKEXPERIENCE = "WORK EXPERIENCE";
		public static final String PROFESSIONALEXPERIENCE = "PROFESSIONAL EXPERIENCE";
		public static final String EMPLOYMENTRECORD = "EMPLOYMENT RECORD";
		public static final String EMPLOYMENTHISTORY = "EMPLOYMENT HISTORY";
		public static final String EMPLOYMENT = "EMPLOYMENT";
		public static final String WORKHISTORY = "WORK HISTORY";
		public static final String PROFESSIONALEXPOSURE = "PROFESSIONAL EXPOSURE";
		public static final String PROFESSIONALSNAP_SHOT = "PROFESSIONAL SNAP SHOT";
		public static final String PROFESSIONALSNAPSHOT = "PROFESSIONAL SNAPSHOT";
		public static final String CAREERSUMMARY = "CAREER SUMMARY";
		
		public static final String SKILLS = "SKILLS";
		public static final String TOPSKILLS = "TOP SKILLS";
		public static final String KEYSKILLS = "KEY SKILLS";
		public static final String SKILLSETS = "SKILLSETS";
		public static final String SKILLSET = "SKILLSET";
		public static final String MYSPECIALITIES = "MY SPECIALITIES";
		public static final String TECHNICALSKILLS = "TECHNICAL SKILLS";
		public static final String SOFTWARESKILLS = "SOFTWARE SKILLS";
		public static final String DESIGNSKILLS = "DESIGN SKILLS";
		public static final String PERSONALSKILLS = "PERSONAL SKILLS";
		public static final String PROFESSIONALSKILLS = "PROFESSIONAL SKILLS";
		public static final String CORECOMPETENCIES = "CORE COMPETENCIES";
		public static final String TECHNICALPROFICIENCY = "TECHNICAL PROFICIENCY";
		
		public static final String TOOLS = "TOOLS";
		public static final String PRODUCTEXPERTISE = "PRODUCTEXPERTISE";
		
		public static final String LANGUAGES = "LANGUAGES";
		public static final String LANGUAGESKILLS = "LANGUAGE SKILLS";
		public static final String LANGUAGESKNOWN = "LANGUAGES KNOWN";
		
		public static final String STRENGTHS = "STRENGTHS";
		public static final String STRENGTH = "STRENGTH";
		public static final String PERSONALSTRENGTHS = "PERSONAL STRENGTHS";
		public static final String PERSONALSTRENGTH = "PERSONAL STRENGTH";
		public static final String PERSONALITYTRAITS= "PERSONALITY TRAITS";
		
		public static final String PERSONALPROFILE = "PERSONAL PROFILE";
		public static final String PERSONALDETAILS = "PERSONAL DETAILS";
		public static final String PERSONALDOSSIER = "PERSONAL DOSSIER";
		public static final String PERSONALINFORMATION = "PERSONAL INFORMATION";
		
		public static final String INTEREST = "INTEREST";
		public static final String INTERESTS = "INTERESTS";
		public static final String AREASOFINTEREST = "AREAS OF INTEREST";
		public static final String FIELDOFINTEREST = "FIELD OF INTEREST";
		public static final String AREAOFEXPERTISE = "AREA OF EXPERTISE";
		
		public static final String PROJECTS = "PROJECTS";
		public static final String PROJECTSUNDERTAKEN = "PROJECTS UNDERTAKEN";
		
		public static final String CERTIFICATION = "CERTIFICATION";
		public static final String CERTIFICATIONS = "CERTIFICATIONS";
		public static final String TRAINING = "TRAINING";
		
		public static final String RESPONSIBILITIES = "RESPONSIBILITIES";
		
		public static final String CONTACT = "CONTACT";
		
		public static final String REFERENCES = "REFERENCES";
		public static final String REFERENCE = "REFERENCE";
		public static final String WORKREFERENCES = "WORK REFERENCES";
		public static final String WORKREFERENCE = "WORK REFERENCE";
		public static final String CHARACTERREFERENCES = "CHARACTER REFERENCES";
		public static final String CHARACTERREFERENCE = "CHARACTER REFERENCE";
		
		public static final String DECLRATION = "DECLRATION";
		
		public static final List<String> SKILLSETHEADERS = Collections.unmodifiableList(new ArrayList<String>()
		{
			{
				add(SKILLS);
				add(TOPSKILLS);
				add(MYSPECIALITIES);
				add(KEYSKILLS);
				add(TECHNICALSKILLS);
				add(SKILLSET);
				add(SKILLSETS);
				add(SOFTWARESKILLS);
				add(PERSONALSKILLS);
				add(PROFESSIONALSKILLS);
				add(CORECOMPETENCIES);
				add(TECHNICALPROFICIENCY);
				add(DESIGNSKILLS);
			}
		});
		
		public static final List<String> STOPWORDS = Collections.unmodifiableList(new ArrayList<String>()
		{
			{
				add(OBJECTIVE);
				add(CAREEROBJECTIVE);
				add(CAREEROBJECTIVES);
				add(PROFESSIONALSTATEMENT);
				add(PERSONALSTATEMENT);
				add(SUMMARY);
				add(PROFESSIONALSUMMARY);
				add(PROFILESUMMARY);
				
				add(EDUCATION);
				add(ACADEMICDETAILS);
				add(ACADEMICPROFILE);
				add(EDUCATIONALQUALIFICATION);
				add(EDUCATIONALBACKGROUND);
				add(EDUCATIONALDETAILS);
				add(EDUCATIONALCREDENTIALS);
				add(QUALIFICATIONS);
				add(QUALIFICATION);
				add(ACADEMICRECORD);
				
				add(EXPERIENCE);
				add(EXPERIENCEDETAILS);
				add(WORKEXPERIENCE);
				add(PROFESSIONALEXPERIENCE);
				add(EMPLOYMENTRECORD);
				add(EMPLOYMENTHISTORY);
				add(WORKHISTORY);
				add(PROFESSIONALEXPOSURE);
				add(PROFESSIONALSNAPSHOT);
				add(PROFESSIONALSNAP_SHOT);
				add(CAREERSUMMARY);
				
				add(SKILLS);
				add(TOPSKILLS);
				add(MYSPECIALITIES);
				add(KEYSKILLS);
				add(TECHNICALSKILLS);
				add(SKILLSET);
				add(SKILLSETS);
				add(SOFTWARESKILLS);
				add(PERSONALSKILLS);
				add(PROFESSIONALSKILLS);
				add(CORECOMPETENCIES);
				add(TECHNICALPROFICIENCY);
				add(DESIGNSKILLS);
				
				add(TOOLS);
				add(PRODUCTEXPERTISE);
				
				add(LANGUAGES);
				add(LANGUAGESKILLS);
				add(LANGUAGESKNOWN);
				
				add(STRENGTHS);
				add(STRENGTH);
				add(PERSONALSTRENGTHS);
				add(PERSONALSTRENGTH);
				add(PERSONALITYTRAITS);
				
				add(PERSONALPROFILE);
				add(PERSONALDETAILS);
				add(PERSONALDOSSIER);
				
				add(INTEREST);
				add(INTERESTS);
				add(AREASOFINTEREST);
				add(AREAOFEXPERTISE);
				add(FIELDOFINTEREST);
				
				add(PROJECTS);
				add(PROJECTSUNDERTAKEN);
				
				add(CERTIFICATION);
				add(CERTIFICATIONS);
				add(TRAINING);
				
				add(RESPONSIBILITIES);
				
				add(CONTACT);
				
				add(REFERENCES);
				add(REFERENCE);
				add(WORKREFERENCES);
				add(WORKREFERENCE);
				add(CHARACTERREFERENCE);
				add(CHARACTERREFERENCES);
				
				add(DECLRATION);
			}
		});
	}
}
