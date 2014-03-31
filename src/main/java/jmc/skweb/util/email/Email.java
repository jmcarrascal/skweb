package jmc.skweb.util.email;

import java.io.File;


public class Email {
	
	private String to = "";
	private String subject = "";
	private String message = "";
	private String from = "";
	private String usernameFrom = "";
	private String passwordFrom = "";
	private String attach = "";
	private File adjunto=null;
	
	
	
	public File getAdjunto() {
		return adjunto;
	}


	public void setAdjunto(File adjunto) {
		this.adjunto = adjunto;
	}


	public Email(String subject, String message, File adjunto, String to){
		this.adjunto = adjunto;
		this.message = message;
		this.subject = subject;
		this.to = to;
	}
	
	
	public String getAttach() {
		return attach;
	}


	public void setAttach(String attach) {
		this.attach = attach;
	}


	public String getUsernameFrom() {
		return usernameFrom;
	}
	public void setUsernameFrom(String usernameFrom) {
		this.usernameFrom = usernameFrom;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return this.message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getPasswordFrom() {
		return passwordFrom;
	}
	public void setPasswordFrom(String passwordFrom) {
		this.passwordFrom = passwordFrom;
	}
	
}
