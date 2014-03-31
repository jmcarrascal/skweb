package jmc.skweb.core.service.impl.test;

import java.net.URL;
import java.sql.*;

import jmc.skweb.util.DateUtil;
import jmc.skweb.util.FormatUtil;


public class TestMDBJoin {



	

	  static Connection theConn;

	  public static void main (String args[]) {
	    try {
	      // connection to an ACCESS MDB
	      theConn = getConnMDB("");

	      ResultSet rs;
	      Statement stmt;
	      String sql;

	      sql =  "SELECT Stock.IvaGravado, Items.Precio, Items.Cant1, Items.TransacNr, " +
	      		 "Gente.Cuit, Transac.Fecha, Transac.TipoComprob, Transac.NrComprob, " +
	      		 "Transac.Prefijo, Transac.Vencim1, Transac.Total, Impuestos.Alicuota FROM (Transac INNER JOIN ((Stock INNER JOIN Items ON Stock.Clave = Items.Articulo) INNER JOIN [c:/Simpa/bd2.mdb].Gente ON Items.GenteNr = Gente.GenteNr) ON (Gente.GenteNr = Transac.GenteNr) AND (Transac.TransacNr = Items.TransacNr)) INNER JOIN Impuestos ON Stock.IvaGravado = Impuestos.Nr WHERE (((Items.TransacNr)=1001))";
	      
	      stmt = theConn.createStatement();
	      rs = stmt.executeQuery(sql);
	      
			Double total = 0d;
			Double iva10 = 0d;
			Double iva21 = 0d;
			Double baseImp10 = 0d;
			Double baseImp21 = 0d;
			Double totalIVA = 0d;
			while (rs.next()) {
				Double totalUnit = rs.getDouble("precio")
						* rs.getDouble("cant1");
				Double alicouta = rs.getDouble("Alicuota");
				if (alicouta.equals(21d)) {
					iva21 = iva21 + (totalUnit * 21d / 100);
					baseImp21 = baseImp21 + totalUnit;
				}
				if (alicouta.equals(10.5d)) {
					iva10 = iva10 + (totalUnit * 10.5d / 100);
					baseImp10 = baseImp10 + totalUnit;
				}
				System.out.println(totalUnit);

				total = total + totalUnit;

			}
			totalIVA = iva21 + iva10;

			//Redondeo
			total = FormatUtil.redondearEn2(total);
			iva10 = FormatUtil.redondearEn2(iva10);
			iva21 = FormatUtil.redondearEn2(iva21);
			baseImp10 = FormatUtil.redondearEn2(baseImp10);
			baseImp21 = FormatUtil.redondearEn2(baseImp21);
			totalIVA = FormatUtil.redondearEn2(totalIVA);			
			
			System.out.println("Total " + total);
			System.out.println("Total IVA 21: " + iva21 + " Base21: " +baseImp21);
			System.out.println("Total IVA 10.5: " + iva10+ " Base10: " +baseImp10);
			System.out.println("Total Bruto:  " + (total + iva21 + iva10));

	      rs.close();
	      stmt.close();
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    finally {
	      try {
	        if (theConn != null) theConn.close();
	      }
	      catch (Exception e) {
	      }
	    }

	    }
	  

	
		public static Connection getConnMDB(String rutaBase) {
			Boolean useSQL = true; 
			if (useSQL){
		        String direccion = "jdbc:jtds:sqlserver://"+"190.30.228.138"+":1433/"+"ArrHerr";
		        try {
					Class.forName("net.sourceforge.jtds.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					
					e.printStackTrace();
				} 
		        Connection conn = null;
				try {
					conn = DriverManager.getConnection(direccion, "felec", "afip");
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
		        try {
					conn.getAutoCommit();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
		        return conn;
				
			}
			   return null;
			}
	
}
