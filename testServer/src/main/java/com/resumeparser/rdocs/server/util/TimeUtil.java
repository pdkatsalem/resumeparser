package com.resumeparser.rdocs.server.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.resumeparser.rdocs.server.constants.CommonConstants;
import com.resumeparser.rdocs.server.constants.TimeConstants;

public class TimeUtil {

	private static final Logger LOGGER = Logger.getLogger(TimeUtil.class.getName());

	private static final Pattern TIME_PREIOD_REGEX =
			Pattern.compile("(((january|february|march|april|may|june|july|august|september|october|november|december"
					+ "|jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec)."
					+ "[0-9]{4})"
					+ "|(present|till date|till|currently|current|now)$|"
					+ "[0-9]{4}."
					+ "(january|february|march|april|may|june|july|august|september|october|november|december"
					+ "|jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec)"
					+ "|[0-9]{4})", Pattern.CASE_INSENSITIVE);

	private static final Pattern YEAR_REGEX =
			Pattern.compile("[0-9]{4}", Pattern.CASE_INSENSITIVE);

	private static final Pattern END_REGEX =
			Pattern.compile("(present|till date|till|currently|current|now)", Pattern.CASE_INSENSITIVE);

	private static final Pattern MONTH_REGEX =
			Pattern.compile("(january|february|march|april|may|june|july|august|september|october|november|december"
					+ "|jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec)", Pattern.CASE_INSENSITIVE);

	public static String getCurrentTimeId() throws Exception {
		try {
			Date curdate = new Date();
			Calendar calendar = Calendar.getInstance();

			calendar.setTime(curdate);

			return (calendar.get(Calendar.DATE)+""+calendar.get(Calendar.MONTH)+""+calendar.get(Calendar.YEAR));
		}
		catch (Exception exp) {
			LOGGER.log(Level.SEVERE, "Exception while getting current date as id ", exp);
			throw exp;
		}
	}

	public static String formatDate(String unparsedDate) throws Exception {
		try {
			String parsedDate = CommonConstants.STATUS.NOTAVAILABLE;

			int formatPredict = predictDateFormat(unparsedDate);

			return parsedDate;
		}
		catch (Exception exp) {
			LOGGER.log(Level.SEVERE, "Exception while parsing date for : "+unparsedDate, exp);
			throw exp;
		}
	}

	private static Integer predictDateFormat(String unparsedDate) throws Exception {
		try {
			int type = 0;

			type = CommonUtil.isArrayElementInString(unparsedDate, TimeConstants.MONTHSFULL.MONTHSLIST);

			if(type == 0) {
				type = 10 + CommonUtil.isArrayElementInString(unparsedDate, TimeConstants.MONTHSSHORT.MONTHSLIST);
			}

			return type;
		}
		catch (Exception exp) {
			LOGGER.log(Level.SEVERE, "Exception while predicting date format for : "+unparsedDate, exp);
			throw exp;
		}
	}

	public static Hashtable getWorkExperience(HashSet<Hashtable> totalExperience) throws Exception {
		try {
			Hashtable workExp = new Hashtable();

			int totalMonths = 0;
			int totalYears = 0;

			Iterator<Hashtable> expIter = totalExperience.iterator();

			while(expIter.hasNext()) {
				Hashtable curExp = expIter.next();

				int sYear;
				int sMonth;
				int eYear;
				int eMonth;

				if(curExp.containsKey("timePeriod")) {
					Hashtable timePeriod = (Hashtable) curExp.get("timePeriod");

					if(!timePeriod.isEmpty()) {
						sMonth = Integer.parseInt(""+timePeriod.get("startMonth"));
						sYear = Integer.parseInt(""+timePeriod.get("startYear"));

						eYear = Integer.parseInt(""+timePeriod.get("endYear"));
						eMonth = Integer.parseInt(""+timePeriod.get("endMonth"));

						totalYears += eYear - sYear;
						if(sMonth > eMonth) {
							totalMonths += eMonth - sMonth + 12;
							totalYears--;
						} else {
							totalMonths += eMonth - sMonth;
						}
					}
				}
			}

			if(totalMonths >= 12) {
				totalYears += totalMonths/12;
				totalMonths = totalMonths % 12;
			}

			workExp.put("years", totalYears);
			workExp.put("months", totalMonths);

			return workExp;
		}
		catch (Exception exp) {
			LOGGER.log(Level.SEVERE, "Exception while computing total work experience", exp);
			throw exp;
		}
	}

	public static Hashtable parseTimePeriod(String curExp) throws Exception {
		try {
			Hashtable workExp = new Hashtable();

			if(curExp.isEmpty() || curExp.isBlank() || curExp == null) {
				return null;
			}

			System.out.println("Time Period for parsing : "+curExp);

			String[] expParsed = curExp.split("-");

			if(expParsed.length < 2) {
				expParsed = curExp.split("to");
			}

			if(expParsed.length < 2) {
				expParsed = curExp.split("\n");
			}

			if(expParsed.length < 2) {
				expParsed = curExp.split("–");
			}

			if(expParsed.length < 2) {
				expParsed = curExp.split("till");
			}

			if(expParsed.length < 2) {
				return null;
			}

			String startDate = expParsed[0].trim();
			String endDate = expParsed[1].trim();

			int sMonth = 0;
			int sYear = 0;
			int eMonth = 0;
			int eYear = 0;

			int sformat = predictDateFormat(startDate);

			if(sformat == 1 || sformat == 2 || sformat == 11 || sformat == 12) {
				String[] sDateArr = startDate.split(" ");

				if(sformat == 2 || sformat == 12) {
					if(sformat == 2) {
						sMonth = TimeConstants.MONTHSFULL.MONTHSLIST.indexOf(sDateArr[0]);
					} else {
						sMonth = TimeConstants.MONTHSSHORT.MONTHSLIST.indexOf(sDateArr[0]);
					}

					sYear = Integer.parseInt(sDateArr[sDateArr.length-1]);
				}
				else {
					if(sformat == 1) {
						sMonth = TimeConstants.MONTHSFULL.MONTHSLIST.indexOf(sDateArr[1]);
					} else if(sformat == 11) {
						sMonth = TimeConstants.MONTHSSHORT.MONTHSLIST.indexOf(sDateArr[1]);
					}

					sYear = Integer.parseInt(sDateArr[0]);
				}
			}

			int eformat = predictDateFormat(endDate);

			int isPresent = CommonUtil.isArrayElementInString(endDate, TimeConstants.TIMEPERIOD.PERIODSLIST);

			if((eformat == 1 || eformat == 2 || eformat == 11 || eformat == 12) && isPresent == 0) {
				String[] eDateArr = endDate.split(" ");

				if(eformat == 2 || eformat == 12) {
					if(eformat == 2) {
						eMonth = TimeConstants.MONTHSFULL.MONTHSLIST.indexOf(eDateArr[0]);
					} else {
						eMonth = TimeConstants.MONTHSSHORT.MONTHSLIST.indexOf(eDateArr[0]);
					}

					eYear = Integer.parseInt(eDateArr[1]);
				}
				else {
					eMonth = Integer.parseInt(eDateArr[1]);

					if(eformat == 1) {
						eMonth = TimeConstants.MONTHSFULL.MONTHSLIST.indexOf(eDateArr[1]);
					} else if(eformat == 11) {
						eMonth = TimeConstants.MONTHSSHORT.MONTHSLIST.indexOf(eDateArr[1]);
					}

					eYear = Integer.parseInt(eDateArr[0]);
				}
			}
			else {
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());

				eMonth = cal.get(Calendar.MONTH);
				eYear = cal.get(Calendar.YEAR);
			}

			workExp.put("startMonth", sMonth);
			workExp.put("startYear", sYear);
			workExp.put("endMonth", eMonth);
			workExp.put("endYear", eYear);

			LOGGER.log(Level.INFO, "Parsed time period : "+workExp);

			return workExp;
		}
		catch (Exception exp) {
			LOGGER.log(Level.SEVERE, "Exception while parsing work experience : "+curExp, exp);
			throw exp;
		}
	}

	public static Hashtable parseTimePeriodFromRegex(String curExpUp) throws Exception {
		try {
			String curExp = curExpUp.replaceAll("\\n", "").replaceAll("\\t", "");
			
			Hashtable workExp = new Hashtable();

			if(curExp.isEmpty() || curExp.isBlank() || curExp == null) {
				return workExp;
			}

			int sMonth = -1;
			int sYear = -1;
			int eMonth = -1;
			int eYear = -1;

			System.out.println("Time Period for parsing : "+curExp);

			ArrayList<String> ranges = new ArrayList<String>();

			Matcher matcher = TIME_PREIOD_REGEX.matcher(curExp);

			while (matcher.find()) {
				ranges.add(matcher.group());
			} 

			if(ranges.size() != 2) {
				return workExp;
			}

			String start = ranges.get(0);
			String end = ranges.get(1);

			matcher = YEAR_REGEX.matcher(start);
			sYear = matcher.find()?Integer.parseInt(matcher.group()):Integer.parseInt("-1");
			start = matcher.replaceAll("");

			matcher = MONTH_REGEX.matcher(start);

			String monthString = matcher.find()?matcher.group():"";

			if(monthString == "") {
				sMonth = -1;
			}
			else if(monthString.length() == 3) {
				sMonth = TimeConstants.MONTHSSHORT.MONTHSLIST.indexOf(monthString.toUpperCase());
			}
			else {
				sMonth = TimeConstants.MONTHSFULL.MONTHSLIST.indexOf(monthString.toUpperCase());
			}
			
			if(END_REGEX.matcher(end).find()) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				
				eMonth = cal.get(Calendar.MONTH);
				eYear = cal.get(Calendar.YEAR);
			}
			else {
				matcher = YEAR_REGEX.matcher(end);

				String yearString = matcher.find()?matcher.group():"";

				if(yearString == "") {
					eYear = -1;
				}
				else {
					eYear = Integer.parseInt(yearString);
				}

				matcher = MONTH_REGEX.matcher(end);

				monthString = matcher.find()?matcher.group():"";

				if(monthString == "") {
					eMonth = -1;
				}
				else if(monthString.length() == 3) {
					eMonth = TimeConstants.MONTHSSHORT.MONTHSLIST.indexOf(monthString.toUpperCase());
				}
				else {
					eMonth = TimeConstants.MONTHSFULL.MONTHSLIST.indexOf(monthString.toUpperCase());
				}
			}

			workExp.put("startMonth", sMonth);
			workExp.put("startYear", sYear);
			workExp.put("endMonth", eMonth);
			workExp.put("endYear", eYear);

			System.out.println("Time Period parsed : "+workExp);

			return workExp;
		}
		catch (Exception exp) {
			LOGGER.log(Level.SEVERE, "Exception while parsing time period for work experience : "+curExpUp, exp);
			throw exp;
		}
	}
}
