package jmc.skweb.core.service.impl.test;

import java.util.Date;

import jmc.skweb.util.DateUtil;

public class TestFormattedDate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Date date = new Date(System.currentTimeMillis());
		
		System.out.println(DateUtil.getShortFormattedDate(date));

	}

}
