package jmc.skweb.core.model;

// Generated 23/09/2011 12:11:03 by Hibernate Tools 3.2.0.b9

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
//@Table(name = "Imagenes", schema = "ComunSql.dbo")
//Simpa
@Table(name = "ImagenesArticulos", schema = "Imagenes.dbo")

public class ImagenesArticulos implements java.io.Serializable {

	private String clave;
	private byte[] imagen;
	

	public ImagenesArticulos() {
	}
	
	public ImagenesArticulos(String clave, byte[] file) {
		this.clave = clave;
		this.imagen = file;
	}

	@Id
	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	@Lob @Basic(fetch=FetchType.LAZY)
	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}


}
