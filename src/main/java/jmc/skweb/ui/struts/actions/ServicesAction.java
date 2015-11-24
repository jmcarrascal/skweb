package jmc.skweb.ui.struts.actions;



import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jmc.skweb.core.model.ArtMadre;
import jmc.skweb.core.model.Condi;
import jmc.skweb.core.model.Domicilios;
import jmc.skweb.core.model.EstadiTipoReporte;
import jmc.skweb.core.model.ExiArt;
import jmc.skweb.core.model.Fam;
import jmc.skweb.core.model.Gente;
import jmc.skweb.core.model.ImagenesArticulos;
import jmc.skweb.core.model.Items;
import jmc.skweb.core.model.Paseban;
import jmc.skweb.core.model.Stock;
import jmc.skweb.core.model.StockPiezas;
import jmc.skweb.core.model.SubFam;
import jmc.skweb.core.model.TipoComprob;
import jmc.skweb.core.model.Transac;
import jmc.skweb.core.model.Usuario;
import jmc.skweb.core.model.report.DatosReporte;
import jmc.skweb.core.model.report.GroupCantTransac;
import jmc.skweb.core.model.report.SaldoAcumulado;
import jmc.skweb.core.model.report.SaldoAcumuladoReport;
import jmc.skweb.core.model.report.TipoReporte;
import jmc.skweb.core.model.report.TransacReport;
import jmc.skweb.core.model.shortEntities.GenteBasic;
import jmc.skweb.core.model.shortEntities.OptionsSelect;
import jmc.skweb.core.model.traza.Trazabi;
import jmc.skweb.core.service.ArticuloManager;
import jmc.skweb.core.service.CuentaCorrienteManager;
import jmc.skweb.core.service.ParametrizacionManager;
import jmc.skweb.core.service.ReportManager;
import jmc.skweb.core.service.TesoreriaManager;
import jmc.skweb.core.service.TransaccionManager;
import jmc.skweb.core.service.UsuarioManager;
import jmc.skweb.util.Constants;
import jmc.skweb.util.FormatUtil;
import jmc.skweb.util.MathUtil;
import jmc.skweb.util.PreferenciasUtil;
import jmc.skweb.util.email.Email;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.context.HttpServletRequestContext;
import org.extremecomponents.table.core.TableConstants;
import org.extremecomponents.table.limit.Filter;
import org.extremecomponents.table.limit.Limit;
import org.extremecomponents.table.limit.LimitFactory;
import org.extremecomponents.table.limit.TableLimit;
import org.extremecomponents.table.limit.TableLimitFactory;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;





/**
 * @author vaio
 *
 */
public class ServicesAction extends ActionSupport  {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nombreArticulo;
	private ArticuloManager articuloManager;
	private CuentaCorrienteManager cuentaCorrienteManager;
	private Stock stock = new Stock();
	private Fam fam = new Fam();
	private List<Fam> listFam = new ArrayList<Fam>();
	private List<SubFam> listSubFam = new ArrayList<SubFam>();
	private SubFam subFam = new SubFam();			
	private String nombreProveedor;
	private Gente gente = new Gente();
	private ArtMadre artMadre = new  ArtMadre();
	private Double totalSaldo = 0d;
	private Double saldoAnterior = 0d;
	private Transac transac = new Transac();
	private String fechaDesde = "";
	private String fechaHasta = "";
	private TesoreriaManager tesoreriaManager;
	private String serie;
	private String cuenta;
	private TransaccionManager transaccionManager;
	private Items items;
	private String bonif1;
	private String bonif2;
	private String bonif3;
	private String bonif4;
	private String countItems;
	private String fechaEntrega;
	private Integer tabSelected;
	private boolean articuloActivo = true;
	private UsuarioManager usuarioManager;
	private Integer carrito = 0;
	private Integer busquedaPorId = 0;
	private String piezas[];
	private Integer idCombo;
	private Integer usaSeleccion;
	private String clave;
	private ReportManager reportManager;
	private DatosReporte datosReporte = new DatosReporte();
	private Integer tipoValorMasivo;
	private String itemsSelecionados;
	private boolean muestroBarraLateral;
	
	
	
	
	
	
	public boolean isMuestroBarraLateral() {
		return muestroBarraLateral;
	}


	public void setMuestroBarraLateral(boolean muestroBarraLateral) {
		this.muestroBarraLateral = muestroBarraLateral;
	}


	public String getItemsSelecionados() {
		return itemsSelecionados;
	}


	public void setItemsSelecionados(String itemsSelecionados) {
		this.itemsSelecionados = itemsSelecionados;
	}


	public Integer getTipoValorMasivo() {
		return tipoValorMasivo;
	}


	public void setTipoValorMasivo(Integer tipoValorMasivo) {
		this.tipoValorMasivo = tipoValorMasivo;
	}


	public DatosReporte getDatosReporte() {
		return datosReporte;
	}


	public void setDatosReporte(DatosReporte datosReporte) {
		this.datosReporte = datosReporte;
	}


	public ReportManager getReportManager() {
		return reportManager;
	}


	public void setReportManager(ReportManager reportManager) {
		this.reportManager = reportManager;
	}


	public String getClave() {
		return clave;
	}


	public void setClave(String clave) {
		this.clave = clave;
	}


	public Integer getIdCombo() {
		return idCombo;
	}


	public void setIdCombo(Integer idCombo) {
		this.idCombo = idCombo;
	}


	public Integer getUsaSeleccion() {
		return usaSeleccion;
	}


	public void setUsaSeleccion(Integer usaSeleccion) {
		this.usaSeleccion = usaSeleccion;
	}


	public String[] getPiezas() {
		return piezas;
	}


	public void setPiezas(String[] piezas) {
		this.piezas = piezas;
	}


	public int getBusquedaPorId() {
		return busquedaPorId;
	}


	public void setBusquedaPorId(int busquedaPorId) {
		this.busquedaPorId = busquedaPorId;
	}


	public Integer getCarrito() {
		return carrito;
	}


	public void setCarrito(Integer carrito) {
		this.carrito = carrito;
	}


	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}


	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}


	public boolean isArticuloActivo() {
		return articuloActivo;
	}


	public void setArticuloActivo(boolean articuloActivo) {
		this.articuloActivo = articuloActivo;
	}


	public Integer getTabSelected() {
		return tabSelected;
	}


	public void setTabSelected(Integer tabSelected) {
		this.tabSelected = tabSelected;
	}


	public String getFechaEntrega() {
		return fechaEntrega;
	}


	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}


	public String getCountItems() {
		return countItems;
	}


	public void setCountItems(String countItems) {
		this.countItems = countItems;
	}


	public String getBonif1() {
		return bonif1;
	}


	public void setBonif1(String bonif1) {
		this.bonif1 = bonif1;
	}


	public String getBonif2() {
		return bonif2;
	}


	public void setBonif2(String bonif2) {
		this.bonif2 = bonif2;
	}


	public String getBonif3() {
		return bonif3;
	}


	public void setBonif3(String bonif3) {
		this.bonif3 = bonif3;
	}


	public String getBonif4() {
		return bonif4;
	}


	public void setBonif4(String bonif4) {
		this.bonif4 = bonif4;
	}


	public Items getItems() {
		return items;
	}


	public void setItems(Items items) {
		this.items = items;
	}


	public TransaccionManager getTransaccionManager() {
		return transaccionManager;
	}


	public void setTransaccionManager(TransaccionManager transaccionManager) {
		this.transaccionManager = transaccionManager;
	}


	public String getSerie() {
		return serie;
	}


	public void setSerie(String serie) {
		this.serie = serie;
	}


	public String getCuenta() {
		return cuenta;
	}


	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}


	public TesoreriaManager getTesoreriaManager() {
		return tesoreriaManager;
	}


	public void setTesoreriaManager(TesoreriaManager tesoreriaManager) {
		this.tesoreriaManager = tesoreriaManager;
	}


	public Double getSaldoAnterior() {
		return saldoAnterior;
	}


	public void setSaldoAnterior(Double saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}


	public String getFechaHasta() {
		return fechaHasta;
	}


	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}


	public String getFechaDesde() {
		return fechaDesde;
	}


	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}


	public Transac getTransac() {
		return transac;
	}


	public void setTransac(Transac transac) {
		this.transac = transac;
	}


	public Double getTotalSaldo() {
		return totalSaldo;
	}


	public void setTotalSaldo(Double totalSaldo) {
		this.totalSaldo = totalSaldo;
	}


	public ArtMadre getArtMadre() {
		return artMadre;
	}


	public void setArtMadre(ArtMadre artMadre) {
		this.artMadre = artMadre;
	}


	public CuentaCorrienteManager getCuentaCorrienteManager() {
		return cuentaCorrienteManager;
	}


	public void setCuentaCorrienteManager(
			CuentaCorrienteManager cuentaCorrienteManager) {
		this.cuentaCorrienteManager = cuentaCorrienteManager;
	}


	public Gente getGente() {
		return gente;
	}


	public void setGente(Gente gente) {
		this.gente = gente;
	}


	public String getNombreProveedor() {
		return nombreProveedor;
	}


	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}


	public SubFam getSubFam() {
		return subFam;
	}


	public void setSubFam(SubFam subFam) {
		this.subFam = subFam;
	}


	public List<SubFam> getListSubFam() {
		return listSubFam;
	}


	public void setListSubFam(List<SubFam> listSubFam) {
		this.listSubFam = listSubFam;
	}


	public List<Fam> getListFam() {
		return listFam;
	}


	public void setListFam(List<Fam> listFam) {
		this.listFam = listFam;
	}


	public Fam getFam() {
		return fam;
	}


	public void setFam(Fam fam) {
		this.fam = fam;
	}


	public Stock getStock() {
		return stock;
	}


	public void setStock(Stock stock) {
		this.stock = stock;
	}


	public ArticuloManager getArticuloManager() {
		return articuloManager;
	}


	public void setArticuloManager(ArticuloManager articuloManager) {
		this.articuloManager = articuloManager;
	}


	public String getNombreArticulo() {
		return nombreArticulo;
	}


	public void setNombreArticulo(String nombreArticulo) {
		this.nombreArticulo = nombreArticulo;
	}

	private ParametrizacionManager parametrizacionManager;
	public ParametrizacionManager getParametrizacionManager() {
		return parametrizacionManager;
	}


	public void setParametrizacionManager(
			ParametrizacionManager parametrizacionManager) {
		this.parametrizacionManager = parametrizacionManager;
	}


	public String execute() throws Exception {
    
    return "success";
	}
  
	/**
	 * Redirige la APP a la pagina de Login 
	 * @return
	 * @throws Exception
	 */
	public String login(){	        
	    return "success";
	}
			
	public String preparedReportes(){				
	    return "success";
	}
	
	
public String preparedEstadistica(){
		
		//Cargo los valores de Tipo de Reporte 
		List<EstadiTipoReporte> tipoReporteList = transaccionManager.getEstadiTipoReporte();
		
		// Lista de TipoComprob
		ActionContext.getContext().getSession().put("tipoReporteList", tipoReporteList);

		if (getUsuarioSesion().getRol() == Constants.ID_USR_VENDEDOR){
			//Cargo los valores de Tipo de Reporte 
			List<GenteBasic> clienteList = articuloManager.getClienteBasicPorVendedor(getUsuarioSesion().getVendedorNr());
			// Lista de TipoComprob
			ActionContext.getContext().getSession().put("clienteList", clienteList);
		}
		
		if (getUsuarioSesion().getRol() == Constants.ID_USR_CLIENTE){
			//Cargo los valores de Tipo de Reporte 
			List<Gente> clienteList = new ArrayList<Gente>(); 
			Gente gente = articuloManager.getGentePorPK(getUsuarioSesion().getGenteNr());
			
			clienteList.add(gente);
			// Lista de TipoComprob
			ActionContext.getContext().getSession().put("clienteList", clienteList);
		}
		
		gente.setIdRol(getUsuarioSesion().getRol());
		gente.setGenteNr(getUsuarioSesion().getGenteNr());
		if (getUsuarioSesion().getGenteSession() != null){
			datosReporte.setAgendadoDesde(getUsuarioSesion().getGenteSession().getGenteNr());
			datosReporte.setAgendadoHasta(getUsuarioSesion().getGenteSession().getGenteNr());
		}
	    return "success";
	}
	
	
	/**
	 * Set TabSelected
	 */
	public String setTabSelected() throws Exception {	        		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		
		Usuario usuario = getUsuarioSesion();
		
		usuario.setTabSelected(tabSelected);
		
		request.getSession().setAttribute("usuario", usuario);
		
		return null;
	}
	
	/**
	 * Llama al menu de agendados
	 */
	public String menuAgendado_Tran() throws Exception {	        		
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		
		Usuario usuario = getUsuarioSesion();
		
		Gente genteTmp = null;
		if (gente.getGenteNr() == null){
			genteTmp = usuario.getGenteSession();
		}else{
			genteTmp = transaccionManager.getGenteConDomiPrinc(gente.getGenteNr());
			usuario.setGenteSession(genteTmp);
			usuario.setTabSelected(0);
			request.getSession().setAttribute("usuario", usuario);
		}												
		
		tabSelected = usuario.getTabSelected();			
		
		gente = genteTmp;

		gente.setIdRol(usuario.getRol());
		
		//Cargo los valores de los comprobantes permitidos
		List<TipoComprob> tipoComrobList= transaccionManager.getTipoComprobEnable(usuario.getRol());
		
		// Lista de TipoComprob
		ActionContext.getContext().getSession().put("tipoComrobList", tipoComrobList);

		//Cargo los valores de todos los comprobantes
		List<TipoComprob> tipoComprobAllList= transaccionManager.getTipoComprobAll();
		
		// Lista de TipoComprob
		ActionContext.getContext().getSession().put("tipoComprobAllList", tipoComprobAllList);
		
		//Cargo los valores de Tipo de Reporte
		List<TipoReporte> tipoReporteList= transaccionManager.getTipoReporteAll();
		
		// Lista de TipoComprob
		ActionContext.getContext().getSession().put("tipoReporteList", tipoReporteList);

		//Cargo los valores de Tipo de Reporte
		List<TipoReporte> tipoRepoTesoList= transaccionManager.getTipoReporteTesoreria();
		
		// Lista de TipoComprob
		ActionContext.getContext().getSession().put("tipoRepoTesoList", tipoRepoTesoList);
		
		//Cargo los valores de Tipo de Reporte Estadistica
		List<TipoReporte> tipoRepoEstadisList= transaccionManager.getTipoReporteEstadistica();
		
		// Lista de TipoComprob
		ActionContext.getContext().getSession().put("tipoRepoEstadisList", tipoRepoEstadisList);

		
		
		return "success";
	}

	/**
	 * Cierra la session de la APP y redirige al Login
	 * @return
	 * @throws Exception
	 */
	public String out() throws Exception {	        		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);		
		
		request.getSession().setAttribute("usuario", null);
		request.setAttribute("usuario", null);
		request.getSession().invalidate();
		
		return "success";
	}
	
	public String close() throws Exception {	        		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);				
		request.getSession().setAttribute("usuario", null);
		request.setAttribute("usuario", null);
		request.getSession().invalidate();
		return null;
	}

	private static void openURL(String url){
		URL method = null; 
		try {
			method = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			method.openStream();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Este metodo devuelve el usuario de la session actual
	 * @return Usuario actual
	 */
	private Usuario getUsuarioSesion() {
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);		
		return (Usuario)request.getSession().getAttribute("usuario");
	}

	

	
	public String home() throws Exception {	        		
		return "success";
	}

	/**
	 * Prepara la busqueda de un Articulo por Nombre.
	 * 
	 */
	public String preparedFindArticuloPorNombre() throws Exception {	        		
						
		return "success";
	}
	
	/**
	 * Prepara la busqueda de un Agendado por Nombre.
	 * 
	 */
	public String preparedFindAgendadoPorNombre_Resumen_CC() throws Exception {	        		
						
		return "success";
	}
	

	/**
	 * Prepara la busqueda de un Agendado por Nombre.
	 * 
	 */
	public String preparedFindAgendadoPorNombre_Resumen_CC_C() throws Exception {	        								
		Usuario usuario = getUsuarioSesion();
		if (usuario.getRol() == Constants.ID_USR_GERENTE){
			return "buscoAgendado";
		}else{
			return "showResumenC";
		}		
	}
	
	/**
	 * Prepara la busqueda de un Agendado por Nombre.
	 * 
	 */
	public String preparedFindAgendadoPorNombre_Resumen_CC_P_C() throws Exception {	        								
		Usuario usuario = getUsuarioSesion();
		if (usuario.getRol() == Constants.ID_USR_GERENTE){
			return "buscoAgendado";
		}else{
			return "showResumenC";
		}		
	}
	
	

	/**
	 * 
	 * Prepara la busqueda de un Agendado por Nombre.
	 * 
	 */
	public String preparedFindAgendadoPorNombre_CR() throws Exception {	        		
						
		return "success";
	}
	
	/**
	 * Prepara la busqueda de un Agendado por Nombre.
	 * 
	 */
	public String preparedFindAgendadoPorNombre_Tran() throws Exception {	        		
		//Cargo los tipo de Comprobante habilitados
		Usuario usuario = getUsuarioSesion();
		
		//Cargo los valores de los comprobantes permitidos
		List<TipoComprob> tipoComrobList= transaccionManager.getTipoComprobEnable(usuario.getRol());
		
		// Lista de TipoComprob
		ActionContext.getContext().getSession().put("tipoComrobList", tipoComrobList);

		//Cargo los valores de todos los comprobantes
		List<TipoComprob> tipoComprobAllList= transaccionManager.getTipoComprobAll();
		
		// Lista de TipoComprob
		ActionContext.getContext().getSession().put("tipoComprobAllList", tipoComprobAllList);
		
		//Cargo los valores de Tipo de Reporte
		List<TipoReporte> tipoReporteList= transaccionManager.getTipoReporteAll();
		
		// Lista de TipoComprob
		ActionContext.getContext().getSession().put("tipoReporteList", tipoReporteList);

		//Cargo los valores de Tipo de Reporte
		List<TipoReporte> tipoRepoTesoList= transaccionManager.getTipoReporteTesoreria();
		
		// Lista de TipoComprob
		ActionContext.getContext().getSession().put("tipoRepoTesoList", tipoRepoTesoList);
		
		
		//Cargo los valores de Tipo de Reporte Estadistica
		List<TipoReporte> tipoRepoEstadisList= transaccionManager.getTipoReporteEstadistica();
		
		// Lista de TipoComprob
		ActionContext.getContext().getSession().put("tipoRepoEstadisList", tipoRepoEstadisList);
		if (getUsuarioSesion().getRol() == Constants.ID_USR_CLIENTE){
			gente = articuloManager.getGentePorPK(getUsuarioSesion().getGenteNr());			
			return "goTransac";
		}
		return "success";
	}

	/**
	 * Prepara la busqueda de un Articulo Madre por Nombre.
	 * 
	 */
	public String preparedFindArtMadrePorNombre() throws Exception {	        		
						
		return "success";
	}

	
	/**
	 * Prepara la busqueda de un Articulo por Flia.
	 * 
	 */
	public String preparedFindArticuloPorFlia() throws Exception {	        		
		//Obtengo lista de SubFamilia
		listSubFam = articuloManager.getOptionsDefault();
		
		//Obtengo lista de Familia
		listFam = articuloManager.getListFam();
		
		// carga la lista de Familias
		ActionContext.getContext().getSession().put("listFam", listFam);

		// carga la lista de Sub Familias
		ActionContext.getContext().getSession().put("listSubFam", listSubFam);
				
		return "success";
	}

	/**
	 * Prepara la busqueda de un Articulo por Flia.
	 * 
	 */
	public String preparedFindArticuloPorFlia_Carr() throws Exception {	        		
		//Tengo que guardar el genteNr
		switch (getUsuarioSesion().getRol()) {
		case Constants.ID_USR_CLIENTE:
			gente = articuloManager.getGentePorPK(getUsuarioSesion().getGenteNr());
			break;
		case Constants.ID_USR_GERENTE:
			if(gente == null || gente.getGenteNr() == null){
				gente = articuloManager.getGentePorPK(getUsuarioSesion().getGenteSession().getGenteNr());
			}else{
				gente = articuloManager.getGentePorPK(getUsuarioSesion().getGenteSession().getGenteNr());
			}
			break;
		case Constants.ID_USR_VENDEDOR:
			if(gente == null || gente.getGenteNr() == null){
				gente = articuloManager.getGentePorPK(getUsuarioSesion().getGenteSession().getGenteNr());
			}else{
				gente = articuloManager.getGentePorPK(getUsuarioSesion().getGenteSession().getGenteNr());
			}
			break;
		case Constants.ID_USR_OPERADOR:
			gente = articuloManager.getGentePorPK(gente.getGenteNr());
			break;
		}
		
		getUsuarioSesion().setGenteSession(gente);
		//Obtengo lista de SubFamilia
		listSubFam = articuloManager.getOptionsDefault();
		
		//Obtengo lista de Familia
		listFam = articuloManager.getListFam();
		
		// carga la lista de Familias
		ActionContext.getContext().getSession().put("listFam", listFam);

		// carga la lista de Sub Familias
		ActionContext.getContext().getSession().put("listSubFam", listSubFam);
				
		return "success";
	}

	/**
	 * Prepara la busqueda de un Proveedor por Nombre.
	 * 
	 */
	public String preparedFindGentePorNombre_CC() throws Exception {	        		
				
		return "success";
	}
	

	/**
	 * Prepara la busqueda de un Proveedor por Nombre.
	 * 
	 */
	public String preparedFindGentePorNombre_CC_C() throws Exception {	        		
		//Preguntar si el Rol es proveedor, por S Tomar el genteNr y envialo directo Pro No ir a buscar el 
		Usuario usuario = getUsuarioSesion();
		return "showSoloImpagosC";
	}

	
	/**
	 * Prepara la busqueda de un Proveedor por Nombre.
	 * 
	 */
	public String preparedFindProveedorPorNombre() throws Exception {	        		
				
		return "success";
	}

	/**
	 * Prepara la busqueda de un Articulo por Nombre.
	 * 
	 */
	public String findArticuloPorNombre() throws Exception {	        		
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
				
		List<Stock> articuloList = articuloManager.getArticuloProNombre(nombreArticulo,articuloActivo);
		
		request.setAttribute("articuloList", articuloList);
		
		return "success";
	}
	
	/**
	 * Metodo que devuelve un array de String con las suggestions en el vector 0
	 * y  data en el vector 1
	 */
	private String[] getJSonJquery(List<Stock> articuloList){
		String[] autocomplete = new String[2];
		String suggestions = "";
		String data = "";
		for(Stock stock: articuloList){
			if(suggestions.equals(""))
				suggestions = "['" + stock.getDescripC() + "'";
			else
				suggestions = suggestions + ",'" + stock.getDescripC() + "'";
			
			if(data.equals(""))
				data = "['" + stock.getClave() + "'";
			else
				data = data + ",'" + stock.getClave() + "'";
		}
		if(!suggestions.equals(""))
			suggestions = suggestions + "]";
		if(!data.equals(""))
			data = data + "]";
		
		autocomplete[0] = suggestions;
		autocomplete[1] = data;

		return autocomplete;
	}

	
//	[
//		{ name: "Peter Pan", to: "peter@pan.de" },
//		{ name: "Molly", to: "molly@yahoo.com" },
//		{ name: "Forneria Marconi", to: "live@japan.jp" },
//		{ name: "Master <em>Sync</em>", to: "205bw@samsung.com" },
//		{ name: "Dr. <strong>Tech</strong> de Log", to: "g15@logitech.com" },
//		{ name: "Don Corleone", to: "don@vegas.com" },
//		{ name: "Mc Chick", to: "info@donalds.org" },
//		{ name: "Donnie Darko", to: "dd@timeshift.info" },
//		{ name: "Quake The Net", to: "webmaster@quakenet.org" },
//		{ name: "Dr. Write", to: "write@writable.com" }
//	]
	/**
	 * Metodo que devuelve un array de String con las suggestions en el vector 0
	 * y  data en el vector 1
	 */
	private String getAutoCompleteDataJquery(List<Stock> articuloList){			
		String data = "";
		int i = 0;
		int total = articuloList.size();
		for(Stock stock: articuloList){
			i++;
			String descrip = stock.getDescripC().replaceAll("\"", "'").replaceAll(",", " ");			
			String tmp = "{ \"clave\": " + "\""+  stock.getClave() + "\", \"valor\": " + "\"" + descrip + "\" }" ;
			if (total == i){				
			}else{
				tmp = tmp + ", ";
			}
			data = data + tmp;
		}		
		data = data + "";
		return data;
	}
	
	/**
	 * Metodo que devuelve un array de String con las suggestions en el vector 0
	 * y  data en el vector 1
	 */
	private String getAutoCompleteDataJqueryGente(List<Gente> genteList){			
		String data = "";
		int i = 0;
		int total = genteList.size();
		for(Gente gente: genteList){
			i++;
			String descrip = gente.getDescripC().replaceAll("\"", "'").replaceAll(",", " ");			
			String tmp = "{ \"clave\": " + "\""+  gente.getGenteNr() + "\", \"valor\": " + "\"" + descrip + "\" }" ;
			if (total == i){				
			}else{
				tmp = tmp + ", ";
			}
			data = data + tmp;
		}		
		data = data + "";
		return data;
	}
	

	/**
	 * Metodo que devuelve un array de String con las suggestions en el vector 0
	 * y  data en el vector 1
	 */
	private String getAutoCompleteDataJqueryGenteBasic(List<GenteBasic> genteList){			
		String data = "";
		int i = 0;
		if(genteList != null){
		int total = genteList.size();
		for(GenteBasic gente: genteList){
			i++;
			String descrip = gente.getDescripC().replaceAll("\"", "'").replaceAll(",", " ");			
			String tmp = "{ \"clave\": " + "\""+  gente.getGenteNr() + "\", \"valor\": " + "\"" + descrip + "\" }" ;
			if (total == i){				
			}else{
				tmp = tmp + ", ";
			}
			data = data + tmp;
		}		
		data = data + "";
		}
		return data;
	}

	/**
	 * 
	 * Agrega Items a la Transaccin
	 * 
	 */
	public String vaciarCarrito_Carr() throws Exception {	        												
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		
		List<Items> itemsSessionList = (List<Items>)request.getSession().getAttribute("items_OP");
		
		List<Items> itemsCarritoSessionList = (List<Items>)request.getSession().getAttribute("items_CARR");
	
		if(itemsCarritoSessionList != null && itemsCarritoSessionList.size()>0){
			for(Items  items_carr: itemsCarritoSessionList){
				itemsSessionList = transaccionManager.addItem(itemsSessionList, items_carr, getUsuarioSesion(),true);	
			}
		}
		
		
		request.getSession().setAttribute("items_OP", itemsSessionList);
						
		request.setAttribute("itemsList", itemsSessionList);	
		
		gente = getUsuarioSesion().getGenteSession();
		
		gente.setIdRol(getUsuarioSesion().getRol());
		
		transac = transaccionManager.calculoTotales(itemsSessionList);
		
		Transac transacOrig = (Transac)request.getSession().getAttribute("transac_tran");
		
		transac.setTipoComprob(transacOrig.getTipoComprob()); 
		
		countItems = String.valueOf(itemsSessionList.size());
		
		return "success";
	}

	
	
	/**
	 * Agrega un item a la lista de Items
	 * 
	 */
	public String changeValorMasivo() throws Exception {	        												
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		
		List<Items> itemsSessionList = (List<Items>)request.getSession().getAttribute("items_OP");
		 
		itemsSessionList = transaccionManager.changeValorMasivo(itemsSessionList, items, tipoValorMasivo, itemsSelecionados, getUsuarioSesion());
					
		request.getSession().setAttribute("items_OP", itemsSessionList);
						
		request.setAttribute("itemsList", itemsSessionList);	
		
		gente = getUsuarioSesion().getGenteSession();
		
		gente.setIdRol(getUsuarioSesion().getRol());
		
		transac = transaccionManager.calculoTotales(itemsSessionList);
		
		Transac transacOrig = (Transac)request.getSession().getAttribute("transac_tran");
		
		transac.setTipoComprob(transacOrig.getTipoComprob()); 
		
		countItems = String.valueOf(itemsSessionList.size());
		
		return "success";
	}

	
	/**
	 * Agrega un item a la lista de Items
	 * 
	 */
	public String addItem_OP() throws Exception {	        												
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		
		List<Items> itemsSessionList = (List<Items>)request.getSession().getAttribute("items_OP");
		
		
		String cadena = (String) request.getSession().getAttribute("formatCadena");
		
		if (items.getBonif() == null){
			items.setBonif(new BigDecimal(0));
		}

		if (cadena == null){
			if (items.getBonif().equals(new BigDecimal(0))){
				cadena = "00+00+00+00";
			}else{
				cadena = FormatUtil.llenoConCeros(String.valueOf(items.getBonif().intValue()),2) + "+00+00+00";
			}			
		}
		
		items.setObser(cadena);
		
		if (items.getId().getItemNr() != null && items.getId().getItemNr() !=0){
			itemsSessionList = transaccionManager.editItem(itemsSessionList, items, getUsuarioSesion());
		}else{
			//pregunto si la preferencia es de logica de Stock por piezas
			Double usaLogicaStockColor = PreferenciasUtil.comparePreferencia(getUsuarioSesion().getListPreferencias(), Constants.PREF_ID_USA_LOGICA_STOCK_COLOR);
			if (usaLogicaStockColor != null && usaLogicaStockColor == -1d){
				//Obtengo las piezas ya precargadas
				List<StockPiezas> stockPiezasList = (List<StockPiezas>)request.getSession().getAttribute("stockPiezasList");
					
				  if (stockPiezasList != null && stockPiezasList.size() > 0){
					int i = 0;  
					for(String str: piezas){
						
						System.out.println(str);
						try{
							Double cantidad = Double.parseDouble(str);
							if (cantidad > 0d){
								//Obtengo el color
								Items itemsNew = new Items();
								BeanUtils.copyProperties(itemsNew, items); 
								//itemsNew = items;
								itemsNew.setColores(stockPiezasList.get(i).getId().getColores());
								itemsNew.setCant1(new BigDecimal(cantidad));
								itemsSessionList = transaccionManager.addItem(itemsSessionList, itemsNew, getUsuarioSesion(),false);
							}
						}catch(NumberFormatException ne){
						}
						i++;
					}
				}
			}else{
				itemsSessionList = transaccionManager.addItem(itemsSessionList, items, getUsuarioSesion(),false);	
			}
			
		}
				
		request.getSession().setAttribute("items_OP", itemsSessionList);
						
		request.setAttribute("itemsList", itemsSessionList);	
		
		gente = getUsuarioSesion().getGenteSession();
		
		gente.setIdRol(getUsuarioSesion().getRol());
		
		transac = transaccionManager.calculoTotales(itemsSessionList);
		
		Transac transacOrig = (Transac)request.getSession().getAttribute("transac_tran");
		
		transac.setTipoComprob(transacOrig.getTipoComprob()); 
		
		countItems = String.valueOf(itemsSessionList.size());
		
		return "success";
	}

	 
	/**
	 * 
	 * Guardar Carrito 
	 * 
	 */
	public String guardarCarrito_Carr() throws Exception {	        												
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);		
		
		List<Items> itemsSessionList =(List<Items>)request.getSession().getAttribute("items_OP");
		
		if (itemsSessionList == null)
			itemsSessionList = new ArrayList<Items>();
		
		request.getSession().setAttribute("items_CARR", itemsSessionList);		
		
		return SUCCESS;
	}

	
	/**
	 * Agrega un item a la lista de del Carrito
	 * 
	 */
	public String addItem_Carr() throws Exception {	        												
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);		
		List<Items> itemsSessionList = (List<Items>)request.getSession().getAttribute("items_CARR");
		
		if (itemsSessionList == null)
			itemsSessionList = new ArrayList<Items>();
		
		String cadena = (String) request.getSession().getAttribute("formatCadena");
		
		if (items.getBonif() == null){
			items.setBonif(new BigDecimal(0));
		}

		if (cadena == null){
			if (items.getBonif().equals(new BigDecimal(0))){
				cadena = "00+00+00+00";
			}else{
				cadena = FormatUtil.llenoConCeros(String.valueOf(items.getBonif().intValue()),2) + "+00+00+00";
			}			
		}
		
		items.setObser(cadena);
		
		if (items.getId().getItemNr() != null && items.getId().getItemNr() !=0){
			itemsSessionList = transaccionManager.editItem(itemsSessionList, items, getUsuarioSesion());
		}else{
			itemsSessionList = transaccionManager.addItem(itemsSessionList, items, getUsuarioSesion(), true);
		}
				
		request.getSession().setAttribute("items_CARR", itemsSessionList);
						
		request.setAttribute("itemsList", itemsSessionList);	
		
		gente = getUsuarioSesion().getGenteSession();		
		
		//transac = transaccionManager.calculoTotales(itemsSessionList);
		
		//Transac transacOrig = (Transac)request.getSession().getAttribute("transac_tran");
		
		//transac.setTipoComprob(transacOrig.getTipoComprob()); 
		
		//countItems = String.valueOf(itemsSessionList.size());
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuilder salida = new StringBuilder();
        
		salida.append("OK");

		response.setContentType("text/html; charset=iso-8859-1");
        //Imprime el resultado
        try {
			sos.print(salida.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * Agrega un item a la lista de del Carrito
	 * 
	 */
	public String addItemColor_Carr() throws Exception {	        												
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);		
		List<Items> itemsSessionList = (List<Items>)request.getSession().getAttribute("items_CARR");
		
		if (itemsSessionList == null)
			itemsSessionList = new ArrayList<Items>();
		
		String cadena = (String) request.getSession().getAttribute("formatCadena");
		
		if (items.getBonif() == null){
			items.setBonif(new BigDecimal(0));
		}

		if (cadena == null){
			if (items.getBonif().equals(new BigDecimal(0))){
				cadena = "00+00+00+00";
			}else{
				cadena = FormatUtil.llenoConCeros(String.valueOf(items.getBonif().intValue()),2) + "+00+00+00";
			}			
		}
		
		items.setObser(cadena);
		
		if (items.getId().getItemNr() != null && items.getId().getItemNr() !=0){
			itemsSessionList = transaccionManager.editItem(itemsSessionList, items, getUsuarioSesion());
		}else{
			itemsSessionList = transaccionManager.addItem(itemsSessionList, items, getUsuarioSesion(), true);
		}
				
		request.getSession().setAttribute("items_CARR", itemsSessionList);
						
		request.setAttribute("itemsList", itemsSessionList);	
		
		gente = getUsuarioSesion().getGenteSession();		
		
		//transac = transaccionManager.calculoTotales(itemsSessionList);
		
		//Transac transacOrig = (Transac)request.getSession().getAttribute("transac_tran");
		
		//transac.setTipoComprob(transacOrig.getTipoComprob()); 
		
		//countItems = String.valueOf(itemsSessionList.size());
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuilder salida = new StringBuilder();
        
		salida.append("OK");

		response.setContentType("text/html; charset=iso-8859-1");
        //Imprime el resultado
        try {
			sos.print(salida.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * Guarda una imagen del articulo
	 * 
	 */
	public String uploadArticuloImage() throws Exception {	        												
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);		
				
		
		String result = articuloManager.updateArticulo(stock.getClave(), stock.getImageFile());

		String fami  = fam.getDesfam();
		
		String subfami  = subFam.getDesubfa();
		
		stock = articuloManager.getArticuloPorPK(stock.getClave());
		
		//Obtengo la lista de Existencia para el articulo
		List<ExiArt> exiArtList = articuloManager.getExistenciaPorArticulo(stock.getClave(), getUsuarioSesion());		

		//Publico la lista
		request.setAttribute("exiArtList", exiArtList);
		
		
		return "success";

	}

	
	/**
	 * Guarda una transaccion
	 * 
	 */
	public String saveTransac() throws Exception {	        												
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);		

		List<Items> itemsSessionList = (List<Items>)request.getSession().getAttribute("items_OP");				
		
		Transac transacOrig = (Transac)request.getSession().getAttribute("transac_tran");
		
		String result = transaccionManager.saveTransac_OP(itemsSessionList, gente.getGenteNr(), getUsuarioSesion().getEmpresaNrSk(), fechaEntrega, transac, transacOrig.getTipoComprob().getNr(), getUsuarioSesion());
		
		List<Items> itemsCarritoSessionList = new ArrayList<Items>();
		
		request.getSession().setAttribute("items_CARR", itemsCarritoSessionList);

		if (result.equals("error")){			
		}else{
			result = "Se ha generado la Transaccin Nr. " + result;
			
		}
		
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuilder salida = new StringBuilder();
        
		salida.append(result);

		response.setContentType("text/html; charset=iso-8859-1");
        //Imprime el resultado
        try {
			sos.print(salida.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;

	}

	
	/**
	 * Arma las imagenes
	 * 
	 */
	public String getArrayImagenes() throws Exception {	        												
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);		

		String result = "";
				
		Usuario usuario = getUsuarioSesion();
		//List<Stock> stockList = articuloManager.getImagenArticuloPorFliaSubFlia(fam.getNrfam(), subFam.getNrsubfam(), true);
		
		boolean primero = true; 
		for(Stock stock : usuario.getArticulosTmpList()){
			String clave= stock.getClave();
			if (stock.getImagenesArticulos() == null){
				clave = "SINIMG";
			}
			
			if (primero){				
				//result = "[";
				result =  result + "downLoadImageArticulo?stock.clave="+clave + "&s=.jpg";
				primero = false;
			}else{
				result =  result + "," + "downLoadImageArticulo?stock.clave=" + clave +"&s=.jpg" ;
			}							
		}
		//result = result + "]";
		
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuilder salida = new StringBuilder();
        
		salida.append(result);

		response.setContentType("text/html; charset=iso-8859-1");
        //Imprime el resultado
        try {
			sos.print(salida.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;

	}

	/**
	 * Arma los titulos
	 * 
	 */
	public String getArrayTitle() throws Exception {	        												
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);		

		String result = "";
				
		Usuario usuario = getUsuarioSesion();
		//List<Stock> stockList = articuloManager.getImagenArticuloPorFliaSubFlia(fam.getNrfam(), subFam.getNrsubfam(), true);
		
		boolean primero = true; 
		for(Stock stock : usuario.getArticulosTmpList()){
			String titulo= stock.getDescripC();
			
			if (primero){				
				//result = "[";
				result =  result + titulo;
				primero = false;
			}else{
				result =  result + "," + titulo ;
			}							
		}
		//result = result + "]";
		
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuilder salida = new StringBuilder();
        
		salida.append(result);

		response.setContentType("text/html; charset=iso-8859-1");
        //Imprime el resultado
        try {
			sos.print(salida.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;

	}

	/**
	 * Arma los Obs
	 * 
	 */
	public String getArrayObs() throws Exception {	        												
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);		

		String result = "";
				
		Usuario usuario = getUsuarioSesion();
		//List<Stock> stockList = articuloManager.getImagenArticuloPorFliaSubFlia(fam.getNrfam(), subFam.getNrsubfam(), true);
		
		boolean primero = true; 
		for(Stock stock : usuario.getArticulosTmpList()){
			String obs= stock.getObservaciones();
			obs = obs.replaceAll(",", " ");
			if (primero){				
				//result = "[";
				result =  result + obs;
				primero = false;
			}else{
				result =  result + "," + obs ;
			}							
		}
		//result = result + "]";
		
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuilder salida = new StringBuilder();
        
		salida.append(result);

		response.setContentType("text/html; charset=iso-8859-1");
        //Imprime el resultado
        try {
			sos.print(salida.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;

	}
	
	
	public String deleteItemCarr() throws Exception {	        												
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
		
		String result = "0";
		
		List<Items> itemsSessionList = (List<Items>)request.getSession().getAttribute("items_CARR");
		
		itemsSessionList = transaccionManager.removeItem(itemsSessionList, items);
		
		if (itemsSessionList.size() > 0)
			result = "1";
		request.getSession().setAttribute("items_CARR", itemsSessionList);
							
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		StringBuilder salida = new StringBuilder();
        
		salida.append(result);

		response.setContentType("text/html; charset=iso-8859-1");
        //Imprime el resultado
        try {
			sos.print(salida.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	/**
	 * Elimina un item a la lista de Items
	 * 
	 */
	public String removeItem_OP() throws Exception {	        												
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		
		List<Items> itemsSessionList = (List<Items>)request.getSession().getAttribute("items_OP");
		
		itemsSessionList = transaccionManager.removeItem(itemsSessionList, items);
		
		request.getSession().setAttribute("items_OP", itemsSessionList);
						
		request.setAttribute("itemsList", itemsSessionList);	
		
		gente = getUsuarioSesion().getGenteSession();		
		
		transac = transaccionManager.calculoTotales(itemsSessionList);
		
		Transac transacOrig = (Transac)request.getSession().getAttribute("transac_tran");
		
		transac.setTipoComprob(transacOrig.getTipoComprob()); 
		
		countItems = String.valueOf(itemsSessionList.size());
		
		return "success";
	}

	
	/**
	 * Prepara la cargar de un pedido
	 * 
	 */
	public String adminComprob_Tran() throws Exception {	        												
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		
		List<Items> itemsList = new ArrayList<Items>();
		
		request.getSession().setAttribute("items_OP", itemsList);
		TipoComprob tipoComprob = null;
		if (transac.getTipoComprob() == null){
			tipoComprob = transaccionManager.getTipoComprobByPK(Constants.ID_TIPO_COMP_PEDIDO_VENTA);	
		}else{
			tipoComprob = transaccionManager.getTipoComprobByPK(transac.getTipoComprob().getNr());
		}
		
		transac.setTipoComprob(tipoComprob);
		
		request.getSession().setAttribute("transac_tran", transac);
		
		request.setAttribute("itemsList", itemsList);
		if (getUsuarioSesion().getRol() == Constants.ID_USR_CLIENTE){
			gente = articuloManager.getGentePorPK(getUsuarioSesion().getGenteNr());			
		
			List<Items> itemsSessionList = (List<Items>)request.getSession().getAttribute("items_OP");
			
			List<Items> itemsCarritoSessionList = (List<Items>)request.getSession().getAttribute("items_CARR");
		
			if(itemsCarritoSessionList != null && itemsCarritoSessionList.size()>0){
				for(Items  items_carr: itemsCarritoSessionList){
					itemsSessionList = transaccionManager.addItem(itemsSessionList, items_carr, getUsuarioSesion(),true);	
				}
			}
			
			
			request.getSession().setAttribute("items_OP", itemsSessionList);
							
			request.setAttribute("itemsList", itemsSessionList);	
			
			gente = getUsuarioSesion().getGenteSession();
			
			gente.setIdRol(getUsuarioSesion().getRol());
			
			transac = transaccionManager.calculoTotales(itemsSessionList);
			
			Transac transacOrig = (Transac)request.getSession().getAttribute("transac_tran");
			
			transac.setTipoComprob(transacOrig.getTipoComprob()); 
			
			countItems = String.valueOf(itemsSessionList.size());
		}else{
			gente = articuloManager.getGentePorPK(gente.getGenteNr());
		}
								
		gente.setIdRol(getUsuarioSesion().getRol());
				
		countItems = String.valueOf(itemsList.size());		
		
		
		
		return "success";
	}
	
	/**
	 * Prepara el final de un pedido
	 * 
	 */
	public String finComprob_Tran() throws Exception {	        												
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
				
		List<Items> itemsSessionList = (List<Items>)request.getSession().getAttribute("items_OP");		
		
		gente = articuloManager.getGentePorPK(gente.getGenteNr());		
		
		request.setAttribute("itemsList", itemsSessionList);

		transac = transaccionManager.calculoTotales(itemsSessionList);
		
		Transac transacOrig = (Transac)request.getSession().getAttribute("transac_tran");
		
		transac.setTipoComprob(transacOrig.getTipoComprob()); 

		//Listo Domicilios para seleccionar el de la entrega
		List<Domicilios> domiciliosList = transaccionManager.getDomiciliosPorGente(gente);
		
		// Lista de Domicilios
		ActionContext.getContext().getSession().put("domiciliosList", domiciliosList);
		
		//Listo Condicin de Vta
		List<Condi> condiList = transaccionManager.getCondiAll();
		
		// Lista de Domicilios
		ActionContext.getContext().getSession().put("condiList", condiList);
		
		transac.setCondiciones(gente.getCondVta());

		return "success";
	}

	
	/**
	 * Prepara la busqueda de un Articulo por Nombre.
	 * 
	 */
	public String getBonifPorAgendado() throws Exception {	        								
		
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);

		int idGente = gente.getGenteNr();
		Gente gente  = articuloManager.getGentePorPK(idGente);
		

		
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuilder salida = new StringBuilder();
        
		salida.append(gente.getBonifStd());

		response.setContentType("text/html; charset=iso-8859-1");
        //Imprime el resultado
        try {
			sos.print(salida.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
			
	}	

	

	/**
	 * Prepara la busqueda de un Agendado por su clave.
	 * 
	 */
	public String getAgendadoPorCod_Tran() throws Exception {	        								
		
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		String nombreAgendado = "";
		System.out.println(gente.getGenteNr());
		try{
			nombreAgendado = articuloManager.getGentePorPKVendedor(gente.getGenteNr(), getUsuarioSesion()).getDescripC();				
			
		}catch(Exception nu){
			
		}
		
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuilder salida = new StringBuilder();
        
		salida.append(nombreAgendado);

		response.setContentType("text/html; charset=iso-8859-1");
        //Imprime el resultado
        try {
			sos.print(salida.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
			
	}	
	
	/**
	 * Prepara Carga Cambos.
	 * 
	 */
	public String getOptionsSelect() throws Exception {	        								
		
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
		
		List<OptionsSelect> optionsList = articuloManager.getOptionsSelect(clave, idCombo, usaSeleccion);		
		
		String result = "";
		
		
		result = FormatUtil.getOptionsSelectListHTML(optionsList);
		
		
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuilder salida = new StringBuilder();
        
		salida.append(result);

		response.setContentType("text/html; charset=iso-8859-1");
        //Imprime el resultado
        try {
			sos.print(salida.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
			
	}	
	
	/**
	 * Prepara la busqueda de un Articulo por clave.
	 * 
	 */
	public String getArticuloPorClave_OP() throws Exception {	        								
		
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		
		System.out.println(stock.getClave());
		
		//String artDescrip = transaccionManager.getArticuloPorClave(stock.getClave(), articuloActivo);
		
		//Obtengo la lista de Articulos
		List<Stock> stockList = articuloManager.getArticuloPorCodigoLike(stock.getClave(), articuloActivo, getUsuarioSesion());		
		
		String result = "";
		
		if (stockList.size() == 1){
			result = stockList.get(0).getDescripC();
		}else{
			result = FormatUtil.getListHTMLStock(stockList);
		}
		
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuilder salida = new StringBuilder();
        
		salida.append(result);

		response.setContentType("text/html; charset=iso-8859-1");
        //Imprime el resultado
        try {
			sos.print(salida.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
			
	}	

	/**
	 * 
	 * 
	 */
	public String getBonifPorCadena() throws Exception {	        								
		
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		
		BigDecimal bonif = transaccionManager.getBonifPorCadena(bonif1,bonif2,bonif3,bonif4);
		
		String formatCadena = transaccionManager.getFormatCadena(bonif1,bonif2,bonif3,bonif4);
				
		request.getSession().setAttribute("formatCadena", formatCadena);
		
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuilder salida = new StringBuilder();
        
		salida.append(bonif);

		response.setContentType("text/html; charset=iso-8859-1");
        //Imprime el resultado
        try {
			sos.print(salida.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
			
	}	

	/**
	 * Prepara la busqueda de un Articulo por Nombre.
	 * 
	 */
	public String getPrecioPorArtAgendado() throws Exception {	        								
		
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);

		System.out.println(gente.getGenteNr());
		System.out.println(stock.getClave());
		
		Usuario usuario = getUsuarioSesion();
		
		BigDecimal precio = transaccionManager.getPrecioClienteArticulo(gente.getGenteNr(), stock.getClave(), usuario, false);
		
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuilder salida = new StringBuilder();
        
		salida.append(precio);

		response.setContentType("text/html; charset=iso-8859-1");
        //Imprime el resultado
        try {
			sos.print(salida.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
			
	}	
	
	/**
	 * Muestra en stock por formula stock - pedidoVta + pedidoCompra.
	 * 
	 */
	public String getFormulaStock() throws Exception {	        								
		
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
		
		HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);						
		String result = "";
		//Pregunto si tiene implementada la logica de Stock
		if (PreferenciasUtil.comparePreferencia(getUsuarioSesion().getListPreferencias(), Constants.PREF_ID_USA_LOGICA_STOCK_COLOR) == -1d){			
			List<StockPiezas> stockPiezasList = articuloManager.getStockPiezasPorArt(stock.getClave(), getUsuarioSesion());		
			Double muestraCantidad = PreferenciasUtil.comparePreferencia(getUsuarioSesion().getListPreferencias(), Constants.PREF_ID_MUESTRA_CANTIDAD);
			//Obtengo Compras y Ventas
			//Obtengo la lista de las compras del articulo
			List<GroupCantTransac> compras = articuloManager.getOperacionByArtTipoCompr(stock.getClave(), Constants.ID_TIPO_COMP_PEDIDO_COMPRA);
			//Obtengo la lista de ventas del articulo
			List<GroupCantTransac> ventas = articuloManager.getOperacionByArtTipoCompr(stock.getClave(), Constants.ID_TIPO_COMP_PEDIDO_VENTA);
			//Recorro compras
			for (GroupCantTransac compra: compras){
				//pregunto si tengo pedido de venta
				GroupCantTransac ventaObtenida = new GroupCantTransac(BigDecimal.ZERO, compra.getColo());
				for (GroupCantTransac venta: ventas){
					if (compra.getColo() == venta.getColo()){
						ventaObtenida = venta;
					}					
				}

				//pregunto si tengo stock
				for (StockPiezas stockpieza: stockPiezasList){
					if(stockpieza.getId().getColores().getNr() == compra.getColo()){
						stockpieza.setComprasmenosventas(compra.getCant1().subtract(ventaObtenida.getCant1()).doubleValue());
					}
					
				}
			}
			
			result = FormatUtil.getListHTMLStockPiezas(stockPiezasList, muestraCantidad, false,false,null,muestroBarraLateral);			
			Integer genteNr = null;
			
			try{
				genteNr = getUsuarioSesion().getGenteSession().getGenteNr();
			}catch(Exception ee){
				
			}
			
			result = result + articuloManager.getTableResumenPedidos(stock.getClave(),getUsuarioSesion(),3 );
			
			result = result + "<h4>Precio: $" + transaccionManager.getPrecioClienteArticulo(genteNr, stock.getClave(),getUsuarioSesion(), false) + "</h4>";
		}else{
			result = articuloManager.getFormulaStock(stock.getClave(), getUsuarioSesion(), carrito);
			
		}
		
		
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuilder salida = new StringBuilder();
        
		salida.append(result);

		response.setContentType("text/html; charset=iso-8859-1");
        //Imprime el resultado
        try {
			sos.print(salida.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
			
	}	

	
	/**
	 * Muestra en un Pedido.
	 * 
	 */
	public String getTablePedido() throws Exception {	        								
		
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);						
		
		String result = transaccionManager.getTransacJoin(transac.getTipoComprob().getNr(),stock.getClave(), getUsuarioSesion());
		
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuilder salida = new StringBuilder();
        
		salida.append(result);

		response.setContentType("text/html; charset=iso-8859-1");
        //Imprime el resultado
        try {
			sos.print(salida.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
			
	}	

	/**
	 * Muestra en un Pedido.
	 * 
	 */
	public String getTableResumenPedido() throws Exception {	        								
		
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);						
		
		String result = articuloManager.getTableResumenPedidos(stock.getClave(), getUsuarioSesion(), 0);
		
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuilder salida = new StringBuilder();
        
		salida.append(result);

		response.setContentType("text/html; charset=iso-8859-1");
        //Imprime el resultado
        try {
			sos.print(salida.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
			
	}	

	
	/**
	 * Env�a el comprobante por Mail al cliente
	 * 
	 */
	public String sendComprobCliente() throws Exception {	        								
		
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);						
		
		String result = "";
		
		//Obtengo la transaccin
		Transac transacN = transaccionManager.getTransacByPK(transac.getTransacNr());
		
		//Obtengo el domicilio que tiene como descripcion @adm
		Domicilios domicilios = transaccionManager.getDomiciliosAdm(transacN.getGente());
		
		if (domicilios != null && domicilios.getInternet() != null && !domicilios.getInternet().trim().equals("")){
		
			//Obtengo el documento
			File fileDownload = cuentaCorrienteManager.getComprobantePdf(transac.getTransacNr(), getUsuarioSesion());
			
			//Obtener la direcci�n de correo del destinatario
			Email email = new Email("Simpa - Comprobante Electronico","Se adjunta el Comprobante tipo: " + transacN.getTipoComprob().getDescripcion() + " Numero : " + transacN.getPrefijo() + "-" + transacN.getNrComprob(),fileDownload, domicilios.getInternet());
			
			usuarioManager.sendMail(email);
			
			result = "<div class = space/>" +"<div class = space/>"+ "<b>Se ha enviado el correo con �xito al la direcci�n de correo " + domicilios.getInternet() + "</b>";
		
		}else{
			result = "<div class = space/>" +"<div class = space/>"+ "<b>El agendado " + transacN.getGente().getRazonSocial() + " no tiene cargada una direcci�n de correo electr�nico en su domicilio \"@adm\"</b>";
		}		
		
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuilder salida = new StringBuilder();
        
		salida.append(result);

		response.setContentType("text/html; charset=iso-8859-1");
        //Imprime el resultado
        try {
			sos.print(salida.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
			
	}	

		
	
	
	/**
	 * Prepara la busqueda de un Agendado por Nombre.
	 * 
	 */
	public String getAgendadoPorNombre() throws Exception {	        								
		
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);

		String filter = (String) request.getParameter("q");
		
		List<Gente> genteList = articuloManager.getProveedorPorNombre(filter);
						
		
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuilder salida = new StringBuilder();        				
		salida.append(getAutoCompleteDataJqueryGente(genteList));
      salida.insert(0, "[");
      salida.append("]");

		//		salida.append("[	{ name: \"Peter Pan\", to: \"peter@pan.de\" },	{ name: \"Molly\", to: \"molly@yahoo.com\" },	{ name: \"Forneria Marconi\", to: \"live@japan.jp\" },	{ name: \"Master <em>Sync</em>\", to: \"205bw@samsung.com\" },	{ name: \"Dr. <strong>Tech</strong> de Log\", to: \"g15@logitech.com\" },	{ name: \"Don Corleone\", to: \"don@vegas.com\" },	{ name: \"Mc Chick\", to: \"info@donalds.org\" },	{ name: \"Donnie Darko\", to: \"dd@timeshift.info\" },	{ name: \"Quake The Net\", to: \"webmaster@quakenet.org\" },	{ name: \"Dr. Write\", to: \"write@writable.com\" }]");
		response.setContentType("text/html; charset=iso-8859-1");
        //Imprime el resultado
        try {
			sos.print(salida.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
			
	}	

	
	
	
	/**
	 * Prepara la busqueda de un Agendado por Nombre.
	 * 
	 */
	public String getAgendadoPorNombre_Tran() throws Exception {	        								
		
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);

		String filter = (String) request.getParameter("q");
		
		List<GenteBasic> genteList = articuloManager.getProveedorBasicPorNombre(filter, getUsuarioSesion());		
		
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuilder salida = new StringBuilder();
        
		salida.append(getAutoCompleteDataJqueryGenteBasic(genteList));
		salida.insert(0, "[");
		salida.append("]");
		
		response.setContentType("text/html; charset=iso-8859-1");
        //Imprime el resultado
        try {
			sos.print(salida.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
			
	}	

	/**
	 * Prepara la busqueda de un Agendado por Nombre en una lista.
	 * 
	 */
	public String getAgendadoPorNombreList_Tran() throws Exception {	        								
		
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);

		String filter = (String) request.getParameter("q");
		
		List<GenteBasic> genteList = articuloManager.getProveedorBasicPorNombre(filter, getUsuarioSesion());		
		
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuilder salida = new StringBuilder();
        
		salida.append(FormatUtil.getListHTMLGenteBasic(genteList));
		
		
		response.setContentType("text/html; charset=iso-8859-1");
        //Imprime el resultado
        try {
			sos.print(salida.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
			
	}
	
	/**
	 * Obtengo la tabla de Items del Carrito
	 * 
	 */
	public String getItemsCarrito() throws Exception {	        								
		
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);

		String filter = (String) request.getParameter("q");
		
		List<Items> itemsList =(List<Items>)request.getSession().getAttribute("items_CARR"); 		
		
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuilder salida = new StringBuilder();
        
		transac = transaccionManager.calculoTotales(itemsList);
		
		salida.append(FormatUtil.getTableHTMLItemsCarrito(itemsList, getUsuarioSesion().getGenteSession().getRazonSocial() , transac));
		
		response.setContentType("text/html; charset=iso-8859-1");
        //Imprime el resultado
        try {
			sos.print(salida.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
			
	}
	

	/**
	 * Prepara la busqueda de un Stock por descrip en una lista.
	 * 
	 */
	public String getStockPorPiezasList() throws Exception {	        								
		
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);

		String filter = (String) request.getParameter("q");
		
		List<StockPiezas> stockPiezasList = articuloManager.getStockPiezasPorArt(filter, getUsuarioSesion());		
		String clave = "";
		if (stockPiezasList != null && stockPiezasList.size() > 0){
			StockPiezas stockPiezas=stockPiezasList.get(0);
			clave = stockPiezas.getId().getStock().getClave();
		}
		//Seteo la lista de Stock Piezas a utilizar
		request.getSession().setAttribute("stockPiezasList", stockPiezasList);

	
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuilder salida = new StringBuilder();        
	
		Double usaLogicaPiezas = PreferenciasUtil.comparePreferencia(getUsuarioSesion().getListPreferencias(), Constants.PREF_ID_MUESTRA_CANTIDAD);
		if (carrito != null && carrito == 1){
			salida.append(FormatUtil.getListHTMLStockPiezas(stockPiezasList, usaLogicaPiezas, true, true,request.getContextPath(),muestroBarraLateral));			
		}else{
			salida.append(FormatUtil.getListHTMLStockPiezas(stockPiezasList, usaLogicaPiezas, true, false,null,muestroBarraLateral));
			salida.append(articuloManager.getTableResumenPedidos(clave,getUsuarioSesion(),3 ));
		}
			
		
		response.setContentType("text/html; charset=iso-8859-1");
        //Imprime el resultado
        try {
			sos.print(salida.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
			
	}	

	/**
	 * Prepara la busqueda de un Stock por descrip en una lista.
	 * 
	 */
	public String getStockPorNombreList() throws Exception {	        								
		
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);

		String filter = (String) request.getParameter("q");
		
		List<Stock> stockList = articuloManager.getArticuloProNombre(filter, articuloActivo);		
		
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
		} catch (IOException e) { 
			e.printStackTrace();
		}
		
		StringBuilder salida = new StringBuilder();
        
		salida.append(FormatUtil.getListHTMLStock(stockList));
		
		
		response.setContentType("text/html; charset=iso-8859-1");
        //Imprime el resultado
        try {
			sos.print(salida.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
			
	}	
	
	
	public String getSoloImpagoClientes() throws Exception {	        								
		
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		
		List<GenteBasic> clienteList = articuloManager.getClienteBasicPorVendedor(getUsuarioSesion().getVendedorNr());
		
		List<SaldoAcumuladoReport> saldoAcumuladoReportList = new ArrayList<SaldoAcumuladoReport>();
		for (GenteBasic genteBasic: clienteList){
			
			
			SaldoAcumulado saldoAcumulado = cuentaCorrienteManager.getCuentaCorrienteVentasPorGente(genteBasic.getGenteNr(), getUsuarioSesion(), false);
	
			if (saldoAcumulado.getListTransac().size() > 0){
				if (saldoAcumulado.getFinAcumulado() > 1){
				SaldoAcumuladoReport saldoAcumuladoReport = new SaldoAcumuladoReport();
				List<TransacReport> transacReportList = new ArrayList<TransacReport>();
					for (Transac transac : saldoAcumulado.getListTransac()){
						TransacReport transacReport = new TransacReport();
						transacReport.setTransacNr(transac.getTransacNr());
						transacReport.setFecha(transac.getFecha());
						transacReport.setLetra(transac.getLetra());
						transacReport.setNrComprob(transac.getNrComprob());
						transacReport.setPrefijo(transac.getPrefijo());
						transacReport.setSaldoArrastre(transac.getSaldoAcumulado());
						transacReport.setSaldoComprob(transac.getSaldoCalculado());
						transacReport.setTipoComprob(transac.getTipoComprob().getDescripcion());
						transacReport.setTotalComprob(transac.getTotalCalculado());
						transacReportList.add(transacReport);			
					}
					
					saldoAcumuladoReport.setFinAcumulado(saldoAcumulado.getFinAcumulado());
					saldoAcumuladoReport.setIncioAcumulado(saldoAcumulado.getIncioAcumulado());
					saldoAcumuladoReport.setListTransac(transacReportList);
					saldoAcumuladoReport.setGente(genteBasic.getDescripC());
					saldoAcumuladoReportList.add(saldoAcumuladoReport);
				}
			}
			
		}
		Gson gson = new Gson();
		
		String s = gson.toJson(saldoAcumuladoReportList);
		
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuilder salida = new StringBuilder();

		salida.append(s);
		response.setContentType("application/json; charset=iso-8859-1");
        //Imprime el resultado
        try {
			sos.print(salida.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
			
	}	

	/**
	 * Prepara la busqueda de un Articulo por Nombre.
	 * 
	 */
	public String getArticuloPorNombre() throws Exception {	        								
		
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);

		String filter = (String) request.getParameter("q");
		
		List<Stock> articuloList = articuloManager.getArticuloProNombre(filter, articuloActivo);
		
		request.setAttribute("articuloList", articuloList);		
		
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuilder salida = new StringBuilder();
        
		
		
//		String[] autocomplete = getJSonJquery(articuloList); 
//		String suggestions = autocomplete[0];
//		String data = autocomplete[1]; 
//		salida.append("clave:"+ suggestions+",");
//		salida.append("valor:"+ data);
//
//        salida.insert(0, "{");
//        salida.append("}");
		salida.append(getAutoCompleteDataJquery(articuloList));
      salida.insert(0, "[");
      salida.append("]");

		//		salida.append("[	{ name: \"Peter Pan\", to: \"peter@pan.de\" },	{ name: \"Molly\", to: \"molly@yahoo.com\" },	{ name: \"Forneria Marconi\", to: \"live@japan.jp\" },	{ name: \"Master <em>Sync</em>\", to: \"205bw@samsung.com\" },	{ name: \"Dr. <strong>Tech</strong> de Log\", to: \"g15@logitech.com\" },	{ name: \"Don Corleone\", to: \"don@vegas.com\" },	{ name: \"Mc Chick\", to: \"info@donalds.org\" },	{ name: \"Donnie Darko\", to: \"dd@timeshift.info\" },	{ name: \"Quake The Net\", to: \"webmaster@quakenet.org\" },	{ name: \"Dr. Write\", to: \"write@writable.com\" }]");
		response.setContentType("text/html; charset=iso-8859-1");
        //Imprime el resultado
        try {
			sos.print(salida.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
			
	}	
	
	/**
	 * Busqueda de un Articulos por ArtMadre.
	 * 
	 */
	public String showListArticulosPorArtMadre() throws Exception {	        		
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
				
		List<Stock> articuloList = articuloManager.getArticulosPorArtMadre(artMadre.getCodArtMad());
		
		artMadre = articuloManager.getArtMadreProPK(artMadre.getCodArtMad());
		
		request.setAttribute("articuloList", articuloList);
		
		return "success";
	}
	
	
	/**
	 * Prepara la busqueda de un Articulo por Nombre.
	 * 
	 */
	public String findArticuloPorProveedor() throws Exception {	        		
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
				
		List<Stock> articuloList = articuloManager.getArticuloPorProveedor(gente.getGenteNr(),articuloActivo);
		
		gente = articuloManager.getGentePorPK(gente.getGenteNr());
		
		request.setAttribute("articuloList", articuloList);
		
		return "success";
	}

	/**
	 * Busqueda de un Articulo Madre por Nombre.
	 * 
	 */
	public String findArtMadrePorNombre() throws Exception {	        		
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
				
		List<ArtMadre> artMadreList = articuloManager.getArtMadrePorNombre(artMadre.getDescArtMad());
		
		request.setAttribute("artMadreList", artMadreList);
		
		return "success";
	}

	/**
	 * Este metodo devuelve el comprobante en formato PDF
	 * @return 
	 * @author Carrascal
	 */
	public String generateEstadiReport(){
		
			HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);									
			
			if (getUsuarioSesion().getRol() == Constants.ID_USR_VENDEDOR){
				if (datosReporte.getTodosClientes() == null){
					datosReporte.setTodosClientes("true");
				}
				if (datosReporte.getTodosClientes().equals("true")){
					//Cargo los valores de Tipo de Reporte 
					List<GenteBasic> clienteList = articuloManager.getClienteBasicPorVendedor(getUsuarioSesion().getVendedorNr());
					String clientes = "";
					for(GenteBasic gb: clienteList){
						if (clientes.equals("")){
							clientes = "(" + gb.getGenteNr();
						}else{
							clientes = clientes + "," + gb.getGenteNr();
						}
					}
					if (clientes.equals("")){
						clientes = "()";
					}else{
						clientes = clientes + ")";
					}
					datosReporte.setClientes(clientes);
				}
			}else{
				datosReporte.setTodosClientes("false");
			}
			
			byte[] fileDownload = reportManager.generateEstadiReport(datosReporte, getUsuarioSesion());						
	    		
			ServletOutputStream sos;
			try {
				sos = response.getOutputStream();
				if (datosReporte.getFormatoReporte().equals("P")){
					response.setHeader("Content-disposition", "attachment; filename=estadistica.pdf");
					   
				}
				if (datosReporte.getFormatoReporte().equals("X")){
					response.setHeader("Content-disposition", "attachment; filename=estadistica.xls");
				}
				response.setHeader("Pragma", "Public");        
				response.setContentType("application/octet-stream");   
				response.setContentLength(fileDownload.length);
				sos.write(fileDownload);
				sos.flush();
				sos.close();	
				
				} catch (IOException e) {
					e.printStackTrace();
				}
	
		return null;
	}

	
	
	/**
	 * Este metodo devuelve el comprobante en formato PDF
	 * @return 
	 * @author Carrascal
	 */
	public String downloadPdf(){
		
			HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);									
			
			File fileDownload = cuentaCorrienteManager.getComprobantePdf(transac.getTransacNr(), getUsuarioSesion());						
	    	
			int length   = 0;
			
			ServletOutputStream sos;
			try {
				sos = response.getOutputStream();
			
					
			response.setHeader("Content-disposition", "attachment; filename=" + transac.getTransacNr() + ".pdf");        
			response.setHeader("Pragma", "Public");        
			response.setContentType("application/octet-stream");   
			response.setContentLength((int)fileDownload.length());
			
			//
			//  Stream to the requester.
			//
			byte[] bbuf = new byte[2048];
				
				//File fileDownload = file; 
				DataInputStream in = new DataInputStream(new FileInputStream(fileDownload));
			
				while ((in != null) && ((length = in.read(bbuf)) != -1))
				{
				sos.write(bbuf,0,length);
			}
			
			sos.flush();
			sos.close();
			in.close();
			} catch (Exception e) {
				
				e.printStackTrace();
			}										
	
		return null;
	}
	
	/**
	 * Este metodo devuelve la imagen de la empresa
	 * @return 
	 * @author Carrascal
	 */
	public String getImageEmpresa(){
		
			HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);									
			
			File imagenEmpresa =new File("c://imagenEmpresa.png");						
	    	
			int length   = 0;
			
			ServletOutputStream sos;
			try {
				sos = response.getOutputStream();
			
					
			response.setHeader("Content-disposition", "attachment; filename=" + transac.getTransacNr() + ".pdf");        
			response.setHeader("Pragma", "Public");        
			response.setContentType("application/octet-stream");   
			response.setContentLength((int)imagenEmpresa.length());
			
			//
			//  Stream to the requester.
			//
			byte[] bbuf = new byte[2048];
				
				//File fileDownload = file; 
				DataInputStream in = new DataInputStream(new FileInputStream(imagenEmpresa));
			
				while ((in != null) && ((length = in.read(bbuf)) != -1))
				{
				sos.write(bbuf,0,length);
			}
			
			sos.flush();
			sos.close();
			in.close();
			} catch (Exception e) {
				
				e.printStackTrace();
			}										
	
		return null;
	}
	
	/**
	 * Muestra la cuenta corriente de ventas en base a una persona  
	 * 
	 */
	public String getSoloImpagosVentaPorGente_CC() throws Exception {	        						
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
				
		gente = articuloManager.getGentePorPK(gente.getGenteNr());
		
		SaldoAcumulado saldoAcumulado = cuentaCorrienteManager.getCuentaCorrienteVentasPorGente(gente.getGenteNr(), getUsuarioSesion(), true);
		
		//totalSaldo = cuentaCorrienteManager.getTotalSaldoVentasPorGente(saldoAcumulado.getListTransac());
		
		totalSaldo = saldoAcumulado.getFinAcumulado();
		
		request.setAttribute("transacList", saldoAcumulado.getListTransac());
		
		request.setAttribute("grillaResult", "El cliente: " + gente.getDescripC() + " tiene un saldo total de: " + totalSaldo);		
		
		return "success";
	}
	
	/**
	 * Muestra la cuenta corriente de ventas en base a una persona  
	 * 
	 */
	public String getPedPendientesPorGente_CC() throws Exception {	        						
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
				
		gente = articuloManager.getGentePorPK(gente.getGenteNr());
		
		List<Transac> transacList = transaccionManager.getPedidosPendientes(gente, getUsuarioSesion());
		
		request.setAttribute("transacList", transacList);
		
		return "success";
	}
	
	/**
	 * Muestra los pedidos de venta desde un agendado 
	 * 
	 */
	public String getPedidoVtaPendiente() throws Exception {	        						
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
				
		gente = articuloManager.getGentePorPK(gente.getGenteNr());
		
		List<Transac> transacList = transaccionManager.getPedidosVtaPendienteAprobar(gente, getUsuarioSesion());
		
		request.setAttribute("transacList", transacList);
		
		return "success";
	}

	/**
	 * Muestra la cuenta corriente de ventas en base a una persona  
	 * 
	 */
	public String getSoloImpagosPorGente_CC_C() throws Exception {	        						
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
				
		Usuario usuario = getUsuarioSesion();
		
		if (usuario.getRol() == Constants.ID_USR_PROVEEDOR){
			gente = articuloManager.getGentePorPK(usuario.getGenteNr());
		}else{
			gente = articuloManager.getGentePorPK(gente.getGenteNr());
		}		
		
		SaldoAcumulado saldoAcumulado = cuentaCorrienteManager.getCuentaCorrienteComprasPorGente(gente.getGenteNr(), getUsuarioSesion());				
		
		totalSaldo = saldoAcumulado.getFinAcumulado();
		
		request.setAttribute("transacList", saldoAcumulado.getListTransac());
		
		request.setAttribute("grillaResult", "El cliente: " + gente.getDescripC() + " tiene un saldo total de: " + totalSaldo);		
		
		return "success";
	}

	/**
	 * Muestra la cuenta corriente de ventas en base a una persona  
	 * 
	 */
	public String findPasebanPorAgendado_CR() throws Exception {	        						
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
				
		gente = articuloManager.getGentePorPK(gente.getGenteNr());
		
		List<Paseban> pasebanList = tesoreriaManager.getPasebanPorAgendado(gente.getGenteNr());
		
		totalSaldo = tesoreriaManager.getPasebanTotalPorAgendado(gente.getGenteNr());
		
		request.setAttribute("pasebanList", pasebanList);
					
		
		return "success";
	}
	
	/**
	 * Retorna la el objeto RESPONSE del contexto
	 */
	private HttpServletResponse getResponse() {
		return (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
	}
	
	/**
	 * Metodo para preguntar si existe el cheque
	 */
	public String existeCheque() {		
		String result = "";
		boolean existe = tesoreriaManager.existeCheque(serie);
		
		if (existe) {
			result = "OK";
		}
		
		
		HttpServletResponse response = this.getResponse();
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			sos.print(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	/**
	 * Metodo para recuperar el cheque
	 */
	public String recuperarCheque() {
		
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
		
			try {
											
				byte[] bytes = tesoreriaManager.getCheque(serie);
					
					ServletOutputStream sos;
					try {
						sos = response.getOutputStream();		
							
					//response.setHeader("Content-disposition", "attachment; filename=" + facturaActual.getNombreArchivo().replaceAll("txt", "xml"));        
					response.setHeader("Pragma", "Public");        
					response.setContentType("image/jpeg");   
					//response.setContentLength(bytes.length);
					sos.write(bytes);
					sos.flush();
					sos.close();	
					
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				

				
			} catch (Exception e) {
				e.printStackTrace();
			}



		return null;
	}
	

	/**
	 * Muestra transac por tipo de comprobante entre fechas  
	 * 
	 */
	public String reportePorTipoComprob_Repo() throws Exception {	        						
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
						
		gente = articuloManager.getGentePorPK(gente.getGenteNr());
		
		List<Transac> transacList = cuentaCorrienteManager.getTransacPorTipoComprob(gente.getGenteNr(), getUsuarioSesion(), transac.getTipoComprob().getNr(), fechaDesde, fechaHasta);				
		
		request.setAttribute("transacList", transacList);
		
		TipoComprob tipoComprob = transaccionManager.getTipoComprobByPK(transac.getTipoComprob().getNr());
		
		transac.setTipoComprob(tipoComprob);
		
		return "success";
	}

	
	/**
	 * Muestra la cuenta corriente filtrado por cliente y fechas  
	 * 
	 */
	public String resumen_CC() throws Exception {	        						
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
						
		gente = articuloManager.getGentePorPK(gente.getGenteNr());
		
		SaldoAcumulado saldoAcumulado = cuentaCorrienteManager.getResumenCuentaCorriente(gente.getGenteNr(), getUsuarioSesion(), fechaDesde, fechaHasta);
		
		//totalSaldo = cuentaCorrienteManager.getTotalSaldoVentasPorGente(saldoAcumulado.getListTransac());
		
		saldoAnterior = saldoAcumulado.getIncioAcumulado(); 
		
		totalSaldo = saldoAcumulado.getFinAcumulado();
		
		request.setAttribute("transacList", saldoAcumulado.getListTransac());
		
		request.setAttribute("grillaResult", "El cliente: " + gente.getDescripC() + " tiene un saldo total de: " + totalSaldo);		
		
		return "success";
	}
	
	
	
	/**
	 * Muestra la estadistica por art y gente filtrado por cliente y fechas  
	 * 
	 */
	public String estadisticaArtGente() throws Exception {	        						
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		
		Usuario usuario = getUsuarioSesion();
		
		if (usuario.getRol() == Constants.ID_USR_PROVEEDOR){
			gente = articuloManager.getGentePorPK(usuario.getGenteNr());
		}else{
			gente = articuloManager.getGentePorPK(gente.getGenteNr());
		}		
				
		List<Items> itemsList = transaccionManager.getEstadisticaArtGente(gente.getGenteNr(), getUsuarioSesion(), fechaDesde, fechaHasta);
		
		
		
		request.setAttribute("itemsList", itemsList);
		
		return "success";
	}

	
	/**
	 * Muestra la cuenta corriente filtrado por cliente y fechas  
	 * 
	 */
	public String resumen_CC_C() throws Exception {	        						
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		
		Usuario usuario = getUsuarioSesion();
		
		if (usuario.getRol() == Constants.ID_USR_PROVEEDOR){
			gente = articuloManager.getGentePorPK(usuario.getGenteNr());
		}else{
			gente = articuloManager.getGentePorPK(gente.getGenteNr());
		}		
				
		SaldoAcumulado saldoAcumulado = cuentaCorrienteManager.getResumenCuentaCorrienteCompras(gente.getGenteNr(), getUsuarioSesion(), fechaDesde, fechaHasta);
		
		//totalSaldo = cuentaCorrienteManager.getTotalSaldoVentasPorGente(saldoAcumulado.getListTransac());
		
		saldoAnterior = saldoAcumulado.getIncioAcumulado(); 
		
		totalSaldo = saldoAcumulado.getFinAcumulado();
		
		request.setAttribute("transacList", saldoAcumulado.getListTransac());
		
		request.setAttribute("grillaResult", "El cliente: " + gente.getDescripC() + " tiene un saldo total de: " + totalSaldo);		
		
		return "success";
	}

	/**
	 * Prepara la busqueda de un Articulo por Flia y SubFlia.
	 * 
	 */
	
	public String findArticuloPorFlia() throws Exception {	        		
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);				
		
		List<Stock> articulosTmpList = articuloManager.getArticuloPorFliaSubFlia(fam.getNrfam(), subFam.getNrsubfam(),articuloActivo, getUsuarioSesion());

		fam = articuloManager.getFamiliaPorPK(fam.getNrfam());
		
		subFam = articuloManager.getSubFamiliaPorPK(subFam.getNrsubfam());
		
		Usuario usuario = getUsuarioSesion();
		
		usuario.setArticulosTmpList(articulosTmpList);
		
		request.getSession().setAttribute("usuario", usuario);

		request.setAttribute("articuloList", articulosTmpList);
		
		return "success";
	}
	
	/*
	 * Devuelve 2 arreglos, unos de propiedades y otro de valores a partir de un
	 * arreglo de objetos de tipo Filter
	 */
	private void extractFilter(Filter[] filters, String[] propertyFilter,
			String[] valueFilter) {
		int j = 0;
		if (filters.length > 0) {
			for (Filter f : filters) {
				propertyFilter[j] = f.getProperty();
				valueFilter[j++] = f.getValue();
			}
		}
	}
	/**
	 * Prepara la busqueda de un Articulo por Flia y SubFlia.
	 * 
	 */
	
	public String findArticuloPorFlia_Carr() throws Exception {	        		
		//Parametros de la Grilla
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);		
		// Grilla de No registrados
		Context context = new HttpServletRequestContext(request);
		LimitFactory limitFactory = new TableLimitFactory(context, "notReg");
		Limit limit = new TableLimit(limitFactory);

		String propertySort = limit.getSort().getProperty();
		String orderSort = limit.getSort().getSortOrder();
		// Se obtienen los filtros para la grilla superior
		Filter[] filters = limit.getFilterSet().getFilters();
		String[] propertyFilter = new String[filters.length];
		String[] valueFilter = new String[filters.length];
		extractFilter(filters, propertyFilter, valueFilter);

		int pageSize = 16;

		String rows = context.getParameter("notReg_" + TableConstants.CURRENT_ROWS_DISPLAYED);

		if (StringUtils.isNotBlank(rows)) {
			pageSize = Integer.parseInt(rows);
		}
		Integer max = limit.getPage() * pageSize;

		Integer min = max - pageSize;

		Long regTotalRows = 0l;
		List<Stock> articulosTmpList = new ArrayList<Stock>();
		if (busquedaPorId == null)
			busquedaPorId = 1;
		switch (busquedaPorId) {
		
		case 0:
			regTotalRows = articuloManager.getCountArticuloPorFliaSubFlia(fam.getNrfam(), subFam.getNrsubfam(),articuloActivo,propertySort,orderSort, propertyFilter, valueFilter, min, pageSize);	
			articulosTmpList = articuloManager.getArticuloPorFliaSubFlia(fam.getNrfam(), subFam.getNrsubfam(),articuloActivo,propertySort,
					orderSort, propertyFilter, valueFilter, min, pageSize);			
			break;
		case 1:
			regTotalRows = articuloManager.getCountArticuloPorNombre(stock.getDescripcion(),articuloActivo,propertySort,orderSort, propertyFilter, valueFilter, min, pageSize);	
			articulosTmpList = articuloManager.getArticuloPorNombre(stock.getDescripcion(),articuloActivo,propertySort,
					orderSort, propertyFilter, valueFilter, min, pageSize);			
			break;
		case 2:
			regTotalRows = articuloManager.getCountArticuloPorCodigo(stock.getClave(),articuloActivo,propertySort,orderSort, propertyFilter, valueFilter, min, pageSize);	
			articulosTmpList = articuloManager.getArticuloPorCodigo(stock.getClave(),articuloActivo,propertySort,
					orderSort, propertyFilter, valueFilter, min, pageSize);			
			break;
		}
		
		Usuario usuario = getUsuarioSesion();			
		for(Stock stock : articulosTmpList){			
			stock.setPrecioTemp(MathUtil.redondearEn2BD(transaccionManager.getPrecioClienteArticulo( getUsuarioSesion().getGenteSession().getGenteNr(), stock.getClave(),usuario, false)));
			if (stock.getTipoCalculo().equals(Short.parseShort("6"))){
				stock.setPrecioTempStr("u$s " + stock.getPrecioTemp());
			}else{
				stock.setPrecioTempStr("$ " + stock.getPrecioTemp() );
			}
		}
		
		fam = articuloManager.getFamiliaPorPK(fam.getNrfam());
		
		subFam = articuloManager.getSubFamiliaPorPK(subFam.getNrsubfam());
		

		
		usuario.setArticulosTmpList(articulosTmpList);
		
		request.getSession().setAttribute("usuario", usuario);
		
		request.setAttribute("totalRows", regTotalRows.intValue());

		request.setAttribute("articuloList", articulosTmpList);
		
		return "success";
	}

	public String downLoadImageArticulo() throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);		
		
		byte[] imageData = null;
		
		ImagenesArticulos ia = articuloManager.getImageArticuloByPK(stock.getClave());
			
		if (ia == null || ia.getImagen() == null){
			imageData  = articuloManager.getImageDefault().getImagen();
		}else{
			imageData = ia.getImagen();
		}
	
		String fileName = "Imagen_Articulo_.jpg";
		
		ServletOutputStream sos;
		try {
			sos = response.getOutputStream();
		
				
		//response.setHeader("Content-disposition", "attachment; filename=" + fileName);        
		response.setHeader("Pragma", "Public");        
		response.setContentType("image/jpeg");   
		
		sos.write(imageData);
		sos.flush();
		sos.close();	
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

		
		
	}
	
	/**
	 * Muestra el detalle de un articulo.
	 * 
	 */
	public String showArticulo() throws Exception {	        		
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		
		//Obtengo el Articulo
		stock = articuloManager.getArticuloPorPK(stock.getClave());						
		
		//Obtengo la lista de Existencia para el articulo
		List<ExiArt> exiArtList = articuloManager.getExistenciaPorArticulo(stock.getClave(), getUsuarioSesion());		

		//Publico la lista
		request.setAttribute("exiArtList", exiArtList);
		
		return "success";
	}
	
	
	
	
	
	/**
	 * Prepara la busqueda de un Gente por Nombre.
	 * 
	 */
	public String findGentePorNombre_CC() throws Exception {	        		
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
				
		Usuario usuario = getUsuarioSesion();
		
		List<Gente> genteList = new ArrayList<Gente>();
		
		if (usuario.getRol() == Constants.ID_USR_GERENTE){
			genteList = articuloManager.getProveedorPorNombre(nombreProveedor);
		}
		if (usuario.getRol() == Constants.ID_USR_VENDEDOR){		
			genteList = articuloManager.getProveedorPorNombreVendedor(nombreProveedor, usuario);
		}
		request.setAttribute("genteList", genteList);
		
		return "success";
	}	

	/**
	 * Prepara la busqueda de un Gente por Nombre.
	 * 
	 */
	public String findGentePorNombre_CC_C() throws Exception {	        		
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
				
		Usuario usuario = getUsuarioSesion();
		
		List<Gente> genteList = new ArrayList<Gente>();
		
		if (usuario.getRol() == Constants.ID_USR_GERENTE){
			genteList = articuloManager.getProveedorPorNombre(nombreProveedor);
		}
		if (usuario.getRol() == Constants.ID_USR_VENDEDOR){		
			genteList = articuloManager.getProveedorPorNombreVendedor(nombreProveedor, usuario);
		}
		request.setAttribute("genteList", genteList);
		
		return "success";
	}	

	/**
	 * Prepara la busqueda de un Gente por Nombre.
	 * 
	 */
	public String findAgendadoPorNombre_CR() throws Exception {	        		
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
				
		Usuario usuario = getUsuarioSesion();
		
		List<Gente> genteList = new ArrayList<Gente>();
		
		if (usuario.getRol() == Constants.ID_USR_GERENTE){
			genteList = articuloManager.getProveedorPorNombre(nombreProveedor);
		}
		if (usuario.getRol() == Constants.ID_USR_VENDEDOR){		
			genteList = articuloManager.getProveedorPorNombreVendedor(nombreProveedor, usuario);
		}
		request.setAttribute("genteList", genteList);
		
		return "success";
	}	
	
	/**
	 * Prepara la busqueda de un Gente por Nombre.
	 * 
	 */

	public String findAgendadoPorNombre_Tran() throws Exception {	        		
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
				
		Usuario usuario = getUsuarioSesion();
		
		List<Gente> genteList = new ArrayList<Gente>();
		
		if (usuario.getRol() == Constants.ID_USR_GERENTE){
			genteList = articuloManager.getProveedorPorNombre(nombreProveedor);
		}
		if (usuario.getRol() == Constants.ID_USR_VENDEDOR){		
			genteList = articuloManager.getProveedorPorNombreVendedor(nombreProveedor, usuario);
		}
		request.setAttribute("genteList", genteList);
		
		return "success";
	}	

	/**
	 * Prepara la busqueda de un Gente por Nombre.
	 * 
	 */
	public String findAgendadoPorNombre_Resumen_CC() throws Exception {	        		
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
				
		Usuario usuario = getUsuarioSesion();
		
		List<Gente> genteList = new ArrayList<Gente>();
		
		if (usuario.getRol() == Constants.ID_USR_GERENTE){
			genteList = articuloManager.getProveedorPorNombre(nombreProveedor);
		}
		if (usuario.getRol() == Constants.ID_USR_VENDEDOR){		
			genteList = articuloManager.getProveedorPorNombreVendedor(nombreProveedor, usuario);
		}
		request.setAttribute("genteList", genteList);
		
		return "success";
	}	

	
	
	/**
	 * Prepara la busqueda de un Gente por Nombre.
	 * 
	 */
	public String findAgendadoPorNombre_Resumen_CC_P_C() throws Exception {	        		
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
				
		Usuario usuario = getUsuarioSesion();
		
		List<Gente> genteList = new ArrayList<Gente>();
		
		if (usuario.getRol() == Constants.ID_USR_GERENTE){
			genteList = articuloManager.getProveedorPorNombre(nombreProveedor);
		}
		if (usuario.getRol() == Constants.ID_USR_VENDEDOR){		
			genteList = articuloManager.getProveedorPorNombreVendedor(nombreProveedor, usuario);
		}
		if (usuario.getRol() == Constants.ID_USR_PROVEEDOR){
			List<Gente> genteListP = new ArrayList<Gente>();
			genteListP.add(articuloManager.getGentePorPK(usuario.getGenteNr()));
			genteList = genteListP; 
		}
		request.setAttribute("genteList", genteList);
		
		return "success";
	}	

	
	
	
	
	
	/**
	 * Prepara la busqueda de un Gente por Nombre.
	 * 
	 */
	public String findAgendadoPorNombre_Resumen_CC_C() throws Exception {	        		
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
				
		Usuario usuario = getUsuarioSesion();
		
		List<Gente> genteList = new ArrayList<Gente>();
		
		if (usuario.getRol() == Constants.ID_USR_GERENTE){
			genteList = articuloManager.getProveedorPorNombre(nombreProveedor);
		}
		if (usuario.getRol() == Constants.ID_USR_VENDEDOR){		
			genteList = articuloManager.getProveedorPorNombreVendedor(nombreProveedor, usuario);
		}
		if (usuario.getRol() == Constants.ID_USR_PROVEEDOR){
			List<Gente> genteListP = new ArrayList<Gente>();
			genteListP.add(articuloManager.getGentePorPK(usuario.getGenteNr()));
			genteList = genteListP; 
		}
		request.setAttribute("genteList", genteList);
		
		return "success";
	}	

	/**
	 * Prepara la busqueda de un Proveedor por Nombre.
	 * 
	 */
	public String findProveedorPorNombre() throws Exception {	        		
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
				
		List<Gente> genteList = articuloManager.getProveedorPorNombre(nombreProveedor);
		
		request.setAttribute("genteList", genteList);
		
		return "success";
	}
	
		
	/**
	 * Este Metodo muestra los remitos pendientes de recepcionar por el usuario logeado de Trazabilidad
	 */
	public String preparedListRemitosPendientes() throws Exception {	        		
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
				
		List<Transac> transacList = transaccionManager.getRemitosPendientesPorClienteTraza(getUsuarioSesion());
		
		gente = articuloManager.getGentePorPK(getUsuarioSesion().getGenteNr());
		
		request.setAttribute("transacList", transacList);
		
		return "success";
	}
	/**
	 * Este Metodo muestra los Medicamentos pendientes de recepcionar por el usuario logeado de Trazabilidad
	 */
	public String viewTrazaIngreso() throws Exception {	        		
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
				
		List<Trazabi> trazabiList = transaccionManager.getTrazabiPorComprobante(transac.getTransacNr());
		
		gente = articuloManager.getGentePorPK(getUsuarioSesion().getGenteNr());
		
		request.setAttribute("trazabiList", trazabiList);
		
		return "success";
	}
	
	
}