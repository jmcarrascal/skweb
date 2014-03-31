package jmc.skweb.core.service.impl.test;

import java.util.ArrayList;
import java.util.List;

import jmc.skweb.util.FormatUtil;

public class TestEjecuta {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> lista = new ArrayList<String>();
		lista.add("004;2;12;121212;1212121212;1212122");
		lista.add("004;2;12;121212;1212121212;1212122");
		lista.add("004;2;12;121212;1212121212;1212122");
		byte[] h = FormatUtil.getFacturasExcel(lista);
	}

}
