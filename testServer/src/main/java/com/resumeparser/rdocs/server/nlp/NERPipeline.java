package com.resumeparser.rdocs.server.nlp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import org.apache.commons.collections4.map.SingletonMap;

import com.resumeparser.rdocs.server.configuration.ConfManager;
import com.resumeparser.rdocs.server.constants.CommonConstants;
import com.resumeparser.rdocs.server.util.NERUtil;
import com.resumeparser.rdocs.server.util.TimeUtil;

import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.tokensregex.CoreMapExpressionExtractor;
import edu.stanford.nlp.ling.tokensregex.Env;
import edu.stanford.nlp.ling.tokensregex.MatchedExpression;
import edu.stanford.nlp.ling.tokensregex.NodePattern;
import edu.stanford.nlp.ling.tokensregex.TokenSequencePattern;
import edu.stanford.nlp.naturalli.NaturalLogicAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class NERPipeline {

	private static final Logger LOGGER = Logger.getLogger(NERPipeline.class.getName());

	public static Hashtable nlpTextContent(String content, Hashtable resumeDetails) throws Exception {
		try {
			Long ctime = System.currentTimeMillis();
			
			Hashtable parsedDetails = new Hashtable();

			Properties props = new Properties();

			props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,regexner,tokensregex");

			props.setProperty("ner.fine.regexner.ignorecase", "true");
			props.setProperty("ner.additional.regexner.ignorecase", "true");
			props.setProperty("regexner.mapping", ConfManager.getNERCollegeDegreesPath());
			props.setProperty("regexner.ignorecase", "true");

			props.setProperty("tokensregex.rules", ConfManager.getNERRulesPath());

			String[] rulesFiles = props.getProperty("tokensregex.rules").split(",");

			Env env = TokenSequencePattern.getNewEnv();

			env.setDefaultStringMatchFlags(NodePattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
			env.setDefaultStringPatternFlags(Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

			CoreMapExpressionExtractor extractor = CoreMapExpressionExtractor.createExtractorFromFiles(env, rulesFiles);

			StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

			Annotation annotation = new Annotation(content);
			pipeline.annotate(annotation);
			
			int nameTokenCount = 0;
			
			HashSet<String> educationalDetails = new HashSet<String>();
			HashSet<Hashtable> workProfiles = new HashSet<Hashtable>();
			HashSet<String> skillSets = new HashSet<String>();
			
			Hashtable curExperience = new Hashtable();
			String prekey = "";
			
			int sentenceTokens = 0;
			Hashtable workExperienceTotal = new Hashtable();
			
			ArrayList<SingletonMap<String, String>> nameTokenList = new ArrayList<SingletonMap<String,String>>();

			for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
				for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
					if(token.ner().equalsIgnoreCase("DEGREE")) {
//						System.out.println(token.word() + "\t" + token.ner() + "\t" + token.tag());
//						educationalDetails.add(token.word());
					}
					else if(token.ner().equalsIgnoreCase("ORGANIZATION")) {
//						System.out.println(token.word() + "\t" + token.ner() + "\t" + token.tag());
					}
					else if(token.ner().equalsIgnoreCase("SKILLSET")) {
//						System.out.println(token.word() + "\t" + token.ner() + "\t" + token.tag());
//						skillSets.add(token.word());
					}
					else if(token.ner().equalsIgnoreCase("DESIGNATION")) {
//						System.out.println(token.word() + "\t" + token.ner() + "\t" + token.tag());
//						skillSets.add(token.word());
					}
					else if(token.ner().equalsIgnoreCase("GIVEN_NAME")) {
//						System.out.println(token.word() + "\t" + token.ner() + "\t" + token.tag());
//						skillSets.add(token.word());
					}
					else if(token.ner().equalsIgnoreCase("COMPANY_GIVEN")) {
//						System.out.println(token.word() + "\t" + token.ner() + "\t" + token.tag());
//						skillSets.add(token.word());
					}
					System.out.println(token.word() + "\t-\t" + token.ner() + "\t-\t" + token.tag());
					
					if(nameTokenCount++ <= 20) {
						SingletonMap<String, String> tokenMap = 
									new SingletonMap<String, String>(token.word(), token.ner()+"-"+token.tag());
						
						nameTokenList.add(tokenMap);
						
//						System.out.println("---- " + token.word() + "\t" + token.ner() + "\t" + token.tag());
					}
				}
				
				List<MatchedExpression> matchedExpressions = extractor.extractExpressions(sentence);

				for (MatchedExpression me : matchedExpressions) {
					SingletonMap<String, String> valueDetails = parseRegexValue(me);
					
					if(valueDetails.containsKey("experience")) {
						if(!prekey.equalsIgnoreCase("experience")) {
							if(curExperience.containsKey("companyName") && 
									(curExperience.containsKey("timePeriod") || curExperience.containsKey("designation"))) {
								workProfiles.add(curExperience);
							}
							curExperience = new Hashtable();
							
							curExperience.put("companyName", valueDetails.getValue());
						} else {
							if(curExperience.containsKey("timePeriod") || curExperience.containsKey("designation")) {
								workProfiles.add(curExperience);
							}
							
							curExperience = new Hashtable();
							curExperience.put("companyName", valueDetails.getValue());
						}
						
						prekey = "experience";
					}
					else if(valueDetails.containsKey("degree")) {
						if(!educationalDetails.contains(valueDetails.getValue())) {
							educationalDetails.add(valueDetails.getValue());
						}
						
						prekey = "degree";
					}
					else if(valueDetails.containsKey("skillset")) {
						if(!skillSets.contains(valueDetails.getValue())) {
							skillSets.add(valueDetails.getValue());
						}
						
						prekey = "skillset";
					}
					else if(valueDetails.containsKey("time_period_work")) {
//						if(prekey.equalsIgnoreCase("experience")) {
						if(!curExperience.containsKey("timePeriod") ||
								(curExperience.containsKey("timePeriod") && curExperience.keySet().size() == 1)) {
							curExperience.put("timePeriod", TimeUtil.parseTimePeriodFromRegex(valueDetails.getValue()));
						} else if(curExperience.containsKey("companyName") && curExperience.containsKey("designation")) {
							workProfiles.add(curExperience);
							curExperience = new Hashtable();
						}
						
						prekey = "exp-tp";
					}
					else if(valueDetails.containsKey("designation")) {
//						if(prekey.equalsIgnoreCase("experience")) {
						if(!curExperience.containsKey("designation") || 
								(curExperience.containsKey("designation") && curExperience.keySet().size() == 1)) {
							curExperience.put("designation", valueDetails.getValue());
						} else if(curExperience.containsKey("companyName") && curExperience.containsKey("timePeriod")) {
							workProfiles.add(curExperience);
							curExperience = new Hashtable();
						}
						
						prekey = "exp-desg";
					}
					else if(!valueDetails.isEmpty()) {
						if((prekey.equalsIgnoreCase("experience") || 
									prekey.equalsIgnoreCase("exp-tp") || 
									prekey.equalsIgnoreCase("exp-desg"))
								&& (curExperience.containsKey("timePeriod") || curExperience.containsKey("designation"))) {
							workProfiles.add(curExperience);
							curExperience = new Hashtable();
						}
						
						prekey = "";
						parsedDetails.putAll(valueDetails);
					}
					else {
						prekey = "";
					}
				}
			}
			
			if(!curExperience.isEmpty() && (curExperience.containsKey("timePeriod") || curExperience.containsKey("designation"))) {
				workProfiles.add(curExperience);
				curExperience = new Hashtable();
			}
			
			parsedDetails.remove("unknown");
			
			if(educationalDetails.size() > 0) {
				parsedDetails.put("educationalDetails", educationalDetails);
			}
			
			if(workProfiles.size() > 0) {
				parsedDetails.put("workProfiles", workProfiles);
				
				Hashtable workTotalExp = TimeUtil.getWorkExperience(workProfiles);
				
				if(workTotalExp == null || workTotalExp.isEmpty()) {
					parsedDetails.put("totalWorkExperience", CommonConstants.STATUS.NOTAVAILABLE);
				} else {
					parsedDetails.put("totalWorkExperience", TimeUtil.getWorkExperience(workProfiles));
				}
			}
			
			if(skillSets.size() > 0) {
				parsedDetails.put("skillSets", skillSets);
			}
			
			SingletonMap<String, String> candidateName = NERUtil.parseNameFromTokens(nameTokenList, ""+resumeDetails.get("emailId"));
			
			if(!candidateName.isEmpty() && !parsedDetails.containsKey("candidateName")) {
				parsedDetails.put("candidateName", candidateName.getKey());
//				parsedDetails.put("nameAccuracy", candidateName.getValue());
			}
			
//			parsedDetails.put("accuracy", NERUtil.getAccuracy(parsedDetails));
			
			System.out.println("Total Time taken for parsing : "+(System.currentTimeMillis()-ctime)+"ms");
			
			return parsedDetails;
		}
		catch (Exception exp) {
			LOGGER.log(Level.SEVERE, "Exception at NERPipeline while parsing", exp);
			throw exp;
		}
	}

	private static SingletonMap<String, String> parseRegexValue(MatchedExpression expression) throws Exception {
		try {
			String expressionText = expression.getText();
			String key = "unknown";
			String value = expressionText;

			if(expressionText.contains(":")) {
				expressionText = expressionText.replaceAll(":", "").replaceAll("-", "");
			}
			
			Pattern pattern;
			
			SingletonMap<String, String> keyMap;

			if(expression.getValue().get().equals("DEGREE_RESULT")
					&& !value.equals("be") && !value.equalsIgnoreCase("been")
					&& !value.equalsIgnoreCase("Most") && !value.equalsIgnoreCase("Made")
					&& !value.equalsIgnoreCase("become")) {
				key = "degree";
				value = expressionText;
				System.out.println("DEG : "+value);
			}
			else if(expression.getValue().get().equals("ORG_RESULT") && NERUtil.isOrganization(expressionText)) {
				key = "experience";
//				value = expressionText;
				
				String[] values = value.split("\\n");
				value = values[values.length - 1];
				
				System.out.println("ORG_VAL: "+value);
			}
			else if(expression.getValue().get().equals("ORG_RESULT_CNF")) {
				key = "experience";
				value = expressionText;
				
				System.out.println("ORG_VAL (Confirmed) : "+value);
			}
			else if(expression.getValue().get().equals("SKILLSET_RESULT")) {
				key = "skillset";
				value = expressionText;
				
//				System.out.println("SKILLS : "+value);
			}
			//TODO -- Check for incorrect values returned and then define the algorithm
//			else if(expression.getValue().get().equals("NAME_PERSONAL")) {
//				key = "candidate_name";
//				
//				pattern = Pattern.compile("(name)", Pattern.CASE_INSENSITIVE);
//				value = pattern.matcher(expressionText).replaceAll("").replaceAll("\n", "").replaceAll("\t", "").trim();
//			}
			else if(expression.getValue().get().equals("FATHER_NAME")) {
				key = "fatherName";
				
				pattern = Pattern.compile("(father([\\W\\D\\S]{0,1}s)?( )?name)", Pattern.CASE_INSENSITIVE);
				value = pattern.matcher(expressionText).replaceAll("").replaceAll("\n", "").replaceAll("\t", "").trim();
			} else if(expression.getValue().get().equals("MOTHER_NAME")) {
				key = "motherName";
				
				pattern = Pattern.compile("(mother([\\W\\D\\S]{0,1}s)?( )?name)", Pattern.CASE_INSENSITIVE);
				value = pattern.matcher(expressionText).replaceAll("").replaceAll("\n", "").replaceAll("\t", "").trim();
			} else if(expression.getValue().get().equals("DATE_OF_BIRTH_FULL")) {
				key = "dateOfBirth";
				
				pattern = Pattern.compile("((date|d)(.|\\s)?(of|o)(.|\\s)?(birth|b))|(birth(\\s)?(date|day))", Pattern.CASE_INSENSITIVE);
				value = pattern.matcher(expressionText)
						.replaceAll("")
						.replaceAll("\n", "")
						.replaceAll("\t", "")
						.replaceAll("Current", "")
						.replaceAll("Present", "")
						.trim();
			} else if(expression.getValue().get().equals("NATIONALITY_GIVEN")) {
				key = "nationality";
				
				pattern = Pattern.compile("(nationality)", Pattern.CASE_INSENSITIVE);
				value = pattern.matcher(expressionText).replaceAll("").replaceAll("\n", "").replaceAll("\t", "").trim();
			} else if(expression.getValue().get().equals("MARITAL_STATUS")) {
				key = "maritalStatus";
				
				pattern = Pattern.compile("(marital(\\s)?status)", Pattern.CASE_INSENSITIVE);
				value = pattern.matcher(expressionText).replaceAll("").replaceAll("\n", "").replaceAll("\t", "").trim();
			} else if(expression.getValue().get().equals("DURATION_PERIOD")) {
				key = "time_period_work";
				
				value = expressionText;
				System.out.println("Duration : "+value);
			} else if(expression.getValue().get().equals("NAME_GIVEN_PERSONAL")) {
				key = "candidateName";
				
				value = expressionText;
				System.out.println("Name Given : "+value);
			} else if(expression.getValue().get().equals("DESIGNATION_GIVEN")) {
				key = "designation";
				
				value = expressionText;
				System.out.println("Designation Given : "+value);
			}

			keyMap = new SingletonMap<String, String>(key, value);
			
			return keyMap;
		}
		catch (Exception exp) {
			LOGGER.log(Level.SEVERE, "Exception while parsing matched expression : "+expression, exp);
			throw exp;
		}
	}
}