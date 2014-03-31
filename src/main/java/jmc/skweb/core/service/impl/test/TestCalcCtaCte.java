package jmc.skweb.core.service.impl.test;

import java.math.BigDecimal;

import jmc.skweb.util.FormatUtil;
import jmc.skweb.util.MathUtil;

public class TestCalcCtaCte {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BigDecimal saldoCalculado = FormatUtil.getSaldoCalculado(1,new BigDecimal(34344d));
		if (saldoCalculado != null){
			saldoCalculado = new BigDecimal(MathUtil.redondear(saldoCalculado.doubleValue()));
		}
		System.out.println(saldoCalculado);
		System.out.println(MathUtil.redondear(saldoCalculado.doubleValue()));
	}

}
