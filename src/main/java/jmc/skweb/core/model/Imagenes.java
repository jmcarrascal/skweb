package jmc.skweb.core.model;

// Generated 23/09/2011 12:11:03 by Hibernate Tools 3.2.0.b9

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name = "Imagenes", schema = "ComunSql.dbo")
//Simpa
@Table(name = "Imagenes", schema = "ComunSql.dbo")

public class Imagenes implements java.io.Serializable {

	private String clave;
	private byte[] imagen1;
	

	public Imagenes() {
	}
	
	@Id
	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public byte[] getImagen1() {
		return imagen1;
	}

	public void setImagen1(byte[] imagen1) {
		this.imagen1 = imagen1;
	}


}
