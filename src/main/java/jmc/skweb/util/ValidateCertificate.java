package jmc.skweb.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CRLException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.List;

import sun.security.x509.CRLDistributionPointsExtension;
import sun.security.x509.DistributionPoint;
import sun.security.x509.GeneralName;
import sun.security.x509.X509CRLImpl;
import sun.security.x509.X509CertImpl;

public class ValidateCertificate {
	
	/**
	 * Valida un certificado digital (Expiración, CRL y CA permitida en ese orden  )
	 * @param certificate
	 * @param x509Certificate
	 * @return
	 */
	public String validateCertificate(Certificate certificate, X509Certificate x509Certificate){
		String result = "";		
			try {
				if (this.isValidExpirationDate(certificate)){
						if (this.isValidCRL(certificate)){							
								if(!this.isValidCA(certificate, x509Certificate)){
									result = "El certificado no fue emitido por la CA esperada";
								}
						}else{
							result = "El certificado se encuentra Revocado";
						}
				}else{
					result = "El certificado se encuentra Expirado";
				}
			} catch (KeyStoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CRLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}					
		return result;
	}
	
	/**
	 * Valida un certificado digital (Expiración y CRL en ese orden)
	 * @param certificate
	 * @param x509Certificate
	 * @return
	 */
	public String validateCertificate(Certificate certificate){
		String result = "";
			try {
				if (this.isValidExpirationDate(certificate)){
						if (!this.isValidCRL(certificate)){							
							result = "El certificado se encuentra Revocado";
						}
				}else{
					result = "El certificado se encuentra Expirado";
				}
			} catch (KeyStoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CRLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		return result;
	}

	public boolean isValidExpirationDate(Certificate certificate) throws KeyStoreException {

		X509Certificate x509Certificate = (X509Certificate) certificate; 
		try {
			x509Certificate.checkValidity();
		} catch (CertificateExpiredException e) {
			return false;
		} catch (CertificateNotYetValidException e) {
			return false;
		}
	
		return true;
	}
	
	public boolean isValidCA(Certificate certificate, X509Certificate x509Certificate){
		try {
			certificate.verify(x509Certificate.getPublicKey());
		} catch (CertificateExpiredException e) {
			return false;
		} catch (CertificateNotYetValidException e) {
			return false;
		} catch (InvalidKeyException e) {
			return false;

		} catch (CertificateException e) {
			return false;

		} catch (NoSuchAlgorithmException e) {
			return false;
		} catch (NoSuchProviderException e) {
			return false;
		} catch (SignatureException e) {
			return false;
		}
		
		return true;
	}
	
	public boolean isValidCRL(Certificate certificate) throws KeyStoreException, CRLException {
					
		try {
				// crea una fabrica de certicidos X.509
				CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
				// convierte el certificado a un X509CertImpl
				X509CertImpl x509CertificateImpl = (X509CertImpl)((X509Certificate) certificate); 
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
									if (crlImpl.isRevoked(certificate)) {
										return false;
									}else{
										return true;
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
		return false;
		
	}


}
