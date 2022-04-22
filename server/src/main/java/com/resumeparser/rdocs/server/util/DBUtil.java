package com.resumeparser.rdocs.server.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBUtil
{
	private static final Logger LOGGER = Logger.getLogger(DBUtil.class.getName());
    static String connectionURL="jdbc:postgresql://localhost:5432/rparser";
    private static String dbUser="postgres";
    private static String dbPassword="postgres";
    
    public static void reduceHits(String token) throws SQLException{
    	Connection con =  getConnection();
    	try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("update userinfo set HITSUSED = HITSUSED +1 where apikey = ?");
            PreparedStatement preparedStatement = con.prepareStatement(stringBuilder.toString());
            preparedStatement.setString(1, token);
            preparedStatement.execute();
    	}
    	catch(Exception e) {
    		LOGGER.log(Level.SEVERE,"error in reducing the hits count" + token);
    	}
    	finally {
    		if (con!=null) {
    			con.close();
    		}
    	}
    }
    public static Connection getConnection() throws SQLException
    {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        Connection connection = DriverManager.getConnection(connectionURL, dbUser, dbPassword);
        return connection;
    }

    public static Integer isValidToken(String token) throws SQLException{
    	Connection connection = getConnection();
    	Integer res = 0 ;
    	try {
    		StringBuilder stringBuilder = new StringBuilder();
    		Integer allowedHits = 0 ;
    		Integer hitsUsed = 0 ;
    		LOGGER.log(Level.INFO,"getting the token from db " + token);
            stringBuilder.append("select APIKEY,HITSALLOWED,HITSUSED from userinfo where apikey = ?");
            PreparedStatement ps = connection.prepareStatement(stringBuilder.toString());
            ResultSet rs =null;
            ps.setString(1, token);
            rs = ps.executeQuery();
            if(rs.next()) {
            	allowedHits =  rs.getInt(2);
            	hitsUsed = rs.getInt(3);
            	allowedHits =  allowedHits - Math.abs(hitsUsed) ;
            }
            else {
            	allowedHits = -1;
            	res =-1;
            }
//            if (allowedHits > 0) {
            	res = allowedHits;
//            }
    	}
    	catch(Exception E){
    		LOGGER.log(Level.SEVERE,"Error in getting the token from db " + token);
    	}
    	finally {
    		 if (connection!=null){
                 connection.close();
             }

    	}
		return res;
    }
    
    
    
    public static void getKey() throws SQLException{
    	Connection connection = getConnection();
    	try {
    		
    	}
    	catch(Exception E){
    		
    	}
    	finally {
    		 if (connection!=null){
                 connection.close();
             }
    	}
    }
    
 
    public static void insert() throws SQLException
    {
        Connection connection = getConnection();
        try
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("INSERT INTO USERINFO (USERNAME,APIKEY,HITSALLOWED,HITSUSED) VALUES(?,?,?,?)");
            PreparedStatement preparedStatement = connection.prepareStatement(stringBuilder.toString());
            preparedStatement.setString(1, "user");
            preparedStatement.setString(2, "userAuthTOken");
            preparedStatement.setInt(3,100);
            preparedStatement.setInt(4, 0);
            preparedStatement.execute();
            
        }catch (Exception e){
        
        }finally
        {
            if (connection!=null){
                connection.close();
            }
        }
    
    }
    
}