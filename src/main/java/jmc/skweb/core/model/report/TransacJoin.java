package jmc.skweb.core.model.report;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

import jmc.skweb.core.model.Items;
import jmc.skweb.util.DateUtil;


/**
 * @author vaio
 *
 */
public class TransacJoin {

	
	private Integer genteNr;
	private String razonSocial;
	private String nrComprob;
	private Integer transacNr;
	private Date fecha;
	private Integer nrInt;
	private BigDecimal cant1;
	private BigDecimal cant1Entregado;
	private BigDecimal saldoCantidad;
	private Date fechaEntrega;
	private String formatFecha;
	private String formatFechaEntrega;
	private Boolean delete;
	private List<Items> itemsList = new ArrayList<Items>();
	
	public TransacJoin(){
		
	}
	
	public TransacJoin(Integer genteNr, String razonSocial,String nrComprob,Integer transacNr,Date fecha){
		this.genteNr = genteNr;
	}

	
	public TransacJoin(Integer genteNr, String razonSocial,String nrComprob,Integer transacNr,Date fecha, Integer nrInt,BigDecimal cant1,BigDecimal cant1Entregado,Date fechaEntrega){
		this.genteNr = genteNr;
		this.razonSocial = razonSocial;
		this.nrComprob = nrComprob;
		this.transacNr = transacNr;
		this.fecha = fecha;
		this.nrInt = nrInt;
		this.cant1 = cant1;
		this.cant1Entregado = cant1Entregado;		
		this.fechaEntrega = fechaEntrega;
	}
	
	public Integer getGenteNr() {
		return genteNr;
	}
	public void setGenteNr(Integer genteNr) {
		this.genteNr = genteNr;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getNrComprob() {
		return nrComprob;
	}
	public void setNrComprob(String nrComprob) {
		this.nrComprob = nrComprob;
	}
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

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Integer getNrInt() {
		return nrInt;
	}

	public void setNrInt(Integer nrInt) {
		this.nrInt = nrInt;
	}

	public BigDecimal getCant1() {
		return cant1;
	}

	public void setCant1(BigDecimal cant1) {
		this.cant1 = cant1;
	}

	public BigDecimal getCant1Entregado() {
		return cant1Entregado;
	}

	public void setCant1Entregado(BigDecimal cant1Entregado) {
		this.cant1Entregado = cant1Entregado;
	}

	public String getFormatFecha() {
		formatFecha = DateUtil.getShortFormattedDate(fecha);
		return formatFecha;
	}

	public void setFormatFecha(String formatFecha) {
		this.formatFecha = formatFecha;
	}

	public String getFormatFechaEntrega() {
		formatFechaEntrega = DateUtil.getShortFormattedDate(fechaEntrega);
		return formatFechaEntrega;
	}

	public void setFormatFechaEntrega(String formatFechaEntrega) {
		this.formatFechaEntrega = formatFechaEntrega;
	}

	public BigDecimal getSaldoCantidad() {
		saldoCantidad = cant1.subtract(cant1Entregado);
		return saldoCantidad;
	}

	public void setSaldoCantidad(BigDecimal saldoCantidad) {
		this.saldoCantidad = saldoCantidad;
	}

	@Transient
	public Boolean getDelete() {
		return delete;
	}

	public void setDelete(Boolean delete) {
		this.delete = delete;
	}

	public List<Items> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<Items> itemsList) {
		this.itemsList = itemsList;
	}

	
	
	
	
	
	
}
