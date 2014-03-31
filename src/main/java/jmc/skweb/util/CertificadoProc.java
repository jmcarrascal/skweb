package jmc.skweb.util;


import java.security.cert.X509Certificate;

import jmc.skweb.core.model.Usuario;

import bsh.This;

public class CertificadoProc extends Certificado{

	private String cargo = "";
	private String Oficina = "";
	
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getOficina() {
		return Oficina;
	}
	public void setOficina(String oficina) {
		Oficina = oficina;
	}
	
	
	public CertificadoProc getCertificadobyCert(X509Certificate cert) {
		
		return getCertificadoProc(super.getCertificadobyCert(cert));
		 
	}
	
	/**
	 * Seteo campos personalizados 
	 * @param ou
	 */
	private CertificadoProc getCertificadoProc(Certificado cert) {
		
		CertificadoProc certificadoProc = new CertificadoProc();
		certificadoProc.setCn(cert.getCn());
		certificadoProc.setEmail(cert.getEmail());
		certificadoProc.setO(cert.getO());
		certificadoProc.setOu(cert.getOu());
		try{
			String[] parrafos = cert.getOu().split("Se desempeña como ");
			String[] titulos = parrafos[1].split("en la oficina ");
			certificadoProc.setCargo(titulos[0].trim());
			certificadoProc.setOficina(titulos[1].trim());
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return certificadoProc;
	}

	
}
