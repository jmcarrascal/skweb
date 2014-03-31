package jmc.skweb.core.security.filter.validate.test;

import jmc.skweb.core.security.filter.validate.ValidacionCache;

public class TestCache {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Boolean respuesta = ValidacionCache.getInstance().validateUrl("msAotip", "9");
		System.out.println("Para msAotip y role 9 respuesta es: " + respuesta);
		Boolean respuesta1 = ValidacionCache.getInstance().validateUrl("msAotip", "18");
		System.out.println("Para msAotip y role 9 respuesta es: " + respuesta1);
	}

}
