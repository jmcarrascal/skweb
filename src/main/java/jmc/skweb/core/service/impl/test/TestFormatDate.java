package jmc.skweb.core.service.impl.test;



import java.util.Date;

import jmc.skweb.util.DateUtil;

public class TestFormatDate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			System.out.println(DateUtil.getCanonicalFech(new Date(System.currentTimeMillis())));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
