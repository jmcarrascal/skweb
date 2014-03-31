package jmc.skweb.core.service.impl.test;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class TestExcelFile {


	/**
	 * Ejemplo sencillo de cómo crear una hoja Excel con POI
	 * 
	 * @author chuidiang
	 * 
	 */

	    /**
	     * Crea una hoja Excel y la guarda.
	     * 
	     * @param args
	     */
	    public static void main(String[] args) {
	        // Se crea el libro
	    	 
	        HSSFWorkbook libro = new HSSFWorkbook();

	        // Se crea una hoja dentro del libro
	        HSSFSheet hoja = libro.createSheet();

	        // Se crea una fila dentro de la hoja
	        HSSFRow fila = hoja.createRow(0);

	        // Se crea una celda dentro de la fila
	        HSSFCell prefijo = fila.createCell((short) 0);
	        HSSFCell puntoVta = fila.createCell((short) 1);	        
	        HSSFCell nroComprobante = fila.createCell((short) 2);
	        HSSFCell transac = fila.createCell((short) 3);
	        HSSFCell cae = fila.createCell((short) 4);
	        HSSFCell vtoCae = fila.createCell((short) 5);
	        
	        // Se crea el contenido de la celda y se mete en ella.
	        //HSSFRichTextString texto = new HSSFRichTextString("hola mundo");
	        prefijo.setCellValue("001");
	        puntoVta.setCellValue("01");
	        nroComprobante.setCellValue("1212");
	        transac.setCellValue("222222");
	        cae.setCellValue("12345657897576");
	        vtoCae.setCellValue("20110101");
	        int i = 1; 
	        while(i < 20){
		        // Se crea una fila dentro de la hoja
		        HSSFRow fila1 = hoja.createRow(i);

		        // Se crea una celda dentro de la fila
		        HSSFCell prefijo1 = fila1.createCell((short) 0);
		        HSSFCell puntoVta1 = fila1.createCell((short) 1);	        
		        HSSFCell nroComprobante1 = fila1.createCell((short) 2);
		        HSSFCell transac1 = fila1.createCell((short) 3);
		        HSSFCell cae1 = fila1.createCell((short) 4);
		        HSSFCell vtoCae1 = fila1.createCell((short) 5);
		        
		        // Se crea el contenido de la celda y se mete en ella.
		        //HSSFRichTextString texto = new HSSFRichTextString("hola mundo");
		        prefijo1.setCellValue("001");
		        puntoVta1.setCellValue("01");
		        nroComprobante1.setCellValue("1212");
		        transac1.setCellValue("222222");
		        cae1.setCellValue("12345657897576");
		        vtoCae1.setCellValue("20110101");
	        	i++;
	        }
	        
	        
	        
	        // Se salva el libro.
	        try {
	            FileOutputStream elFichero = new FileOutputStream("c:\\holamundo.xls");
	            libro.write(elFichero);
	            elFichero.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}



