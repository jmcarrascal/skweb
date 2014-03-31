package jmc.skweb.core.service.impl.test;

import java.io.InputStream;

public class TestCargaClase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestCargaClase t = new TestCargaClase();
		t.testcarga();
		
	}
	public void testcarga(){
		InputStream tmplDatosPersonales = 
			getClass().getResourceAsStream("reportFichaDactilar.jrxml");
		if (tmplDatosPersonales != null){
			System.out.println("OK");
		}else{
			System.out.println("FALLO");
		}
	}
	
}
