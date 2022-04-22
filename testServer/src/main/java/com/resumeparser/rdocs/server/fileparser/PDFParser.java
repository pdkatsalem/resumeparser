package com.resumeparser.rdocs.server.fileparser;

import java.io.File;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.resumeparser.rdocs.server.parser.EmailExtractor;
import com.resumeparser.rdocs.server.parser.GenderExtractor;
import com.resumeparser.rdocs.server.parser.PhoneNumberExtractor;
import com.resumeparser.rdocs.server.parser.SocialProfilesExtractor;

public class PDFParser{

	private static final Logger LOGGER = Logger.getLogger(PDFParser.class.getName());

	public static Hashtable readPDFFile(String fileName) throws Exception {
		PDDocument document = null;
		Hashtable parsedData = new Hashtable();

		try {
			Long fileId = 0L;

			document = PDDocument.load(new File(fileName));

			if (!document.isEncrypted()) {
				PDFTextStripper stripper = new PDFTextStripper();
//				stripper.setSortByPosition(true);

				int pageCount = document.getPages().getCount();

				String text = "";

				if(pageCount > 2) {
//					stripper.setEndPage(1);

					text = stripper.getText(document);

					//TODO -- Add logic to check the last page and if not found check the previous one 

					//					stripper.setStartPage(pageCount-1);
					//					stripper.setEndPage(pageCount);
					//					
					//					text += stripper.getText(document);
				} 
				else {
					text = stripper.getText(document);
				}

//				parsedData.putAll(NERPipeline.nerFromString(text));
				
				parsedData.put("emailId", EmailExtractor.extractEmail(fileId, text));
				parsedData.put("phoneNumber", PhoneNumberExtractor.parsePhoneNumber(fileId, text));
				parsedData.put("socialProfiles", SocialProfilesExtractor.parseSocialProfiles(fileId, text));
				parsedData.put("gender", GenderExtractor.parseGender(fileId, text));
				
				parsedData.putAll(com.resumeparser.rdocs.server.nlp.NERPipeline.nlpTextContent(text, parsedData));
				
//				parsedData.put("segments", SegmentsExtractor.parseFileSegments(fileId, text));

				//				BasicPipelineExample.parseFromFile(text.trim());
			}
		}
		catch (Exception exp) {
			LOGGER.log(Level.SEVERE, "Exception while parsing PDF", exp);
		}
		finally {
			document.close();
		}

		return parsedData;
	}
}
