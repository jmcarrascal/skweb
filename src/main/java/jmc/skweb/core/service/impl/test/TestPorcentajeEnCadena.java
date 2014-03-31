package jmc.skweb.core.service.impl.test;

import java.math.BigDecimal;

import jmc.skweb.util.MathUtil;

public class TestPorcentajeEnCadena {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BigDecimal[] cadena = new BigDecimal[4];  
		cadena[0] = new BigDecimal(15);
		cadena[1] = new BigDecimal(16);
		cadena[2] = new BigDecimal(0);
		cadena[3] = new BigDecimal(0);
		System.out.println(MathUtil.getPorcentajeEnCadena(cadena));

	}

}
