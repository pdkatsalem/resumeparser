package com.resumeparser.rdocs.server.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.resumeparser.rdocs.server.configuration.ConfManager;

public class FileUtil {
	private static final Logger LOGGER = Logger.getLogger(FileUtil.class.getName());
	
	public static String getTempFilePath(String fileName, Long fileId) throws Exception {
		try {
			String folder = ConfManager.getFileStoragePath();

			folder += "/"+TimeUtil.getCurrentTimeId();

			return folder+"/"+fileId+"/"+fileName;
		} catch (Exception exp) {
			LOGGER.log(Level.SEVERE, "Exception while getting temp file path for filename : "+fileName, exp);
			throw exp;
		}
	}
}
