package jmc.skweb.util.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import jmc.skweb.util.FileUtil;


public class TestConvertFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Obtengo un byte[] a partir de un File
		try {
			byte[] byteTest = FileUtil.getBytesFromFile(new File("c://test.pdf"));
			System.out.println(byteTest.length);
			//File file = FileUtil.getFileFromByteArray(byteTest, "c://testB.pdf");
			//System.out.println(file.length());
			//FileUtil.copy(file, new File("c://testA.pdf"));
			
			System.out.println(System.getProperty("java.io.tmpdir"));
			writeFile(byteTest,System.getProperty("java.io.tmpdir") + "testB.pdf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	private static File convert(byte[] array){
		FileOutputStream fileInt = null;
		try {
			fileInt = new FileOutputStream(new File("test.pdf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			fileInt.write(array);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//File file = fileInt.;
		return null;
	}

	/**
	 * Solo para pruebas
	 * @param archivo
	 * @deprecated
	 */
	private static void writeFile(byte[] archivo, String nombre){		
		FileOutputStream archivoNuevo=null;		
		try {
			archivoNuevo = new FileOutputStream(nombre);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			archivoNuevo.write(archivo);
			archivoNuevo.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
