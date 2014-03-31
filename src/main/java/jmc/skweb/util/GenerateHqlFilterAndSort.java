package jmc.skweb.util;


import java.lang.reflect.Method;

import javax.persistence.Transient;

import jmc.util.annotation.ComposedAttribute;

public class GenerateHqlFilterAndSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	//	System.out.println(getHqlFilterAndSort("gba.core.model.ProyectoOficina", "proyecto.fechaSalida", "desc","poo","proyecto.idProyecto","po","12",true));
	}
	
	public static String getHqlFilterAndSort(String className, String propertySort, String orderSort, String prefixSort, String propertyFilterArray[], String prefixFilter, String valueFilterArray[], Boolean setWhere){
		String result = "";
		String andConnector=" and ";
		int i=0;
		String valueFilter;

		if (setWhere){
			result = " where ";
			andConnector=" ";
		}
		for(String propertyFilter : propertyFilterArray){
			valueFilter=valueFilterArray[i++];
			if (propertyFilter != null && propertyFilter.length() >0){

					if (isPropertyPersistent(prefixFilter,propertyFilter,valueFilter)){
					//if (){
						if (isReturnNumericMethod(className, propertyFilter)){
							result += andConnector + prefixFilter + "." + propertyFilter + " = " + valueFilter;
						}else{
							result += andConnector + prefixFilter + "." + propertyFilter + " like ('%" + valueFilter + "%')";
						}
					}else{
					//Delego en la clase que sabe escribir el SQL para sus properties Transient
						String filter=getHqlFilterDispatched(className, propertyFilter, prefixFilter,valueFilter);
						if (filter.length()>0)
								filter=andConnector + filter;
						result+= filter; 
					}
			}
		}		
		if (propertySort != null && !propertySort.equals("")){
			result = result + " order by " + prefixSort + "." + propertySort + " " + orderSort;  
		}
		else{
			//result = result + " order by " + prefixSort + ".idFactura desc";
		}
		System.out.println(result);
		return result;		
	}
	
	//Devuelve SQL de  la forma A or B or C cuando el atributo que esta siendo examinado a nivel
	//de objetos no coincide explicitamente con lo almacenado en la DB
	@SuppressWarnings("unchecked")
	private static String getHqlFilterDispatched(String className,
			String propertyFilter, String prefixFilter,String valueFilter) {
		
		Class clas=getClass(prefixFilter,propertyFilter);
		String beforeProperty=getBeforeMethodName(propertyFilter);
		Method method=getMethod(clas, propertyFilter);
		ComposedAttribute annotation=method.getAnnotation(ComposedAttribute.class);
		String result="";
		if (prefixFilter.length()>0)prefixFilter+=".";
		if (annotation!=null){
			String[] attributes=annotation.attributes().split("[|]");
			for (String attribute: attributes){
				result+=prefixFilter+beforeProperty+attribute +"='"+valueFilter+"' or ";
				
			}
			if (result.length()>3) 
				result="("+result.substring(0,result.length()-3)+")";
			
		}
		return result;
				
	}
		
	private static String getBeforeMethodName(String propertyFilter) {
		String result="";
		int indexLast=propertyFilter.lastIndexOf(".");
		if (indexLast>-1)
			result=propertyFilter.substring(0, indexLast+1);
		return result;
	}

	@SuppressWarnings("unchecked")
	private static boolean isPropertyPersistent(String prefixFilter,
			String propertyFilter, String valueFilter) {
		return true;
}

	public static Boolean isReturnNumericMethod(String className, String propertyFilter){

		propertyFilter = getLastProperty(propertyFilter);
		
		int punto = propertyFilter.indexOf(".");
		
		if (punto != -1){
			className = getClassName(propertyFilter.substring(0, punto));
			propertyFilter = propertyFilter.substring(punto + 1);			
		}else{
			className = Constants.PACKAGE_MODEL + "." + className;
		}
						
		Class myObj = null;
		
		try {
			myObj = Class.forName(className);
		} catch (ClassNotFoundException e) {		
			e.printStackTrace();
			return false;
		}
		
		
		String metodo = getMethodName(propertyFilter);				
		
		
		Method method = null;
		try {
			method = myObj.getMethod(metodo, null);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println(method.getReturnType().getName());
		return isNumeric(method.getReturnType().getName());
		
	}
	
	private static String getMethodName(String propertyFilter){
		int indexStartProperty=0;
		int indexLastProperty=1;
		if (propertyFilter.indexOf(".")>0){
			indexStartProperty=propertyFilter.lastIndexOf(".");
			indexStartProperty+=1;
			indexLastProperty=indexStartProperty+1;
		}
		
		String primerLetra = propertyFilter.substring(indexStartProperty,indexLastProperty);
		String restoProperty = propertyFilter.substring(indexStartProperty+1);
		String metodo = "get" + primerLetra.toUpperCase() + restoProperty;
		return metodo;
	}

	private static String getClassName(String propertyName){
		String primerLetra = propertyName.substring(0,1);
		String restoProperty = propertyName.substring(1);
		String className="";
		try{
			className = Constants.PACKAGE_MODEL + "." + primerLetra.toUpperCase() + restoProperty;
			Class.forName(className);
			return className;
		}catch(ClassNotFoundException cnf){
			String[] otros_paquetes=Constants.PACKAGE_MODEL.split("[|]"); 
			for(String s: otros_paquetes){
				className=s+"."+primerLetra.toUpperCase() + restoProperty;
				try {
					Class.forName(className);
					return className;
				} catch (ClassNotFoundException e) {
					System.out.println("Pruebo proximo paquete posible");
				}		
			}
		}
		return className;
	}

	private static Boolean isNumeric(String className){
		
		if (className.equals("java.lang.Long") || className.equals("java.lang.Integer") || className.equals("java.lang.Double") || className.equals("java.lang.Float") || className.equals("java.lang.Boolean")){ 
			return true;
		}		
		return false;
	
	}
	
	private static String getLastProperty(String propertyName){
		String[] listProperty = propertyName.split("\\.");
		
		if (listProperty.length > 2){
			propertyName = listProperty[listProperty.length-2] + "." + listProperty[listProperty.length-1];  
		}
		
		return propertyName;
	}
	

	
	private static Method getMethod(Class clas,String propertyFilter){
		String metodo = getMethodName(propertyFilter);				
			
		Method method = null;
		try {
			method = clas.getMethod(metodo, null);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return method;
	}

	
	/*
	 * Este método devuelve el objeto Class a partir del nombre de la clase incial y considerando que 
	 * la propiedad propertyFilter puede contener referencias a otras clases (siendo la ultima parte 
	 * realmente una propiedad)
	 * */
	private static Class getClass(String className, String propertyFilter) {
		propertyFilter = getLastProperty(propertyFilter);
		
		int punto = propertyFilter.indexOf(".");
		
			className = Constants.PACKAGE_MODEL + "." + "Digecot";
						
		Class clas = null;
		
		try {
			clas = Class.forName(className);
		} catch (ClassNotFoundException e) {		
			System.out.println("ERROR AL CARGAR LA CLASE!");
			e.printStackTrace();
		}
		return clas;
	}

}
