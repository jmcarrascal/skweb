package jmc.skweb.core.model.report;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Transient;

import jmc.skweb.util.DateUtil;


/**
 * @author vaio
 *
 */
public class TransacReport {

	
	private Integer transacNr;
	private Date fecha;
	private String tipoComprob;
	private String letra;
	private String prefijo;
	private String nrComprob;
	private Double totalComprob;
	private Double saldoComprob;
	private Double saldoArrastre;
	
	public Integer getTransacNr() {
		return transacNr;
	}
	public void setTransacNr(Integer transacNr) {
		this.transacNr = transacNr;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getTipoComprob() {
		return tipoComprob;
	}
	public void setTipoComprob(String tipoComprob) {
		this.tipoComprob = tipoComprob;
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
	public String getNrComprob() {
		return nrComprob;
	}
	public void setNrComprob(String nrComprob) {
		this.nrComprob = nrComprob;
	}
	public Double getTotalComprob() {
		return totalComprob;
	}
	public void setTotalComprob(Double totalComprob) {
		this.totalComprob = totalComprob;
	}
	public Double getSaldoComprob() {
		return saldoComprob;
	}
	public void setSaldoComprob(Double saldoComprob) {
		this.saldoComprob = saldoComprob;
	}
	public Double getSaldoArrastre() {
		return saldoArrastre;
	}
	public void setSaldoArrastre(Double saldoArrastre) {
		this.saldoArrastre = saldoArrastre;
	}
	
}
