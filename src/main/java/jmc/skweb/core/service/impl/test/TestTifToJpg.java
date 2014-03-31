package jmc.skweb.core.service.impl.test;


	import java.io.File;
import java.io.FileInputStream;
	import java.io.FileOutputStream;
	import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
	import java.awt.image.RenderedImage;

import jmc.skweb.util.FormatUtil;



	import com.sun.image.codec.jpeg.JPEGCodec;
	import com.sun.image.codec.jpeg.JPEGImageEncoder;
	import com.sun.media.jai.codec.SeekableStream;
	import com.sun.media.jai.codec.FileSeekableStream;
	import com.sun.media.jai.codec.TIFFDecodeParam;
	import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.ImageCodec;

	public class TestTifToJpg {

	  public static void TiffToJpg(String tiff, String output)
	     throws IOException
	  {
	    File tiffFile = new File(tiff);
	    SeekableStream s = new FileSeekableStream(tiffFile);
	    TIFFDecodeParam param = null;
	    ImageDecoder dec = ImageCodec.createImageDecoder("tiff", s, param);
	    RenderedImage op = dec.decodeAsRenderedImage(0);
	    FileOutputStream fos = new FileOutputStream(output);
	    JPEGImageEncoder jpeg = JPEGCodec.createJPEGEncoder(fos);
	    jpeg.encode(op.getData());
	    fos.close();
	  }

	  
	  public static void main(String[] args) throws Exception {
		  
		  	byte[] cara = FormatUtil.convertJPG("c:/Cheques/00701010580558725200000183264.tif",0);
			
			byte[] contraCara = FormatUtil.convertJPG("c:/Cheques/00701010580558725200000183264.tif",1);
			
			byte[] cheque = FormatUtil.mergeJPG(cara, contraCara);
		 
			  try {
					Class.forName("net.sourceforge.jtds.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					
					e.printStackTrace();
				} 
			  Connection conn = null;
				try {
					conn = DriverManager.getConnection("jdbc:jtds:sqlserver://Localhost:1433/comunsql", "sa", "super");
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			  
			  try{			  
			  
				
			  byte[] image = cheque; 

			  

			  String sql = "insert into imagenes (imagen1,clave) values (?,18219862)"; 
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

