package jmc.skweb.core.model;

// Generated 23/09/2011 12:11:03 by Hibernate Tools 3.2.0.b9

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Domicilios generated by hbm2java
 */
@Entity
@Table(name = "Domicilios", schema = "ComunSql.dbo")
public class Domicilios implements java.io.Serializable {

	private DomiciliosId id;
	private Gente gente;
	private String razonSocial;
	private String descripcion;
	private String domicilio;
	private String localidad;
	private String provincia;
	private String pais;
	private String cp;
	private String contacto;
	private String teVoz;
	private String Integerernet;
	private String teMovil;
	private String teEmerg;
	private String teFax;
	private String teComp;
	private Short domiPrincipal;
	private Integer transporte;
	private Short domiEntrega;
	private Short domiCobranza;
	private Integer domiMailing;
	private Integer codProv;
	private String descripC;
	private Integer nr;
	

	
	
	
	@Transient
	public Integer getNr() {
		nr = id.getDomiNr();
		return nr;
	}


	public void setNr(Integer nr) {
		this.nr = nr;
	}


	@Transient
	public String getDescripC() {
		this.descripC = "[" + id.getDomiNr() + "] " + domicilio; 
		return descripC;
	}


	public Domicilios() {
	}

	public Domicilios(DomiciliosId id, Gente gente, Short domiPrincipal,
			Integer transporte, Short domiEntrega, Short domiCobranza,
			Integer domiMailing, Integer codProv) {
		this.id = id;
		this.gente = gente;
		this.domiPrincipal = domiPrincipal;
		this.transporte = transporte;
		this.domiEntrega = domiEntrega;
		this.domiCobranza = domiCobranza;
		this.domiMailing = domiMailing;
		this.codProv = codProv;
	}

	public Domicilios(DomiciliosId id, Gente gente, String razonSocial,
			String descripcion, String domicilio, String localidad,
			String provincia, String pais, String cp, String contacto,
			String teVoz, String Integerernet, String teMovil, String teEmerg,
			String teFax, String teComp, Short domiPrincipal, Integer transporte,
			Short domiEntrega, Short domiCobranza, Integer domiMailing, Integer codProv) {
		this.id = id;
		this.gente = gente;
		this.razonSocial = razonSocial;
		this.descripcion = descripcion;
		this.domicilio = domicilio;
		this.localidad = localidad;
		this.provincia = provincia;
		this.pais = pais;
		this.cp = cp;
		this.contacto = contacto;
		this.teVoz = teVoz;
		this.Integerernet = Integerernet;
		this.teMovil = teMovil;
		this.teEmerg = teEmerg;
		this.teFax = teFax;
		this.teComp = teComp;
		this.domiPrincipal = domiPrincipal;
		this.transporte = transporte;
		this.domiEntrega = domiEntrega;
		this.domiCobranza = domiCobranza;
		this.domiMailing = domiMailing;
		this.codProv = codProv;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "genteNr", column = @Column(name = "GenteNr", nullable = false)),
			@AttributeOverride(name = "domiNr", column = @Column(name = "DomiNr", nullable = false)) })
	public DomiciliosId getId() {
		return this.id;
	}

	public void setId(DomiciliosId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GenteNr", nullable = false, insertable = false, updatable = false)
	public Gente getGente() {
		return this.gente;
	}

	public void setGente(Gente gente) {
		this.gente = gente;
	}

	@Column(name = "RazonSocial", length = 50)
	public String getRazonSocial() {
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	@Column(name = "Descripcion", length = 50)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "Domicilio", length = 50)
	public String getDomicilio() {
		return this.domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	@Column(name = "Localidad", length = 50)
	public String getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	@Column(name = "Provincia", length = 50)
	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	@Column(name = "Pais", length = 50)
	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@Column(name = "CP", length = 15)
	public String getCp() {
		return this.cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	@Column(name = "Contacto", length = 50)
	public String getContacto() {
		return this.contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	@Column(name = "TeVoz", length = 50)
	public String getTeVoz() {
		return this.teVoz;
	}

	public void setTeVoz(String teVoz) {
		this.teVoz = teVoz;
	}

	@Column(name = "Internet", length = 50)
	public String getInternet() {
		return this.Integerernet;
	}

	public void setInternet(String Integerernet) {
		this.Integerernet = Integerernet;
	}

	@Column(name = "TeMovil", length = 50)
	public String getTeMovil() {
		return this.teMovil;
	}

	public void setTeMovil(String teMovil) {
		this.teMovil = teMovil;
	}

	@Column(name = "TeEmerg", length = 50)
	public String getTeEmerg() {
		return this.teEmerg;
	}

	public void setTeEmerg(String teEmerg) {
		this.teEmerg = teEmerg;
	}

	@Column(name = "TeFax", length = 50)
	public String getTeFax() {
		return this.teFax;
	}

	public void setTeFax(String teFax) {
		this.teFax = teFax;
	}

	@Column(name = "TeComp", length = 50)
	public String getTeComp() {
		return this.teComp;
	}

	public void setTeComp(String teComp) {
		this.teComp = teComp;
	}

	@Column(name = "DomiPrincipal", nullable = false)
	public Short getDomiPrincipal() {
		return this.domiPrincipal;
	}

	public void setDomiPrincipal(Short domiPrincipal) {
		this.domiPrincipal = domiPrincipal;
	}

	@Column(name = "Transporte", nullable = false)
	public Integer getTransporte() {
		return this.transporte;
	}

	public void setTransporte(Integer transporte) {
		this.transporte = transporte;
	}

	@Column(name = "DomiEntrega", nullable = false)
	public Short getDomiEntrega() {
		return this.domiEntrega;
	}

	public void setDomiEntrega(Short domiEntrega) {
		this.domiEntrega = domiEntrega;
	}

	@Column(name = "DomiCobranza", nullable = false)
	public Short getDomiCobranza() {
		return this.domiCobranza;
	}

	public void setDomiCobranza(Short domiCobranza) {
		this.domiCobranza = domiCobranza;
	}

	@Column(name = "DomiMailing", nullable = false)
	public Integer getDomiMailing() {
		return this.domiMailing;
	}

	public void setDomiMailing(Integer domiMailing) {
		this.domiMailing = domiMailing;
	}

	@Column(name = "CodProv", nullable = false)
	public Integer getCodProv() {
		return this.codProv;
	}

	public void setCodProv(Integer codProv) {
		this.codProv = codProv;
	}

}
