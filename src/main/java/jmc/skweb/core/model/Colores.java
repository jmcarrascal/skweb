package jmc.skweb.core.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Colores", schema = "ComunSql.dbo")
public class Colores implements java.io.Serializable {

	public Colores() {
		
	}
 
	private Integer nr;
	private String descrip;
	
	@Id
	@Column(name = "nr", nullable = false)
	public Integer getNr() {
		return this.nr;
	}

	public void setNr(Integer nr) {
		this.nr = nr;
	}

	@Column(name = "descrip", length = 50)
	public String getDescrip() {
		return this.descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
}
