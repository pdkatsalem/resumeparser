package com.resumeparser.rdocs.server.fileparser;

import java.io.File;
import java.io.FileInputStream;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Logger;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import com.resumeparser.rdocs.server.nlp.NERPipeline;
import com.resumeparser.rdocs.server.parser.EmailExtractor;
import com.resumeparser.rdocs.server.parser.GenderExtractor;
import com.resumeparser.rdocs.server.parser.PhoneNumberExtractor;
import com.resumeparser.rdocs.server.parser.SocialProfilesExtractor;

public class DocParser {

	private static final Logger LOGGER = Logger.getLogger(DocParser.class.getName());
	
	public static Hashtable readDocFile(String fileName) throws Exception {
		FileInputStream fis = null;
		Hashtable parsedData = new Hashtable();
		Long fileId = 0l;
		
		try {
			File file = new File(fileName);
			fis = new FileInputStream(file.getAbsolutePath());

			HWPFDocument document = new HWPFDocument(fis);
			WordExtractor extractor = new WordExtractor(document);

			String[] paragraphs = extractor.getParagraphText();

			System.out.println("Total no of paragraph "+paragraphs.length);
			
			String text = "";
			for (String para : paragraphs) {
				text += para+"\n";
			}
			
			parsedData.put("emailId", EmailExtractor.extractEmail(fileId, text));
			parsedData.put("phoneNumber", PhoneNumberExtractor.parsePhoneNumber(fileId, text));
			parsedData.put("socialProfiles", SocialProfilesExtractor.parseSocialProfiles(fileId, text));
			parsedData.put("gender", GenderExtractor.parseGender(fileId, text));
			
			parsedData.putAll(NERPipeline.nlpTextContent(text, parsedData));
			
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		finally {
			fis.close();
		}

		return parsedData;
	}

	public static Hashtable readDocxFile(String fileName) throws Exception {
		FileInputStream fis = null;
		Hashtable parsedData = new Hashtable();
		
		Long fileId = 0l;
		
		try {
			File file = new File(fileName);
			fis = new FileInputStream(file.getAbsolutePath());

			XWPFDocument document = new XWPFDocument(fis);
			
			XWPFWordExtractor extractor = new XWPFWordExtractor(document);

			List<XWPFParagraph> paragraphs = document.getParagraphs();
			List<XWPFTable> tables = document.getTables();
			
			String text = "";
			String extractedContent = "";
			
			System.out.println("Total no of paragraph "+paragraphs.size()+", tables : "+tables.size());
			
			for (XWPFParagraph para : paragraphs) {
				text += para.getText().trim() + "\n";
			}
			
			for(XWPFTable table:tables) {
				text += table.getText().trim() + "\n";
			}
			
			extractedContent = extractor.getText();
			text = extractedContent;
			
			parsedData.put("emailId", EmailExtractor.extractEmail(fileId, text));
			parsedData.put("phoneNumber", PhoneNumberExtractor.parsePhoneNumber(fileId, text));
			parsedData.put("socialProfiles", SocialProfilesExtractor.parseSocialProfiles(fileId, text));
			parsedData.put("gender", GenderExtractor.parseGender(fileId, text));
			
			parsedData.putAll(NERPipeline.nlpTextContent(text, parsedData));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			fis.close();
		}
		
		return parsedData;
	}
}
