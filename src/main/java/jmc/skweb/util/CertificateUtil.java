package jmc.skweb.util;

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
import jmc.skweb.util.Constants;




import sun.security.x509.CRLDistributionPointsExtension;
import sun.security.x509.DistributionPoint;
import sun.security.x509.GeneralName;
import sun.security.x509.X509CRLImpl;
import sun.security.x509.X509CertImpl;


public class CertificateUtil {


	
	public boolean validateExpirationDate(X509Certificate cert) throws Exception {

			// recupera el certificado del token
			
			try {
					cert.checkValidity();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		
		return true;
	}

	public boolean validateCRL(X509Certificate cert) throws KeyStoreException, CRLException {
		
			try {
				// crea una fabrica de certicidos X.509
				CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
				// convierte el certificado a un X509CertImpl
				X509CertImpl x509CertificateImpl = (X509CertImpl)(cert); 
				// obtiene la CRL del certificado
				CRLDistributionPointsExtension crlDistributionPointsExtension = x509CertificateImpl.getCRLDistributionPointsExtension();
				if (crlDistributionPointsExtension != null) {
					// para cada punto de distribuciion de la CRL verifica que el certificado del token no este revocado 
					@SuppressWarnings("unchecked")
					List<DistributionPoint> distributionPoints = (List<DistributionPoint>) crlDistributionPointsExtension.get(CRLDistributionPointsExtension.POINTS);
					for (DistributionPoint distributionPoint: distributionPoints) {
						List<GeneralName> generalNames = distributionPoint.getFullName().names();
						for (GeneralName generalName: generalNames) {
							String generalNameString = generalName.toString();
							if (generalNameString.startsWith("URIName:")) {
								String crlURLString = generalNameString.substring(9);
								if (crlURLString.startsWith("http")) {
									X509CRLImpl crlImpl = null;
									InputStream crlInputStream = new URL(crlURLString).openConnection().getInputStream();
									try {
										crlImpl = (X509CRLImpl)certificateFactory.generateCRL(crlInputStream);
									} finally {
										crlInputStream.close();
									}
									// verifica si el certidicado esta revocado en la CRL
									if (crlImpl.isRevoked(cert)) {
										return false;
									}
								}
							}
						}
					}
				}
			} catch (CertificateException e) {
				e.printStackTrace();
				return false;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		
		return true;
	}
	
	public Certificado getCertificadobyCert(X509Certificate cert){
		String sn = cert.getSubjectDN().getName();
		String[] fields = sn.split(",");
		Certificado certificado = new Certificado();
		// se recuperan los valores de los campos del asunto
		for (String field: fields) {
			String[] codeValue = field.split("=");
			String code = codeValue[0];
			if (code.startsWith(" ")) {
				code = code.substring(1);
			}
			String value = codeValue[1];
			if (code.equals("EMAILADDRESS")) {
				certificado.setEmail(value);				
			} else if (code.equals("CN")) {
				certificado.setCn(value);				
			} else if (code.equals("O")) {
				certificado.setO(value);
			} else if (code.equals("OU")) {
				certificado.setOu(value);				
			}			
		}
		return certificado;		
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
