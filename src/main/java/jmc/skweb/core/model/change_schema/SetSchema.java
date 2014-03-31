package jmc.skweb.core.model.change_schema;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Table;

public class SetSchema {

	/**
	 * @param args
	 */
	private String rutaArchivos = "C:/Eclipse WS/Eclipse WSDPI/skweb/src/data/";
	
	private String anotacion = "@Table";
	
	public static void main(String[] args) {			
		
		//Recorrer los archivos
		String rutaModel = "C:/Eclipse WS/Eclipse WSDPI/skweb/src/main/java/jmc/skweb/core/model/";
		String empresa = "local.txt";
//		String empresa = "plasticos.txt";
//		String empresa = "herramientas.txt";
//		String empresa = "texsur.txt";
//		String empresa = "localL.txt";
		File repositorio = new File(rutaModel);
		File[] fileList = repositorio.listFiles();
		SetSchema setSchema = new SetSchema();
		for(File file: fileList){
			setSchema.analizoArchivo(file, empresa);
		}
		
		//Obtener 

	}

	private void analizoArchivo(File file, String empresa) {				 
		    SetSchema setSchema = new SetSchema();
		    String name = file.getName().replaceFirst(".java", "");
		    String nombreCompleto = file.getAbsolutePath(); 
		    boolean update = false;
		    //Obtengo la entidad
			List<String> lineasEntidad = setSchema.getEntidad(file);
			List<String> nuevasLineasEntidad = new ArrayList<String>();
			for(String linea:lineasEntidad){

				if (linea != null && linea.length() > 5){ 
				String key = linea.substring(0,6);
				if (key.equals(anotacion)){					
					String schema = setSchema.getSchema(name, empresa);
					String lineaTable = "@Table(name = \"" + name + "\", schema = \""+schema+"\")";
					nuevasLineasEntidad.add(lineaTable);
					update = true;
				}else{
					nuevasLineasEntidad.add(linea);
				}
				}else{
					nuevasLineasEntidad.add(linea);
				}
			}			
			//Guardo el n
			if (update){
				//Elimino el archivo
				file.delete();
				setSchema.setEntidad(nombreCompleto,nuevasLineasEntidad);
			}
			
	}

	private void setEntidad(String nombreCompleto,
			List<String> nuevasLineasEntidad) {

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(nombreCompleto));
			for(String linea : nuevasLineasEntidad){
				out.write(linea +"\n");	
			}
			
			out.close();
			} 
			catch (IOException e) 
			{ 
			System.out.println("Exception ");

			}

		
	}

	private List<String> getEntidad(File file) {
		List<String> lineas = new ArrayList<String>();
		BufferedReader entrada = null;
		try {
			entrada = new BufferedReader( new FileReader( file ) );
		} catch (FileNotFoundException e1) {
			
		}
		String linea = "";
		
	    try{
	    	while((linea = entrada.readLine())!=null){		    	
				lineas.add(linea);
			}
	    }catch(Exception e){	    	
	    }
	    
		return lineas;
	}

	private String getSchema(String name, String empresa) {
		
		File file = new File(rutaArchivos + empresa);
		BufferedReader entrada = null;
		try {
			entrada = new BufferedReader( new FileReader( file ) );
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String linea = "";
	    try{
	    	while((linea = entrada.readLine())!=null){
		    	//linea = entrada.readLine();				
				String[] datos = linea.split(",");			
				String key = datos[0];
				String valor = datos[1];			
					if (key.equals(name)){
						return valor;
					}
	    	}
	    }catch(Exception e){
	    	
	    }		
		return "";
	}
	
	

}
