package com.resumeparser.rdocs.webclient.api.files.v1;

import java.io.File;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.URI;
import java.net.http.*;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.dispatcher.multipart.UploadedFile;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.apache.struts2.rest.RestActionSupport;

import com.opensymphony.xwork2.ModelDriven;
import com.resumeparser.rdocs.server.bean.FilesBean;
import com.resumeparser.rdocs.server.bean.FilesImpl;
import com.resumeparser.rdocs.server.bean.ParserBean;
import com.resumeparser.rdocs.server.bean.ParserImpl;
import com.resumeparser.rdocs.server.util.DBUtil;

public class FilesController extends RestActionSupport implements ModelDriven<Object>, ServletRequestAware, ServletResponseAware {
	private static final Logger LOGGER = Logger.getLogger(FilesController.class.getName());

	Hashtable responsedata;
	HttpServletRequest request;
	HttpServletResponse response;

	private Hashtable model = new Hashtable();
	private String id;
	
	public HttpHeaders create() {
		responsedata = new Hashtable();
		try {
			LOGGER.log(Level.INFO, "Model data : "+model);
			
			responsedata.put("status", true);
		}
		catch (Exception exp) {
			LOGGER.log(Level.INFO, "File upload started");
		}
		return new DefaultHttpHeaders("create").disableCaching();
	}
	
	public HttpHeaders fileupload() {
		responsedata = new Hashtable();
		
		try {
			FilesBean filebean = new FilesImpl();
			String requestMethod = request.getMethod();
			
			if(requestMethod.equalsIgnoreCase("POST")) {

				MultiPartRequestWrapper requestwrap = (MultiPartRequestWrapper) request;
				UploadedFile uFile = requestwrap.getFiles("fileUploader")[0];

				String fileName = requestwrap.getFileNames("fileUploader")[0];
				
				LOGGER.log(Level.INFO, "File upload started");
				
				responsedata.put("file_id", filebean.addImportFile(fileName, (File) uFile.getContent()));
				ParserBean parserbean = new ParserImpl();
				Hashtable<String, String> filesMap = new Hashtable<String, String>();
				filesMap.put("serverId", "{\"status\":\"success\",\"file_id\":" + responsedata.get("file_id").toString() + "}");
				filesMap.put("fileName", fileName);
				responsedata.put("resumeData", parserbean.parseFile(filesMap));
				DBUtil.reduceHits(request.getHeader("AUTHTOKEN"));
				responsedata.put("remainingHits", DBUtil.isValidToken(request.getHeader("AUTHTOKEN")));
//				String[] urlSplit = request.getRequestURL().toString().split("/");
//				String serverID = urlSplit[urlSplit.length-2];
//				StringBuilder queryUrl = new StringBuilder(request.getRequestURL().toString().replaceFirst(request.getRequestURI().toString(), ""));
//				queryUrl.append("/api/rparse/v1/parser/" + serverID + "/parse.json");
//				
//				post(queryUrl.toString(), "{\\\"serverId\\\":\\\"{\\\\\\\"status\\\\\\\":\\\\\\\"success\\\\\\\",\\\\\\\"file_id\\\\\\\":" + responsedata.get("file_id") + "}\\\",\\\"fileName\\\":\\\"" + fileName + "\\\"}",request.getHeader("AUTHTOKEN"));
				responsedata.put("status", "success");
			}
			else if(requestMethod.equalsIgnoreCase("DELETE")) {
				LOGGER.log(Level.INFO, "File Deletion request "+model.get("file_id"));
				
				responsedata.put("status", filebean.deleteImportFile(Long.parseLong(""+model.get("file_id"))));
				responsedata.put("status", "success");
			}
		}
		catch (Exception exp) {
			LOGGER.log(Level.SEVERE, "Exception while uploading file for parsing", exp);
		}
		return new DefaultHttpHeaders("fileupload").disableCaching();
	}
	
	@Override
	public Hashtable getModel()
	{
		return (responsedata!=null?responsedata:model);
	}
	
	public void post(String uri, String data,String header) throws Exception {
		LOGGER.log(Level.INFO, "url" + uri);
		LOGGER.log(Level.INFO, "data" + data);
	    HttpClient client = HttpClient.newBuilder().build();
	    HttpRequest request = HttpRequest.newBuilder()
	            .uri(URI.create(uri))
	            .POST(BodyPublishers.ofString(data))
	            .setHeader("AUTHTOKEN", header)
	            .build();

	    HttpResponse<?> response = client.send(request, BodyHandlers.discarding());
	    System.out.println(response.statusCode());
	    System.out.println(response.body());
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
