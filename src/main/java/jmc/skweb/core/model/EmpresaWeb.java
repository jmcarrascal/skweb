package jmc.skweb.core.model;



// Generated 23/09/2011 12:11:03 by Hibernate Tools 3.2.0.b9

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Gente generated by hbm2java
 */
@Entity
@Table(name = "EmpresaWeb", schema = "ComunSql.dbo")
public class EmpresaWeb implements java.io.Serializable {

	private Integer idEmpresaWeb;	
	private String nombre;
	private Integer empresaNrSk;

	@Id	
	public Integer getIdEmpresaWeb() {
		return idEmpresaWeb;
	}
	public void setIdEmpresaWeb(Integer idEmpresaWeb) {
		this.idEmpresaWeb = idEmpresaWeb;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getEmpresaNrSk() {
		return empresaNrSk;
	}
	public void setEmpresaNrSk(Integer empresaNrSk) {
		this.empresaNrSk = empresaNrSk;
	}
	
	



}

