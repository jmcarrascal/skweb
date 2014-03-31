package jmc.skweb.core.service.impl.test;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import jmc.skweb.util.FileUtil;


public class TestAddTextInImage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			//Tomo imagen y la convierto en byte[]
			//byte[] data = FileUtil.getBytesFromFile(new File("C:/SGI/archivos/huellas/h_81.jpg"));
			
			//System.out.println("Tamaño imagen original " + data.length);
			
			//Objeto Image  
			//Image reference = new ImageIcon(data).getImage();
			
			//Tomo Tamaño de la imagen
			//int widthImg = reference.getWidth(null);
			//int heightImg = reference.getHeight(null);

			//Creo imagen del mismo tamaño
			BufferedImage bimg = new BufferedImage(500, 400,BufferedImage.TYPE_INT_RGB);

			
			//Convierto imagen nueva en obbjeto Graphics2D	 
			Graphics2D gr = bimg.createGraphics();
			
			gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			//pinto el fondo blanco
			gr.setColor(Color.white);
			gr.fillRect(0, 0, 500, 400);
			
			gr.setBackground(Color.white);

			//Seteo Ubicacion
			gr.fillRect(0, 0, 1, 1);
			
			//coloco la imagen original
			//gr.drawImage(new ImageIcon(data).getImage(),0,0,null);
			
			//Seteo el color del String a Escribir			
			
			
			gr.setPaint(Color.black);
			
			//Seteo el tipo de letra
			Font font = new Font("arial", 1, 10);
			
			gr.setFont(font);
			
			gr.drawString("Estado Actual", 50, 10);
			gr.drawString("Estado Actual", 50, 20);
			//FuinalizaciÃ³n
			gr.dispose();
			ByteArrayOutputStream bas =	new ByteArrayOutputStream();
			ImageIO.write(bimg, "jpg", bas);
			byte[] data = bas.toByteArray();
			
			try {
				FileUtil.writeFile(data, "c://testimage.jpg");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	/**
	 * Agrega el Id de solicitud a la Imagen
	 * 
	 * @param Texto a Agregar
	 * @param data
	 * @return
	 */
	public static void addTextInImage(String text, String archivoOrig, String archivoTemp) {
		
		try {
			//Tomo imagen y la convierto en byte[]
			byte[] data = FileUtil.getBytesFromFile(new File(archivoOrig));
			
			//System.out.println("Tamaño imagen original " + data.length);
			
			//Objeto Image  
			Image reference = new ImageIcon(data).getImage();
			
			//Tomo Tamaño de la imagen
			int widthImg = reference.getWidth(null);
			int heightImg = reference.getHeight(null);

			//Creo imagen del mismo tamaño
			BufferedImage bimg = new BufferedImage(widthImg, heightImg,BufferedImage.TYPE_INT_RGB);

			//Convierto imagen nueva en obbjeto Graphics2D 
			Graphics2D gr = (Graphics2D)bimg.getGraphics();
			gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			//pinto el fondo blanco
			gr.setColor(Color.white);
			
			//Seteo Ubicacion
			gr.fillRect(0, 0, 1, 1);
			
			//coloco la imagen original
			gr.drawImage(new ImageIcon(data).getImage(),0,0,null);
			
			//Seteo el color del String a Escribir			
			gr.setPaint(Color.GRAY);
			
			//Seteo el tipo de letra
			Font font = new Font("arial", 1, 42);
			
			gr.setFont(font);
			
			gr.drawString(text, 50, heightImg-14);

			//Finalizo
			ByteArrayOutputStream bas =	new ByteArrayOutputStream();
			ImageIO.write(bimg, "jpg", bas);
			data = bas.toByteArray();
			
			try {
				FileUtil.writeFile(data, archivoTemp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		} catch (IOException e) {			
			e.printStackTrace();
		
			
		}
		

	}

}
