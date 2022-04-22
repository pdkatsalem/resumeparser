package com.resumeparser.rdocs.server.bean;

import java.io.File;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;

import com.resumeparser.rdocs.server.util.FileUtil;

public class FilesImpl implements FilesBean {
	private static final Logger LOGGER = Logger.getLogger(FilesImpl.class.getName());

	@Override
	public Long addImportFile(String fileName, File file) throws Exception {
		try {
			Long fileId = System.currentTimeMillis();
			
			File tempFile = new File(FileUtil.getTempFilePath(fileName, fileId));
			FileUtils.copyFile(file, tempFile);
			
			LOGGER.log(Level.INFO, FileUtil.getTempFilePath(fileName, fileId));
			
			return fileId;
		}
		catch (Exception exp) {
			LOGGER.log(Level.SEVERE, "Exception while adding file for parsing : ", exp);
			throw exp;
		}
	}

	@Override
	public Hashtable deleteImportFile(Long fileId) throws Exception {
		try {
			return new Hashtable<>();
		}
		catch (Exception exp) {
			LOGGER.log(Level.SEVERE, "Exception while deleting file : ", exp);
			throw exp;
		}
	}
}
