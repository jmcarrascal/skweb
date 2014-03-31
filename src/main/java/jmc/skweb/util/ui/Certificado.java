package jmc.skweb.util.ui;

import java.security.cert.X509Certificate;

public class Certificado{

	private String nombre;
	private String doc;
	private String cargo;
	private String dependencia;
	private String cuit;
	private Boolean valid;
	
	
	private void setSubject(String subject){
		String[] subjects=subject.split(",");
		//Elimino CN=
		String CN=subjects[1].substring(4);
		String[] datosPersonales=CN.split("/");
		this.setNombre(datosPersonales[0]);
		if (datosPersonales.length>=2)
			this.setDoc(datosPersonales[1]);
		if (datosPersonales.length>=3)
			this.setCargo(datosPersonales[2]);
		// Elimino OU=
		String OU=subjects[2].substring(4);
		this.setDependencia(OU);
		
		try {
			String cuit=subjects[5].split("/")[1];
			this.setCuit(cuit);
		} catch (Exception e) {
			this.setCuit("--");
		}	
	}
	
	public Certificado(X509Certificate certificate) {
	
		setSubject(certificate.getSubjectDN().getName());
			
		// TODO Auto-generated constructor stub
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	
	public String getDependencia() {
		return dependencia;
	}
	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}
	
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		//this.valid = Math.random()>0.5;
		this.valid =  valid;
	}
	
	
	
	
	
	
	
}
