/*

 * Copyright 2008 Dirección Provincial de Informática de la Provincia de Buenos Aires.  All Rights Reserved

 * Dirección Provincial de Informática de la Provincia de Buenos Aires Proprietary and Confidential.

 *

 * You agree to use Your best efforts to protect the software and documentation

 * from unauthorized copy or use. The software source code represents and embodies

 * trade secrets of Dirección Provincial de Informática de la Provincia de Buenos Aires and/or its licensors.

 * The source code and embodied trade secrets are not licensed to you and any modification,

 * addition or deletion is strictly prohibited. You agree not to disassemble, decompile,

 * or otherwise reverse engineer the software in order to discover the source code and/or

 * the trade secrets contained in the source code.

 *

 *

 * Derecho de autor 2008 Dirección Provincial de Informática de la Provincia de Buenos Aires.  Todos los derechos reservados.

 * Propiedad de Dirección Provincial de Informática de la Provincia de Buenos Aires y Confidencial.

 *

 * Por la presente acuerdo hacer mi mayor esfuerzo para proteger el software y la documentación

 * de la copia o el uso no autorizados. El código fuente del software representa e incluye

 * secretos comerciales de Dirección Provincial de Informática de la Provincia de Buenos Aires y/o sus licenciantes. 

 * No se le otorga licencia del código fuente ni los secretos comerciales incluidos;

 * y cualquier modificación, agregado o eliminación se encuentra estrictamente prohibida.

 * Asimismo, por la presente me comprometo a no desarmar, descompilar, o de alguna forma utilizar

 * técnicas de ingeniería inversa sobre el software para descubrir su fuente y/o los

 * secretos comerciales contenidos en el código fuente.

 *

 */

package jmc.skweb.util.email;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;





/**
 * @author clarisa
 *
 */
public class SendEmailThread extends Thread{
		
	// Datos del usuario	
	private Email email;
	private Properties props;

	
	
	


	public SendEmailThread(Properties props, Email email) {
		this.email = email;
		this.props = props;
		
	}
	

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}

	// Envia el email	
	public void run() {
		System.out.println("empezoooooooooooooooo el envio del email");
		
		
		
		Session session = Session.getInstance(props);

		
		
		session.setDebug(false);

		MimeMessage message = new MimeMessage(session);
		
		try {
			
			
			
			// A quien va dirigido
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getTo()));						
			
			message.setSubject(email.getSubject());
			
			message.setText(email.getMessage());
			
			message.addHeaderLine("");
			
			InternetAddress addressFrom = new InternetAddress(props.getProperty("mail.smtp.from"));
			
			message.setFrom(addressFrom);
			
		} catch (AddressException e) {			
			e.printStackTrace();
		} catch (MessagingException e) {			
			e.printStackTrace();
		} 
	
		try {
			Transport t = session.getTransport("smtp");
			try {
				
				javax.mail.internet.MimeMultipart multiParte = new javax.mail.internet.MimeMultipart();
	            javax.mail.internet.MimeBodyPart texto = new javax.mail.internet.MimeBodyPart();
	            texto.setText(email.getMessage(), "ISO-8859-1");
	            multiParte.addBodyPart(texto);
	            
				if (email.getAdjunto() != null){
		            BodyPart adjunto = new MimeBodyPart();
					adjunto.setDataHandler(new DataHandler(new FileDataSource(email.getAdjunto())));
					adjunto.setFileName(email.getAdjunto().getName());					
					multiParte.addBodyPart(adjunto);
				}
	
	            message.setContent(multiParte);				
				
	            //System.out.println("Host " + props.getProperty("mail.smtp.host"));
				//System.out.println("User" + user);
				//System.out.println("PassWord" + props.getProperty("mail.smtp.pass"));
	            
	            t.connect(props.getProperty("mail.smtp.host"),props.getProperty("mail.smtp.user"),props.getProperty("mail.smtp.pass"));				
				t.sendMessage(message,message.getAllRecipients());
				t.close();
			} catch (Exception e) {				
				e.printStackTrace();
			}			
		} catch (NoSuchProviderException e) {			
			e.printStackTrace();
		}
		System.out.println("Terminooooooooooooooo el envio del email");
		
	}

}
