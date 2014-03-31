package jmc.skweb.core.security.filter.validate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.*;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;


import java.security.cert.CRLException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.List;

import jmc.skweb.core.model.Usuario;
import jmc.skweb.util.CertificadoProc;
import jmc.skweb.util.Constants;




import sun.security.x509.CRLDistributionPointsExtension;
import sun.security.x509.DistributionPoint;
import sun.security.x509.GeneralName;
import sun.security.x509.X509CRLImpl;
import sun.security.x509.X509CertImpl;


public class ValidateDataCertificate {


	
		
	public Usuario getUsuariobyCert(X509Certificate cert){

		
		CertificadoProc certProc = new CertificadoProc(); 
		
		certProc = certProc.getCertificadobyCert(cert);
		
		return castCertificadoProcUsuario(certProc);		
	}  
	/**
	 * Casteo
	 * @param certProc
	 * @return
	 */
	private Usuario castCertificadoProcUsuario(CertificadoProc certProc){
		Usuario usu = new Usuario();
		
		usu.setEmail(certProc.getEmail());
		
		usu.setNombre(certProc.getCn());
		return usu;
	}
	private boolean validateEmailAddress(String emailAddress) {
		if (emailAddress == null) {
			return false;
		}
		boolean result = true;
		try {
			@SuppressWarnings("unused")
			InternetAddress emailAddr = new InternetAddress(emailAddress);
			if (!hasNameAndDomain(emailAddress)) {
				result = false;
			}
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}

	private boolean hasNameAndDomain(String emailAddress) {
		String[] tokens = emailAddress.split("@");
		return (tokens.length == 2 && tokens[0].length() > 0 && tokens[1].length() > 0);
	}

	private Usuario integridadOrganismoOrganizacional(String ou) {
		Usuario usuario = new Usuario();
		try{
			String[] parrafos = ou.split("Se desempeña como ");
			String[] titulos = parrafos[1].split("en la oficina ");
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return usuario;		
	}

}
