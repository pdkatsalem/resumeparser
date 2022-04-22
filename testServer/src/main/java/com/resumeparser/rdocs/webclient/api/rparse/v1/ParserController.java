package com.resumeparser.rdocs.webclient.api.rparse.v1;

import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.apache.struts2.rest.RestActionSupport;

import com.opensymphony.xwork2.ModelDriven;
import com.resumeparser.rdocs.server.bean.ParserBean;
import com.resumeparser.rdocs.server.bean.ParserImpl;
import com.resumeparser.rdocs.server.configuration.ConfManager;
import com.resumeparser.rdocs.server.fileparser.DocParser;
import com.resumeparser.rdocs.server.fileparser.PDFParser;
import com.resumeparser.rdocs.server.util.CommonUtil;
import com.resumeparser.rdocs.server.util.FileUtil;

public class ParserController extends RestActionSupport implements ModelDriven<Object>, ServletRequestAware, ServletResponseAware {
	private static final Logger LOGGER = Logger.getLogger(ParserController.class.getName());

	Hashtable responsedata;
	HttpServletRequest request;
	HttpServletResponse response;

	private Hashtable model = new Hashtable();
	private String id;
	
	public HttpHeaders parse() {
		responsedata = new Hashtable();
		
		try {
			ParserBean parserbean = new ParserImpl();
			
			responsedata.put("resumeData", parserbean.parseFile(model));
			responsedata.put("status", true);
		}
		catch (Exception exp) {
			LOGGER.log(Level.SEVERE, "Exception while parsing file : "+model, exp);
		}
		return new DefaultHttpHeaders("fileupload").disableCaching();
	}
	
	@Override
	public Hashtable getModel()
	{
		return (responsedata!=null?responsedata:model);
	}

	public Hashtable modelData() {
		return this.model;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getReq() {
		return this.request;
	}

	public void setId(String id){
		this.id=id;
	}
}
