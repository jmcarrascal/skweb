package jmc.skweb.core.service.impl.test;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestInsertSQLValue {

	/**
	 * @param args
	 */
	
	/**
	 * SqlServerBlobInsert.java
	 * Copyright (c) 2007 by Dr. Herong Yang. All rights reserved.
	 */
	
	  public static void main(String [] args) {

		  
		  try {
				Class.forName("net.sourceforge.jtds.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} 
		  Connection conn = null;
			try {
				conn = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/Comunsql", "sa", "super");
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		  
		  try{			  
		  
			File f = new File("C:\\Cheques\\testBMP1.bmp");
		  
		  FileInputStream in = new FileInputStream(f); 
		  byte[] image = new byte[(int) f.length()]; 

		  in.read(image); 

		  String sql = "update paseban set imagen2 = ? where transacnr = 18219862"; 
		  PreparedStatement stmt = conn.prepareStatement(sql); 
		   
		  stmt.setBytes(1, image); 
		  stmt.executeUpdate(); 
		  stmt.close(); 
		   
		  conn.close();
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  
	  }


}

