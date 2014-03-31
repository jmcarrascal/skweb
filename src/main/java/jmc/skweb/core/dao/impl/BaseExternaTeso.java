package jmc.skweb.core.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseExternaTeso {
	
public static void saveImage(String clave, int parte, byte[] image, String servidor, String base, String usr, String pass, String tabla,
		
		String campoPrimaryKey ){
		clave = clave.trim();	
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} 
	  Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:jtds:sqlserver://"+ servidor +":1433/" + base, usr, pass);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	  boolean hagoInsert = false;
	  try{
    		String sql = "select count(*) as total from " + tabla + " where " + campoPrimaryKey + " like '%"+ clave +"%'";
			ResultSet rs;
			Statement stmt;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				if (rs.getInt("total") == 0){
					hagoInsert = true;
				}
			}

			rs.close();
			stmt.close();

	  }catch(SQLException se){
		  se.printStackTrace();
	  }
	  
	  try{			  
	  String sql = "";
	  if(hagoInsert){
		  if(parte == 0){
			  sql = "insert into " + tabla + " (imagen1," + campoPrimaryKey  + " ) values (?, '"+ clave +"')";
		  }
		  if(parte == 1){
			  sql = "insert into " + tabla + " (imagen2," + campoPrimaryKey  + " ) values (?, '"+ clave +"')";
		  }
	  }else{
		  if(parte == 0){
			  sql = "update " + tabla + " set imagen1 = ? where " + campoPrimaryKey  + " = '"+ clave +"'";
		  }
		  if(parte == 1){
			  sql = "update " + tabla + " set imagen2 = ? where " + campoPrimaryKey  + " = '"+ clave +"'";
		  }
	  }
		  
		   
	  PreparedStatement stmt = conn.prepareStatement(sql); 
	   
	  stmt.setBytes(1, image); 
	  stmt.executeUpdate(); 
	  stmt.close(); 
	   
	  conn.close();
	  }catch(Exception e){
		  e.printStackTrace();
	  }
	  
}

public static byte[] getImageCheque(String chequeNr, int parte,String servidor, String base, String usr, String pass, String tabla,
		String campoPrimaryKey){
	try {
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		
		e.printStackTrace();
	} 
  Connection conn = null;
	try {
		conn = DriverManager.getConnection("jdbc:jtds:sqlserver://"+ servidor +":1433/" + base, usr, pass);
	} catch (SQLException e) {
		
		e.printStackTrace();
	}

		byte[] img = null;
		  try{
			  String sql = "";
	    		if (parte == 0){
	    			sql = "select imagen1 as imagen from "+ tabla +" where " + campoPrimaryKey  + "  = '" + chequeNr+ "'";	
	    		}
	    		if (parte == 1){
	    			sql = "select imagen2 as imagen from "+ tabla +" where " + campoPrimaryKey  + "  = '" + chequeNr+ "'";	
	    		}
			  	
				ResultSet rs;
				Statement stmt;
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);

				if (rs.next()) {
					img = rs.getBytes("imagen");
				}

				rs.close();
				stmt.close();

		  }catch(SQLException se){
			  se.printStackTrace();
		  }
		  return img;
		  

}
}

