package jmc.skweb.core.service.impl.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class TestOpenURL {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		URL yahoo = null;
		try {
			yahoo = new URL("http://www.coinse.com.ar");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		BufferedReader in = null;
		try {in = new BufferedReader(new InputStreamReader(yahoo.openStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String line = null;
        
        try {
			while ((line = in.readLine()) != null) {
			    //Process the data, here we just print it out
			    System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			}
		

	}

