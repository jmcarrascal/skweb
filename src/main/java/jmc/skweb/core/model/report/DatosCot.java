package jmc.skweb.core.model.report;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class DatosCot {

	private Integer viajeNr;
	private Integer transacRemito;
	private String letra;
	private String prefijo;
	private BigDecimal total;
	private String nrComprob;
	private Timestamp fecha;
	private String razonSocial;
	private String cuit;
	private Integer tipoIva;
	private String domicilio;
	private String localidad;
	private String provincia;
	
	
	public DatosCot(Integer viajeNr, Integer transacRemito, String letra, String prefijo,
			BigDecimal total, String nrComprob, Timestamp fecha, String razonSocial, String cuit,
			Integer tipoIva, String domicilio, String localidad, String provincia ){
		this.viajeNr = viajeNr;
		this.transacRemito = transacRemito;
		this.letra = letra;
		this.prefijo = prefijo;
		this.total = total;
		this.nrComprob = nrComprob;
		this.fecha = fecha;
		this.razonSocial = razonSocial;
		this.tipoIva = tipoIva;
		this.domicilio = domicilio;
		this.localidad = localidad;
		this.provincia = provincia;
	}
	
	
	public Integer getViajeNr() {
		return viajeNr;
	}
	public void setViajeNr(Integer viajeNr) {
		this.viajeNr = viajeNr;
	}
	public Integer getTransacRemito() {
		return transacRemito;
	}
	public void setTransacRemito(Integer transacRemito) {
		this.transacRemito = transacRemito;
	}
	public String getLetra() {
		return letra;
	}
	public void setLetra(String letra) {
		this.letra = letra;
	}
	public String getPrefijo() {
		return prefijo;
	}
	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public String getNrComprob() {
		return nrComprob;
	}
	public void setNrComprob(String nrComprob) {
		this.nrComprob = nrComprob;
	}
	public Timestamp getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public Integer getTipoIva() {
		return tipoIva;
	}
	public void setTipoIva(Integer tipoIva) {
		this.tipoIva = tipoIva;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	
	
	
	
	
	
}
