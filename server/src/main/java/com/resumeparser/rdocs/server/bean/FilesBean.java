package com.resumeparser.rdocs.server.bean;

import java.io.File;
import java.util.Hashtable;

public interface FilesBean {
	public Long addImportFile(String fileName, File file) throws Exception;
	
	public Hashtable deleteImportFile(Long fileId) throws Exception;
}
