package com.resumeparser.rdocs.server.bean;

import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.resumeparser.rdocs.server.fileparser.DocParser;
import com.resumeparser.rdocs.server.fileparser.PDFParser;
import com.resumeparser.rdocs.server.util.CommonUtil;
import com.resumeparser.rdocs.server.util.DataUtil;
import com.resumeparser.rdocs.server.util.FileUtil;
import com.resumeparser.rdocs.server.util.TimeUtil;

public class ParserImpl implements ParserBean {

	private static final Logger LOGGER = Logger.getLogger(ParserImpl.class.getName());
	
	@Override
	public Hashtable parseFile(Hashtable fileDetails) throws Exception {
		try {
			Hashtable resumeData = new Hashtable();
			
			Hashtable serverDetails = CommonUtil.getHashtableFromString(""+fileDetails.get("serverId"));
			String fileName = ""+fileDetails.get("fileName");
			String folderDetails = FileUtil.getTempFilePath(fileName, Long.parseLong(""+serverDetails.get("file_id")));
			
			if(fileName.endsWith(".pdf")) {
				resumeData = PDFParser.readPDFFile(folderDetails);
			}
			else if(fileName.endsWith(".doc")) {
				resumeData = DocParser.readDocFile(folderDetails);
			}
			else if(fileName.endsWith(".docx")) {
				resumeData = DocParser.readDocxFile(folderDetails);
			}
			
			if(resumeData.containsKey("dateOfBirth")) {
				TimeUtil.formatDate(""+resumeData.get("dateOfBirth"));
			}
			
			resumeData = DataUtil.fillEmptyValues(resumeData);
			
			resumeData.put("resumeName", fileName);
			
			LOGGER.log(Level.INFO, "Parsed resume : "+fileName+", Data : "+resumeData);
			
			return resumeData;
		}
		catch (Exception exp) {
			LOGGER.log(Level.SEVERE, "Exception while parsing resume : "+fileDetails, exp);
			throw exp;
		}
	}

}
