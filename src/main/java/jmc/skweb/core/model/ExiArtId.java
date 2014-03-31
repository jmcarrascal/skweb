   package jmc.skweb.core.model;

// Generated 23/09/2011 12:11:03 by Hibernate Tools 3.2.0.b9

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * ExiArtId generated by hbm2java
 */
@Embeddable
public class ExiArtId implements java.io.Serializable {

	private String clave;
	private Existencias existencias;

	public ExiArtId() {
	}

	public ExiArtId(String clave, Existencias existencias) {
		this.clave = clave;
		this.existencias = existencias;
	}

	@Column(name = "Clave", nullable = false, length = 13)
	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NrExist")
	public Existencias getExistencias() {
		return existencias;
	}

	public void setExistencias(Existencias existencias) {
		this.existencias = existencias;
	}

	
	
	

}
