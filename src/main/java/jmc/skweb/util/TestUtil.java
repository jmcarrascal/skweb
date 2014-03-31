package jmc.skweb.util;

public class TestUtil {
	
	public static boolean validate(String cadena){
		//El Valor es el checkSuma 		
		Long[] valores = new Long[1];
		valores[0] = 180l;		
		return FormatUtil.validar(cadena, valores);
	}

}
