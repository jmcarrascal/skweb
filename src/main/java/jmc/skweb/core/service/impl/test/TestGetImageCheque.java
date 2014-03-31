package jmc.skweb.core.service.impl.test;

import java.io.File;
import java.util.List;

public class TestGetImageCheque {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Obtener PC que tengo que ir a buscar archivos
		//Hago un loop para obtener imagen de cada PC
			//Listo Directorios
			File carpeta = new File("//192.168.0.166//Compartir//");			
			if (carpeta.exists()){
				System.out.println("OK");
				//Listo las carpetas
				File[] carpetas = carpeta.listFiles();				
				for(File carpetaFechas:carpetas){
					if(carpetaFechas.getName().indexOf("read") == -1){
						if(carpetaFechas.isDirectory()){
							File[] imagenes = carpetaFechas.listFiles();
							for(File imagen:imagenes){
								//Obtengo la Imagen
								System.out.println(imagen.getName());
								//Obtener imagen JPG
								
							}
						}
					}
					//Renombro la carpeta
					File carpetaLeida = new File(carpetaFechas.getAbsoluteFile() + "_read");
					carpetaFechas.renameTo(carpetaLeida);
				}
			}


	}

}
