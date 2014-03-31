package jmc.skweb.util;

import java.security.cert.X509Certificate;

public class Certificado {

	private String email = "";
	private String cn = "";
	private String o = "";
	private String ou = "";
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCn() {
		return cn;
	}
	public void setCn(String cn) {
		this.cn = cn;
	}
	public String getO() {
		return o;
	}
	public void setO(String o) {
		this.o = o;
	}
	public String getOu() {
		return ou;
	}
	public void setOu(String ou) {
		this.ou = ou;
	}
	
	/**
	 * En base a un certificado obtengo el objeto Certificado
	 * @param cert
	 * @return
	 */
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

	/**
	 * En base a SN obtengo el objeto Certificado
	 * @param cert
	 * @return
	 */
	public static Certificado getCertificadobySn(String sn){
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
	
}
