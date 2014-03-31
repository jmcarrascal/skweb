package jmc.skweb.core.service.impl.test;

import java.io.File;
import java.io.IOException;

import jmc.skweb.util.FileUtil;

public class TestCopyFolder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean termino = true;
		if (termino){
			
			File carpetaFechas = new File("//192.168.0.166//Compartir//20110830//");
			//File carpetaFechas = new File("c://cot//");
			File carpetaLeida = new File(carpetaFechas.getAbsoluteFile() + "_read");
			System.gc();
			System.out.println(carpetaFechas.canExecute());
			System.out.println(carpetaFechas.canRead());
			System.out.println(carpetaFechas.canWrite());
			System.out.println(carpetaFechas.delete());
			System.out.println(carpetaFechas.renameTo(carpetaLeida));
			File f = new File("//192.168.0.166//Compartir//20110830_1");
			try{
			if(f.mkdir())
			System.out.println("Directory Created");
			else
			System.out.println("Directory is not created");
			}catch(Exception e){
				e.printStackTrace();
			}
			File fDelete = new File("\\\\192.168.0.166\\Compartir\\20110830\\18219862.tif");
			//System.out.println(fDelete.delete());
			System.out.println(deleteDir(fDelete.getAbsoluteFile()));
			
			

			
		}


	}
	public static boolean deleteDir(File dir) {
	    System.out.println(dir.isDirectory());
	    System.out.println(dir.isFile());
	    System.out.println(dir.isAbsolute());
	    System.out.println(dir.isHidden());
		//if (dir.isDirectory()) {
	        dir.renameTo(new File(dir.getAbsoluteFile() + "_juan.tif"));
	    //}
	    return true;
	}

}
