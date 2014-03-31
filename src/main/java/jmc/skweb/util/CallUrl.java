package jmc.skweb.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class CallUrl implements Runnable {
	private String url = ""; 
	
	public CallUrl(String url){
		this.url = url;
	}
	
	public void run() {
		URL method = null; 
		try {
			method = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			method.openStream();
		} catch (IOException e) {
			
			e.printStackTrace();
		}		
	}

}
