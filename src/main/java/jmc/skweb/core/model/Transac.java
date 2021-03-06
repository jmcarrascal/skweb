package jmc.skweb.core.model;

// Generated 22/09/2011 00:18:30 by Hibernate Tools 3.2.0.b9

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Immutable;

import jmc.skweb.util.DateUtil;
import jmc.skweb.util.FormatUtil;
import jmc.skweb.util.MathUtil;

/**
 * Transac generated by hbm2java
 */
@Entity
//@Table(name = "Transac", schema="Empresa1.dbo")
//Simpa Plsticos
//@Table(name = "Transac", schema="Plasticos.dbo")
@Table(name = "Transac", schema = "Empresa1.dbo")

public class Transac implements java.io.Serializable {

	private Integer transacNr;
	private Gente gente;
	private Date fecha;
	private TipoComprob tipoComprob;
	private String nrComprob = "00000000";
	private String letra = "";
	private String prefijo = "";
	private Integer vendedor = 0;
	private Integer cobrador = 0;
	private Integer condiciones = 0;
	private Integer tipoIva = 0;
	private Float alicuotaIva = new Float(0);
	private Float sobreAlicIva = new Float(0);
	private Float alicuotaInternos = new Float(0);
	private Float valorMonExt = 0f;
	private Date fechaTransac;
	private Short vencim1 = 0;
	private Short vencim2 = 0;
	private Short vencim3 = 0;
	private Short vencim4 = 0;
	private Integer nrSucursal = 0;
	private Integer nrOperador = 0;
	private String observaciones = "";
	private BigDecimal netoGrav;
	private BigDecimal netoNoGrav = new BigDecimal(0);
	private BigDecimal iva = new BigDecimal(0);
	private BigDecimal ivaRni= new BigDecimal(0);
	private BigDecimal ivaPercep= new BigDecimal(0);	
	private BigDecimal retIb= new BigDecimal(0);
	private BigDecimal retIva= new BigDecimal(0);
	private BigDecimal retGan= new BigDecimal(0);
	private BigDecimal retCargasSoc= new BigDecimal(0);
	private BigDecimal descuentos= new BigDecimal(0);
	private BigDecimal otrosImp1= new BigDecimal(0);
	private BigDecimal otrosImp2= new BigDecimal(0);
	private BigDecimal otrosImp3= new BigDecimal(0);
	private BigDecimal total= new BigDecimal(0);
	private BigDecimal efectivo= new BigDecimal(0);
	private BigDecimal valTerceros= new BigDecimal(0);
	private BigDecimal pagares= new BigDecimal(0);
	private BigDecimal tarjetas= new BigDecimal(0);
	private BigDecimal chequesPropios = new BigDecimal(0);
	private BigDecimal monExt= new BigDecimal(0);
	private String urgencia = "";
	private Date fechaEntrega;
	private String ordenCompra = "";
	private Date fechaEstPago;
	private Integer destino =0;
	private BigDecimal saldo = new BigDecimal(0);
	private Short cuotas = 0;
	private Integer nrDomicilioEnt = 0;
	private Integer imputacionContable = 0;
	private Short ingreBrutos = 0;
	private Float bultos = 0F;
	private Float peso = 0f;
	private BigDecimal valor = new BigDecimal(0);
	private String proce = "";
	private String riesgo = "";
	private String flete = "";
	private BigDecimal p = new BigDecimal(0);
	private BigDecimal r = new BigDecimal(0);
	private Integer tranRemitoOrig = 0;
	private Integer tranReciboFact= 0;
	private Integer tranFactCred;
	private BigDecimal factCred = new BigDecimal(0);
	private Character objetooLe;
	private String benef = "";
	private BigDecimal modu = new BigDecimal(0);
	private String cae = "";
	private String fechaCae = "";
	private String signo;
	private BigDecimal saldoCalculado;
	private String rutaComprobantePdf;
	private String shortObservaciones;
	private Double saldoAcumulado;
	private List<Items> itemsList = new ArrayList<Items>();
	private Integer recepcionClienteTraza;
	
	
	

	@Transient
	public Integer getRecepcionClienteTraza() {
		return recepcionClienteTraza;
	}

	public void setRecepcionClienteTraza(Integer recepcionClienteTraza) {
		this.recepcionClienteTraza = recepcionClienteTraza;
	}

	public Transac() {
	}

	public Transac(Integer transacNr, Integer genteNr, Date fecha, Integer tipoComprob,
			String nrComprob, String letra, String prefijo, Integer vendedor,
			Integer cobrador, Integer condiciones, Integer tipoIva, Float alicuotaIva,
			Float sobreAlicIva, Float alicuotaInternos, Float valorMonExt,
			Date fechaTransac, Short vencim1, Short vencim2, Short vencim3,
			Short vencim4, Integer nrSucursal, Integer nrOperador, BigDecimal netoGrav,
			BigDecimal netoNoGrav, BigDecimal iva, BigDecimal ivaRni,
			BigDecimal ivaPercep,  BigDecimal retIb,
			BigDecimal retIva, BigDecimal retGan, BigDecimal retCargasSoc,
			BigDecimal descuentos, BigDecimal otrosImp1, BigDecimal otrosImp2,
			BigDecimal otrosImp3, BigDecimal total, BigDecimal efectivo,
			BigDecimal valTerceros, BigDecimal pagares, BigDecimal tarjetas,
			BigDecimal chequesPropios, BigDecimal monExt, BigDecimal saldo,
			Integer imputacionContable, BigDecimal p, BigDecimal r,
			BigDecimal factCred) {
		this.transacNr = transacNr;
		
		this.fecha = fecha;
		
		this.nrComprob = nrComprob;
		this.letra = letra;
		this.prefijo = prefijo;
		this.vendedor = vendedor;
		this.cobrador = cobrador;
		this.condiciones = condiciones;
		this.tipoIva = tipoIva;
		this.alicuotaIva = alicuotaIva;
		this.sobreAlicIva = sobreAlicIva;
		this.alicuotaInternos = alicuotaInternos;
		this.valorMonExt = valorMonExt;
		this.fechaTransac = fechaTransac;
		this.vencim1 = vencim1;
		this.vencim2 = vencim2;
		this.vencim3 = vencim3;
		this.vencim4 = vencim4;
		this.nrSucursal = nrSucursal;
		this.nrOperador = nrOperador;
		this.netoGrav = netoGrav;
		this.netoNoGrav = netoNoGrav;
		this.iva = iva;
		this.ivaRni = ivaRni;
		this.ivaPercep = ivaPercep;
		
		this.retIb = retIb;
		this.retIva = retIva;
		this.retGan = retGan;
		this.retCargasSoc = retCargasSoc;
		this.descuentos = descuentos;
		this.otrosImp1 = otrosImp1;
		this.otrosImp2 = otrosImp2;
		this.otrosImp3 = otrosImp3;
		this.total = total;
		this.efectivo = efectivo;
		this.valTerceros = valTerceros;
		this.pagares = pagares;
		this.tarjetas = tarjetas;
		this.chequesPropios = chequesPropios;
		this.monExt = monExt;
		this.saldo = saldo;
		this.imputacionContable = imputacionContable;
		this.p = p;
		this.r = r;
		this.factCred = factCred;
	}

	public Transac(Integer transacNr, Integer genteNr, Date fecha, Integer tipoComprob,
			String nrComprob, String letra, String prefijo, Integer vendedor,
			Integer cobrador, Integer condiciones, Integer tipoIva, Float alicuotaIva,
			Float sobreAlicIva, Float alicuotaInternos, Float valorMonExt,
			Date fechaTransac, Short vencim1, Short vencim2, Short vencim3,
			Short vencim4, Integer nrSucursal, Integer nrOperador,
			String observaciones, BigDecimal netoGrav, BigDecimal netoNoGrav,
			BigDecimal iva, BigDecimal ivaRni, BigDecimal ivaPercep,
			 BigDecimal retIb, BigDecimal retIva,
			BigDecimal retGan, BigDecimal retCargasSoc, BigDecimal descuentos,
			BigDecimal otrosImp1, BigDecimal otrosImp2, BigDecimal otrosImp3,
			BigDecimal total, BigDecimal efectivo, BigDecimal valTerceros,
			BigDecimal pagares, BigDecimal tarjetas, BigDecimal chequesPropios,
			BigDecimal monExt, String urgencia, Date fechaEntrega,
			String ordenCompra, Date fechaEstPago, Integer destino,
			BigDecimal saldo, Short cuotas, Integer nrDomicilioEnt,
			Integer imputacionContable, Short ingreBrutos, Float bultos,
			Float peso, BigDecimal valor, String proce, String riesgo,
			String flete, BigDecimal p, BigDecimal r, Integer tranRemitoOrig,
			Integer tranReciboFact, Integer tranFactCred, BigDecimal factCred,
			Character objetooLe, String benef, BigDecimal modu, String cae,
			String fechaCae, Set tranCais, Set itemses, Set tranPresus,
			Set aplicacioneses, Set impuConts) {
		this.transacNr = transacNr;
		
		this.fecha = fecha;
		
		this.nrComprob = nrComprob;
		this.letra = letra;
		this.prefijo = prefijo;
		this.vendedor = vendedor;
		this.cobrador = cobrador;
		this.condiciones = condiciones;
		this.tipoIva = tipoIva;
		this.alicuotaIva = alicuotaIva;
		this.sobreAlicIva = sobreAlicIva;
		this.alicuotaInternos = alicuotaInternos;
		this.valorMonExt = valorMonExt;
		this.fechaTransac = fechaTransac;
		this.vencim1 = vencim1;
		this.vencim2 = vencim2;
		this.vencim3 = vencim3;
		this.vencim4 = vencim4;
		this.nrSucursal = nrSucursal;
		this.nrOperador = nrOperador;
		this.observaciones = observaciones;
		this.netoGrav = netoGrav;
		this.netoNoGrav = netoNoGrav;
		this.iva = iva;
		this.ivaRni = ivaRni;
		this.ivaPercep = ivaPercep;
		
		this.retIb = retIb;
		this.retIva = retIva;
		this.retGan = retGan;
		this.retCargasSoc = retCargasSoc;
		this.descuentos = descuentos;
		this.otrosImp1 = otrosImp1;
		this.otrosImp2 = otrosImp2;
		this.otrosImp3 = otrosImp3;
		this.total = total;
		this.efectivo = efectivo;
		this.valTerceros = valTerceros;
		this.pagares = pagares;
		this.tarjetas = tarjetas;
		this.chequesPropios = chequesPropios;
		this.monExt = monExt;
		this.urgencia = urgencia;
		this.fechaEntrega = fechaEntrega;
		this.ordenCompra = ordenCompra;
		this.fechaEstPago = fechaEstPago;
		this.destino = destino;
		this.saldo = saldo;
		this.cuotas = cuotas;
		this.nrDomicilioEnt = nrDomicilioEnt;
		this.imputacionContable = imputacionContable;
		this.ingreBrutos = ingreBrutos;
		this.bultos = bultos;
		this.peso = peso;
		this.valor = valor;
		this.proce = proce;
		this.riesgo = riesgo;
		this.flete = flete;
		this.p = p;
		this.r = r;
		this.tranRemitoOrig = tranRemitoOrig;
		this.tranReciboFact = tranReciboFact;
		this.tranFactCred = tranFactCred;
		this.factCred = factCred;
		this.objetooLe = objetooLe;
		this.benef = benef;
		this.modu = modu;
		this.cae = cae;
		this.fechaCae = fechaCae;
	}

	@Id
	@Column(name = "TransacNr", unique = true, nullable = false)
	public Integer getTransacNr() {
		return this.transacNr;
	}

	public void setTransacNr(Integer transacNr) {
		this.transacNr = transacNr;
	}


	@Column(name = "Fecha", nullable = false, length = 16)
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	
	@Column(name = "NrComprob", nullable = false, length = 8)
	public String getNrComprob() {
		return this.nrComprob;
	}

	public void setNrComprob(String nrComprob) {
		this.nrComprob = nrComprob;
	}

	@Column(name = "Letra", nullable = false, length = 1)
	public String getLetra() {
		return this.letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	@Column(name = "Prefijo", nullable = false, length = 4)
	public String getPrefijo() {
		return this.prefijo;
	}

	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}

	@Column(name = "Vendedor", nullable = false)
	public Integer getVendedor() {
		return this.vendedor;
	}

	public void setVendedor(Integer vendedor) {
		this.vendedor = vendedor;
	}

	@Column(name = "Cobrador", nullable = false)
	public Integer getCobrador() {
		return this.cobrador;
	}

	public void setCobrador(Integer cobrador) {
		this.cobrador = cobrador;
	}

	@Column(name = "Condiciones", nullable = false)
	public Integer getCondiciones() {
		return this.condiciones;
	}

	public void setCondiciones(Integer condiciones) {
		this.condiciones = condiciones;
	}

	@Column(name = "TipoIva", nullable = false)
	public Integer getTipoIva() {
		return this.tipoIva;
	}

	public void setTipoIva(Integer tipoIva) {
		this.tipoIva = tipoIva;
	}

	@Column(name = "AlicuotaIva", nullable = false, precision = 24, scale = 0)
	public Float getAlicuotaIva() {
		return this.alicuotaIva;
	}

	public void setAlicuotaIva(Float alicuotaIva) {
		this.alicuotaIva = alicuotaIva;
	}

	@Column(name = "SobreAlicIva", nullable = false, precision = 24, scale = 0)
	public Float getSobreAlicIva() {
		return this.sobreAlicIva;
	}

	public void setSobreAlicIva(Float sobreAlicIva) {
		this.sobreAlicIva = sobreAlicIva;
	}

	@Column(name = "AlicuotaInternos", nullable = false, precision = 24, scale = 0)
	public Float getAlicuotaInternos() {
		return this.alicuotaInternos;
	}

	public void setAlicuotaInternos(Float alicuotaInternos) {
		this.alicuotaInternos = alicuotaInternos;
	}

	@Column(name = "ValorMonExt", nullable = false, precision = 24, scale = 0)
	public Float getValorMonExt() {
		return this.valorMonExt;
	}

	public void setValorMonExt(Float valorMonExt) {
		this.valorMonExt = valorMonExt;
	}

	@Column(name = "FechaTransac", nullable = false, length = 23)
	public Date getFechaTransac() {
		return this.fechaTransac;
	}

	public void setFechaTransac(Date fechaTransac) {
		this.fechaTransac = fechaTransac;
	}

	@Column(name = "Vencim1", nullable = false)
	public Short getVencim1() {
		return this.vencim1;
	}

	public void setVencim1(Short vencim1) {
		this.vencim1 = vencim1;
	}

	@Column(name = "Vencim2", nullable = false)
	public Short getVencim2() {
		return this.vencim2;
	}

	public void setVencim2(Short vencim2) {
		this.vencim2 = vencim2;
	}

	@Column(name = "Vencim3", nullable = false)
	public Short getVencim3() {
		return this.vencim3;
	}

	public void setVencim3(Short vencim3) {
		this.vencim3 = vencim3;
	}

	@Column(name = "Vencim4", nullable = false)
	public Short getVencim4() {
		return this.vencim4;
	}

	public void setVencim4(Short vencim4) {
		this.vencim4 = vencim4;
	}

	@Column(name = "NrSucursal", nullable = false)
	public Integer getNrSucursal() {
		return this.nrSucursal;
	}

	public void setNrSucursal(Integer nrSucursal) {
		this.nrSucursal = nrSucursal;
	}

	@Column(name = "NrOperador", nullable = true)
	public Integer getNrOperador() {
		return this.nrOperador;
	}

	public void setNrOperador(Integer nrOperador) {
		this.nrOperador = nrOperador;
	}

	@Column(name = "Observaciones", length = 256)
	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Column(name = "NetoGrav", nullable = false, scale = 4)
	public BigDecimal getNetoGrav() {
		return this.netoGrav;
	}

	public void setNetoGrav(BigDecimal netoGrav) {
		this.netoGrav = netoGrav;
	}

	@Column(name = "NetoNoGrav", nullable = false, scale = 4)
	public BigDecimal getNetoNoGrav() {
		return this.netoNoGrav;
	}

	public void setNetoNoGrav(BigDecimal netoNoGrav) {
		this.netoNoGrav = netoNoGrav;
	}

	@Column(name = "Iva", nullable = false, scale = 4)
	public BigDecimal getIva() {
		return this.iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	@Column(name = "IvaRni", nullable = false, scale = 4)
	public BigDecimal getIvaRni() {
		return this.ivaRni;
	}

	public void setIvaRni(BigDecimal ivaRni) {
		this.ivaRni = ivaRni;
	}

	@Column(name = "IvaPercep", nullable = false, scale = 4)
	public BigDecimal getIvaPercep() {
		return this.ivaPercep;
	}

	public void setIvaPercep(BigDecimal ivaPercep) {
		this.ivaPercep = ivaPercep;
	}


	@Column(name = "RetIb", nullable = false, scale = 4)
	public BigDecimal getRetIb() {
		return this.retIb;
	}

	public void setRetIb(BigDecimal retIb) {
		this.retIb = retIb;
	}

	@Column(name = "RetIva", nullable = false, scale = 4)
	public BigDecimal getRetIva() {
		return this.retIva;
	}

	public void setRetIva(BigDecimal retIva) {
		this.retIva = retIva;
	}

	@Column(name = "RetGan", nullable = false, scale = 4)
	public BigDecimal getRetGan() {
		return this.retGan;
	}

	public void setRetGan(BigDecimal retGan) {
		this.retGan = retGan;
	}

	@Column(name = "RetCargasSoc", nullable = false, scale = 4)
	public BigDecimal getRetCargasSoc() {
		return this.retCargasSoc;
	}

	public void setRetCargasSoc(BigDecimal retCargasSoc) {
		this.retCargasSoc = retCargasSoc;
	}

	@Column(name = "Descuentos", nullable = false, scale = 4)
	public BigDecimal getDescuentos() {
		return this.descuentos;
	}

	public void setDescuentos(BigDecimal descuentos) {
		this.descuentos = descuentos;
	}

	@Column(name = "OtrosImp1", nullable = false, scale = 4)
	public BigDecimal getOtrosImp1() {
		return this.otrosImp1;
	}

	public void setOtrosImp1(BigDecimal otrosImp1) {
		this.otrosImp1 = otrosImp1;
	}

	@Column(name = "OtrosImp2", nullable = false, scale = 4)
	public BigDecimal getOtrosImp2() {
		return this.otrosImp2;
	}

	public void setOtrosImp2(BigDecimal otrosImp2) {
		this.otrosImp2 = otrosImp2;
	}

	@Column(name = "OtrosImp3", nullable = false, scale = 4)
	public BigDecimal getOtrosImp3() {
		return this.otrosImp3;
	}

	public void setOtrosImp3(BigDecimal otrosImp3) {
		this.otrosImp3 = otrosImp3;
	}

	@Column(name = "Total", nullable = false, scale = 4)
	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@Column(name = "Efectivo", nullable = false, scale = 4)
	public BigDecimal getEfectivo() {
		return this.efectivo;
	}

	public void setEfectivo(BigDecimal efectivo) {
		this.efectivo = efectivo;
	}

	@Column(name = "ValTerceros", nullable = false, scale = 4)
	public BigDecimal getValTerceros() {
		return this.valTerceros;
	}

	public void setValTerceros(BigDecimal valTerceros) {
		this.valTerceros = valTerceros;
	}

	@Column(name = "Pagares", nullable = false, scale = 4)
	public BigDecimal getPagares() {
		return this.pagares;
	}

	public void setPagares(BigDecimal pagares) {
		this.pagares = pagares;
	}

	@Column(name = "Tarjetas", nullable = false, scale = 4)
	public BigDecimal getTarjetas() {
		return this.tarjetas;
	}

	public void setTarjetas(BigDecimal tarjetas) {
		this.tarjetas = tarjetas;
	}

	@Column(name = "ChequesPropios", nullable = false, scale = 4)
	public BigDecimal getChequesPropios() {
		return this.chequesPropios;
	}

	public void setChequesPropios(BigDecimal chequesPropios) {
		this.chequesPropios = chequesPropios;
	}

	@Column(name = "MonExt", nullable = false, scale = 4)
	public BigDecimal getMonExt() {
		return this.monExt;
	}

	public void setMonExt(BigDecimal monExt) {
		this.monExt = monExt;
	}

	@Column(name = "Urgencia", length = 15)
	public String getUrgencia() {
		return this.urgencia;
	}

	public void setUrgencia(String urgencia) {
		this.urgencia = urgencia;
	}

	@Column(name = "FechaEntrega", length = 16)
	public Date getFechaEntrega() {
		return this.fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	@Column(name = "OrdenCompra", length = 15)
	public String getOrdenCompra() {
		return this.ordenCompra;
	}

	public void setOrdenCompra(String ordenCompra) {
		this.ordenCompra = ordenCompra;
	}

	@Column(name = "FechaEstPago", length = 16)
	public Date getFechaEstPago() {
		return this.fechaEstPago;
	}

	public void setFechaEstPago(Date fechaEstPago) {
		this.fechaEstPago = fechaEstPago;
	}

	@Column(name = "Destino")
	public Integer getDestino() {
		return this.destino;
	}

	public void setDestino(Integer destino) {
		this.destino = destino;
	}

	@Column(name = "Saldo", nullable = false, scale = 4)
	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	@Column(name = "Cuotas")
	public Short getCuotas() {
		return this.cuotas;
	}

	public void setCuotas(Short cuotas) {
		this.cuotas = cuotas;
	}

	@Column(name = "NrDomicilioEnt")
	public Integer getNrDomicilioEnt() {
		return this.nrDomicilioEnt;
	}

	public void setNrDomicilioEnt(Integer nrDomicilioEnt) {
		this.nrDomicilioEnt = nrDomicilioEnt;
	}

	@Column(name = "ImputacionContable", nullable = false)
	public Integer getImputacionContable() {
		return this.imputacionContable;
	}

	public void setImputacionContable(Integer imputacionContable) {
		this.imputacionContable = imputacionContable;
	}

	@Column(name = "IngreBrutos")
	public Short getIngreBrutos() {
		return this.ingreBrutos;
	}

	public void setIngreBrutos(Short ingreBrutos) {
		this.ingreBrutos = ingreBrutos;
	}

	@Column(name = "Bultos", precision = 24, scale = 0)
	public Float getBultos() {
		return this.bultos;
	}

	public void setBultos(Float bultos) {
		this.bultos = bultos;
	}

	@Column(name = "Peso", precision = 24, scale = 0)
	public Float getPeso() {
		return this.peso;
	}

	public void setPeso(Float peso) {
		this.peso = peso;
	}

	@Column(name = "Valor", scale = 4)
	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Column(name = "Proce", length = 50)
	public String getProce() {
		return this.proce;
	}

	public void setProce(String proce) {
		this.proce = proce;
	}

	@Column(name = "Riesgo", length = 7)
	public String getRiesgo() {
		return this.riesgo;
	}

	public void setRiesgo(String riesgo) {
		this.riesgo = riesgo;
	}

	@Column(name = "Flete", length = 10)
	public String getFlete() {
		return this.flete;
	}

	public void setFlete(String flete) {
		this.flete = flete;
	}

	@Column(name = "p", nullable = false, scale = 4)
	public BigDecimal getP() {
		return this.p;
	}

	public void setP(BigDecimal p) {
		this.p = p;
	}

	@Column(name = "r", nullable = false, scale = 4)
	public BigDecimal getR() {
		return this.r;
	}

	public void setR(BigDecimal r) {
		this.r = r;
	}

	@Column(name = "TranRemitoOrig")
	public Integer getTranRemitoOrig() {
		return this.tranRemitoOrig;
	}

	public void setTranRemitoOrig(Integer tranRemitoOrig) {
		this.tranRemitoOrig = tranRemitoOrig;
	}

	@Column(name = "TranReciboFact")
	public Integer getTranReciboFact() {
		return this.tranReciboFact;
	}

	public void setTranReciboFact(Integer tranReciboFact) {
		this.tranReciboFact = tranReciboFact;
	}

	@Column(name = "TranFactCred")
	public Integer getTranFactCred() {
		return this.tranFactCred;
	}

	public void setTranFactCred(Integer tranFactCred) {
		this.tranFactCred = tranFactCred;
	}

	@Column(name = "FactCred", nullable = false, scale = 4)
	public BigDecimal getFactCred() {
		return this.factCred;
	}

	public void setFactCred(BigDecimal factCred) {
		this.factCred = factCred;
	}

	@Transient
	public Character getObjetooLe() {
		return this.objetooLe;
	}

	public void setObjetooLe(Character objetooLe) {
		this.objetooLe = objetooLe;
	}

	@Column(name = "Benef", length = 15)
	public String getBenef() {
		return this.benef;
	}

	public void setBenef(String benef) {
		this.benef = benef;
	}

	@Column(name = "modu", scale = 4)
	public BigDecimal getModu() {
		return this.modu;
	}

	public void setModu(BigDecimal modu) {
		this.modu = modu;
	}

	@Column(name = "CAE", length = 15)
	public String getCae() {
		return this.cae;
	}

	public void setCae(String cae) {
		this.cae = cae;
	}

	@Column(name = "FechaCae", length = 15)
	public String getFechaCae() {
		return this.fechaCae;
	}

	public void setFechaCae(String fechaCae) {
		this.fechaCae = fechaCae;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "genteNr")
	public Gente getGente() {
		return gente;
	}

	public void setGente(Gente gente) {
		this.gente = gente;
	}
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TipoComprob")	
	public TipoComprob getTipoComprob() {
		return tipoComprob;
	}

	public void setTipoComprob(TipoComprob tipoComprob) {
		this.tipoComprob = tipoComprob;
	}
	
	
	@Transient
	public String getSigno() {				
		return FormatUtil.getSignoRow(tipoComprob.getNr());
	}


	public void setSigno(String signo) {
		this.signo = signo;
	}

	@Transient
	public String getSignoN() {						
		return FormatUtil.getSignoPorNegativoRow(this.saldoAcumulado);
	}
	
	@Transient
	public Double getSaldoCalculado() {
		Double result = 0.0d;
		BigDecimal saldoCalculado = FormatUtil.getSaldoCalculado(tipoComprob.getFactorCtaCte(),saldo);
		if (saldoCalculado != null){
			result = MathUtil.redondear(saldoCalculado.doubleValue());
		}else{
			try{
				result = saldoCalculado.doubleValue();	
			}catch(Exception e){
				result = 0.0d;
			}			
			
		}
		
		return result;		
		
	}

	@Transient
	public Double getTotalCalculado() {
		Double result = 0.0d;		
		if (total != null){
			result = MathUtil.redondear(total.doubleValue());
		}else{
			try{
				result = total.doubleValue();	
			}catch(Exception e){
				result = 0.0d;
			}			
			
		}		
		return result;		
		
	}

	public void setSaldoCalculado(BigDecimal saldoCalculado) {
		this.saldoCalculado = saldoCalculado;
	}

	@Transient
	public String getformattedDate() {
		String fechaComp = null;
		if (this.fecha != null)
			fechaComp = DateUtil.getFormatedSTDDate(new Timestamp(this.fecha.getTime()));
		return fechaComp;
	}

	@Transient
	public String getformattedAAAADate() {
		String fechaComp = null;
		if (this.fecha != null)
			fechaComp = DateUtil.getFormatedSTDDate(new Timestamp(this.fecha.getTime()));
		return fechaComp;
	}

	@Transient
	public String getRutaComprobantePdf() {
		return rutaComprobantePdf;
	}

	public void setRutaComprobantePdf(String rutaComprobantePdf) {
		this.rutaComprobantePdf = rutaComprobantePdf;
	}
	
	@Transient
	public Boolean getTienePdf() {
		if (rutaComprobantePdf == null || rutaComprobantePdf == "")
			return false;
		else
			return true;
	}
	
	
	@Transient	
	public Double getSaldoAcumulado() {
		Double result = 0.0d;		
		if (saldoAcumulado != null){
			result = MathUtil.redondear(saldoAcumulado.doubleValue());
		}else{
			try{
				result = saldoAcumulado.doubleValue();	
			}catch(Exception e){
				result = 0.0d;
			}			
			
		}		
		return result;		
	}

	public void setSaldoAcumulado(Double saldoAcumulado) {
		this.saldoAcumulado = saldoAcumulado;
	}

	@Transient
	public String getShortObservaciones() {
		if(observaciones != null && observaciones.length() > 30)
			shortObservaciones = observaciones.substring(0, 29);
		return shortObservaciones;
	}

	public void setShortObservaciones(String shortObservaciones) {
		this.shortObservaciones = shortObservaciones;
	}

	@OneToMany(cascade=CascadeType.ALL,targetEntity=Items.class,fetch=FetchType.LAZY,mappedBy="id.transac")
	public List<Items> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<Items> itemsList) {
		this.itemsList = itemsList;
	}
	
}
