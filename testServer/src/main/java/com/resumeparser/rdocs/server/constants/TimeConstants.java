package com.resumeparser.rdocs.server.constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TimeConstants {
	public static class MONTHSFULL {
		public static final String JAN = "JANUARY";
		public static final String FEB = "FEBRUARY";
		public static final String MAR = "MARCH";
		public static final String APR = "APRIL";
		public static final String MAY = "MAY";
		public static final String JUN = "JUNE";
		public static final String JUL = "JULY";
		public static final String AUG = "AUGUST";
		public static final String SEP = "SEPTEMBER";
		public static final String OCT = "OCTOBER";
		public static final String NOV = "NOVEMBER";
		public static final String DEC = "DECEMBER";
		
		public static final List<String> MONTHSLIST = Collections.unmodifiableList(new ArrayList<String> () {
			{
				add(JAN);
				add(FEB);
				add(MAR);
				add(APR);
				add(MAY);
				add(JUN);
				add(JUL);
				add(AUG);
				add(SEP);
				add(OCT);
				add(NOV);
				add(DEC);
			}
		});
	}
	
	public static class MONTHSSHORT {
		public static final String JAN = "JAN";
		public static final String FEB = "FEB";
		public static final String MAR = "MAR";
		public static final String APR = "APR";
		public static final String MAY = "MAY";
		public static final String JUN = "JUN";
		public static final String JUL = "JUL";
		public static final String AUG = "AUG";
		public static final String SEP = "SEP";
		public static final String OCT = "OCT";
		public static final String NOV = "NOV";
		public static final String DEC = "DEC";
		
		public static final List<String> MONTHSLIST = Collections.unmodifiableList(new ArrayList<String> () {
			{
				add(JAN);
				add(FEB);
				add(MAR);
				add(APR);
				add(MAY);
				add(JUN);
				add(JUL);
				add(AUG);
				add(SEP);
				add(OCT);
				add(NOV);
				add(DEC);
			}
		});
	}
	
	public static class TIMEPERIOD {
		public static final String TILLDATE = "TILL DATE";
		public static final String TILL = "TILL";
		public static final String PRESENT = "PRESENT";
		public static final String CURRENT = "CURRENT";
		public static final String NOW = "NOW";
		public static final String CURRENTLY = "CURRENTLY";
		public static final List<String> PERIODSLIST = Collections.unmodifiableList(new ArrayList<String> () {
			{
				add(TILLDATE);
				add(TILL);
				add(PRESENT);
				add(CURRENT);
				add(NOW);
				add(CURRENTLY);
			}
		});
	}
}
