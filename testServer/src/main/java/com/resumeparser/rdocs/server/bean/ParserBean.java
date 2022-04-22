package com.resumeparser.rdocs.server.bean;

import java.util.Hashtable;

public interface ParserBean {
	public Hashtable parseFile(Hashtable fileDetails) throws Exception;
}
