package com.resumeparser.rdocs.server.authFilter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.resumeparser.rdocs.server.util.DBUtil;


public class AuthFilter implements Filter
{
	private static final Logger LOGGER = Logger.getLogger(AuthFilter.class.getName());
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
    
    }
    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
    
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        Boolean isAuthReq  = false;
        Boolean isReqValid = true;
        Integer allowedHits = 0;
        String token = "";
        token = httpRequest.getHeader("AUTHTOKEN");
//        String token = httpRequest.getHeader("TOKEN");
////        JSONObject object=new JSONObject();
////        JSONObject;
////        object.put("roroo","ss");
       isAuthReq =  httpRequest.getRequestURI().toString().startsWith("/api/rparse") || httpRequest.getRequestURI().toString().startsWith("/api/files") ;
       if (isAuthReq) { 
       try {
			
			allowedHits  = DBUtil.isValidToken(token);
			if(allowedHits <= 0) {
				isReqValid = false ;
			}
			
			LOGGER.log(Level.INFO,"ALLOWED HITS ::::::" + allowedHits.toString() + " isreqvalid ::::" + isReqValid.toString());
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			
		}
       }
        
        
        
        LOGGER.log(Level.INFO,httpRequest.getRequestURI().toString() );
        
        
        if (isAuthReq && !isReqValid) {
        	String resMessage = "";
        	if (allowedHits == -1) {
        		resMessage= "Token invalid / pass valid token ";
        	}
        	else {
        		resMessage= "Your total no of hits count exceeds the purchase limit ";
        	}
        	LOGGER.log(Level.INFO,httpRequest.getRequestURI().toString() + "if con" );
        	resp.getOutputStream().print("{string data }");
            resp.sendError(401,resMessage);
            return;
        }
        else {
        	filterChain.doFilter(servletRequest,servletResponse);
        }
        
//        if(token!=null){
//        
//        }
        
    }
    
    @Override
    public void destroy()
    {
    
    }
}