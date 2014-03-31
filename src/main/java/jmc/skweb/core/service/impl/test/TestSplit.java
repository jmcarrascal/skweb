package jmc.skweb.core.service.impl.test;

public class TestSplit {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String filtro = "arr+f";
		String[] filtros = filtro.split("\\+");
		
		for(String f:filtros){
			System.out.println(f);
		}
		

	}

}
