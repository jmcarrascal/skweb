package jmc.skweb.core.service.impl.test;

public class TestSubString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {	
	
	String valorInt = "61221036814299|20110609|00000278|Factura individual, DocTipo: 80, DocNro 23925446769 no se encuentra inscripto en condicion ACTIVA en el impuesto.";
	System.out.println(getError(valorInt));
	}
	private static String getError(String valorInt){		
		if (valorInt != null){
			int i = valorInt.indexOf("|", 1);
			while (i != -1){			 
				valorInt = valorInt.substring(i);
				i = valorInt.indexOf("|", 1);
				
			}
			return valorInt.replaceFirst("\\|", "");					
		}else{
			return null;
		}
	}

}
