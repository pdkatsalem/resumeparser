package com.resumeparser.rdocs.server.configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfManager {

	private static final Logger LOGGER = Logger.getLogger(ConfManager.class.getName());
	
	private static String serverHome = null;
    private static String webAppsConfPath = null;
    
	static
	{
		serverHome = System.getProperty("catalina.base");
		if(serverHome.startsWith("/home/ubuntu") || serverHome.startsWith("/root/")) {
			webAppsConfPath = serverHome + File.separator + "webapps" + File.separator + "ROOT" + File.separator + "WEB-INF" + File.separator + "conf";
		}
		else {
		    webAppsConfPath = "/Users/anand-3214/eclipse-workspace/resumeparser/src/main/webapp/WEB-INF/conf";
        }
	}

	private static String serverConfFile = webAppsConfPath + File.separator + "conf.properties";
	
	private static final Map CONFIG = Collections.unmodifiableMap(loadProperties(serverConfFile));
	
	private static String filePath = ""+CONFIG.get("filePath");

	public static Map loadProperties(String file)
	{
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		Map propmap=new LinkedHashMap();

		try
		{
			String line = null;
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis, "UTF-8");
			br = new BufferedReader(isr);
			while (( line = br.readLine()) != null)
			{
				if(line.startsWith("#")) //For removing Comments
				{
					continue;
				}

				String[] strobj=line.split("=",2);
				if(strobj.length>1)
				{
					propmap.put(strobj[0], strobj[1]);
				}
			}
		}
		catch(Exception exp)
		{
			LOGGER.log(Level.SEVERE, "File not found : Please check the file directory ", exp);
		}
		finally
		{
			try{fis.close();}catch(Exception e1){}
			try{isr.close();}catch(Exception e2){}
			try{br.close();}catch(Exception e3){}
		}
		return propmap;
	}

	public static String getFileStoragePath() {
		return filePath;
	}
	
	public static String getNERCollegeDegreesPath() {
		String files = webAppsConfPath + File.separator + "college-degrees.txt";
		files += ", " + webAppsConfPath + File.separator + "skillset-common.txt";
		files += ", " + webAppsConfPath + File.separator + "names-common.txt";
		files += ", " + webAppsConfPath + File.separator + "designation-common.txt";
		files += ", " + webAppsConfPath + File.separator + "companies-common.txt";
		
		return files;
	}
	
	public static String getNERRulesPath() {
		return webAppsConfPath + File.separator + "ner_custom.rules";
	}
}
