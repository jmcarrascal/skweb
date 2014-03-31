package jmc.skweb.core.model.report;

import java.math.BigDecimal;

public class EstadisticaPedido {
	
	private String razonSocial;
	private String descripArt;
	private String articulo;
	private Integer genteNr;
	private BigDecimal cantidad;
	private BigDecimal entregado;
	private BigDecimal saldo;
	private Number precioProme;
	private BigDecimal valorizado;
	private BigDecimal cant2;
	private int tipoComprob;
	private BigDecimal precio;
	private BigDecimal bonif;
	
	
	public int getTipoComprob() {
		return tipoComprob;
	}

	public void setTipoComprob(int tipoComprob) {
		this.tipoComprob = tipoComprob;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public BigDecimal getBonif() {
		return bonif;
	}

	public void setBonif(BigDecimal bonif) {
		this.bonif = bonif;
	}

	public BigDecimal getCant2() {
		return cant2;
	}

	public void setCant2(BigDecimal cant2) {
		this.cant2 = cant2;
	}

	public BigDecimal getValorizado() {
		valorizado = saldo.multiply(new BigDecimal(precioProme.doubleValue()));
		return valorizado;
	}

	public void setValorizado(BigDecimal valorizado) {
		this.valorizado = valorizado;
	}

	public EstadisticaPedido(){
		
	}
	
	public EstadisticaPedido(String razonSocial, String descripArt,
			String articulo, Integer genteNr) {
		this.razonSocial = razonSocial;
		this.descripArt = descripArt;
		this.articulo = articulo;
		this.genteNr = genteNr;
	}

	public EstadisticaPedido(String razonSocial, String descripArt,
			String articulo, Integer genteNr, BigDecimal cantidad,
			BigDecimal entregado, BigDecimal saldo, Number precioProme) {
		this.razonSocial = razonSocial;
		this.descripArt = descripArt;
		this.articulo = articulo;
		this.genteNr = genteNr;
		this.cantidad = cantidad;
		this.entregado = entregado;
		this.saldo = saldo;
		this.precioProme = precioProme;
	}
	public EstadisticaPedido(String razonSocial, String descripArt,
			String articulo, Integer genteNr, BigDecimal cantidad,BigDecimal cant2,
			BigDecimal precio,BigDecimal saldo, Number precioProme) {
		this.razonSocial = razonSocial;
		this.descripArt = descripArt;
		this.articulo = articulo;
		this.genteNr = genteNr;
		this.cantidad = cantidad;
		this.cant2 = cant2;
		this.precio = precio;
		this.precioProme = precioProme;
	}
	
	public EstadisticaPedido(String razonSocial, String descripArt,
			String articulo, Integer genteNr, BigDecimal cantidad,
			BigDecimal entregado, BigDecimal saldo) {
		this.razonSocial = razonSocial;
		this.descripArt = descripArt;
		this.articulo = articulo;
		this.genteNr = genteNr;
		this.cantidad = cantidad;
		this.entregado = entregado;
		this.saldo = saldo;
	}

	
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getDescripArt() {
		return descripArt;
	}
	public void setDescripArt(String descripArt) {
		this.descripArt = descripArt;
	}
	public String getArticulo() {
		return articulo;
	}
	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}
	public Integer getGenteNr() {
		return genteNr;
	}
	public void setGenteNr(Integer genteNr) {
		this.genteNr = genteNr;
	}
	


	public Number getPrecioProme() {
		return precioProme;
	}

	public void setPrecioProme(Number precioProme) {
		this.precioProme = precioProme;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getEntregado() {
		return entregado;
	}

	public void setEntregado(BigDecimal entregado) {
		this.entregado = entregado;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	
	
	

}
