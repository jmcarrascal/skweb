package jmc.skweb.core.service.impl.test;

public class TestIndexOf {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String rutaBase = "hp01|Plasticos";
		int ubicador = rutaBase.indexOf("|");
		
		String servidor = rutaBase.substring(0, ubicador);
		String base = rutaBase.substring(ubicador +1);
		
		System.out.println(servidor);
		System.out.println(base);
	}

}
