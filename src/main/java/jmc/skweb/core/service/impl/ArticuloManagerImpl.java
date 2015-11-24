package jmc.skweb.core.service.impl;




import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import jmc.skweb.core.dao.ArtMadreDAO;
import jmc.skweb.core.dao.ExiArtDAO;
import jmc.skweb.core.dao.FamDAO;
import jmc.skweb.core.dao.GenericDAO;
import jmc.skweb.core.dao.GenteBasicDAO;
import jmc.skweb.core.dao.GenteDAO;
import jmc.skweb.core.dao.ParametrizacionDAO;
import jmc.skweb.core.dao.StockDAO;
import jmc.skweb.core.dao.StockPiezasDAO;
import jmc.skweb.core.dao.SubFamDAO;
import jmc.skweb.core.dao.TransacDAO;
import jmc.skweb.core.model.ArtMadre;
import jmc.skweb.core.model.Colores;
import jmc.skweb.core.model.Domicilios;
import jmc.skweb.core.model.DondeEstaFactuMail;
import jmc.skweb.core.model.EstadiOrigen;
import jmc.skweb.core.model.EstadiReporte;
import jmc.skweb.core.model.EstadiTipoReporte;
import jmc.skweb.core.model.ExiArt;
import jmc.skweb.core.model.Existencias;
import jmc.skweb.core.model.Fam;
import jmc.skweb.core.model.Gente;
import jmc.skweb.core.model.ImagenesArticulos;
import jmc.skweb.core.model.Parametrizacion;
import jmc.skweb.core.model.Preferencias;
import jmc.skweb.core.model.StockPiezas;
import jmc.skweb.core.model.StockPiezasId;
import jmc.skweb.core.model.Transac;
import jmc.skweb.core.model.UsuarioWeb;
import jmc.skweb.core.model.report.GroupCantTransac;
import jmc.skweb.core.model.report.TransacJoin;
import jmc.skweb.core.model.shortEntities.GenteBasic;
import jmc.skweb.core.model.shortEntities.OptionsSelect;
import jmc.skweb.core.model.Stock;
import jmc.skweb.core.model.SubFam;
import jmc.skweb.core.model.Usuario;
import jmc.skweb.core.service.ArticuloManager;
import jmc.skweb.core.service.TransaccionManager;
import jmc.skweb.core.service.UsuarioManager;
import jmc.skweb.util.Constants;
import jmc.skweb.util.FileUtil;
import jmc.skweb.util.FormatUtil;
import jmc.skweb.util.PreferenciasUtil;





/**
 * @author Juan Manuel Carrascal
 *
 */
/**
 * @author Administrator
 *
 */
//@Transactional
/**
 * @author vaio
 *
 */
public class ArticuloManagerImpl implements ArticuloManager{

 	  	private GenericDAO<Existencias> existenciasDAO;
 	  	private GenericDAO<Fam> famDAO;
 	  	private GenericDAO<SubFam> subFamDAO;
 	  	private GenericDAO<Stock> stockDAO;
 	  	private GenericDAO<ExiArt> exiArtDAO;
 	  	private StockPiezasDAO extendedStockPiezasDAO;
 	  	private FamDAO extendedFamDAO;
 	  	private StockDAO extendedStockDAO;
 	  	private ExiArtDAO extendedExiArtDAO;
 	  	private SubFamDAO extendedSubFamDAO;
 	  	private GenteDAO extendedGenteDAO;
 	  	private TransacDAO extendedTransacDAO;
 	  	private ArtMadreDAO extendedArtMadreDAO;
 	  	private GenericDAO<DondeEstaFactuMail> dondeEstaFactuMailDAO;
 	  	private GenteBasicDAO extendedGenteBasicDAO;
 	  	private GenericDAO<Parametrizacion> parametrizacionDAO;
 	  	private GenericDAO<ImagenesArticulos> imagenesArticulosDAO;
 	  	private GenericDAO<EstadiTipoReporte> estadiTipoReporteDAO;
 	  	
 	  	
 	  	
 	  	public GenericDAO<EstadiTipoReporte> getEstadiTipoReporteDAO() {
			return estadiTipoReporteDAO;
		}
		public void setEstadiTipoReporteDAO(
				GenericDAO<EstadiTipoReporte> estadiTipoReporteDAO) {
			this.estadiTipoReporteDAO = estadiTipoReporteDAO;
		}
		public StockPiezasDAO getExtendedStockPiezasDAO() {
			return extendedStockPiezasDAO;
		}
		public void setExtendedStockPiezasDAO(StockPiezasDAO extendedStockPiezasDAO) {
			this.extendedStockPiezasDAO = extendedStockPiezasDAO;
		}
		public GenericDAO<ImagenesArticulos> getImagenesArticulosDAO() {
			return imagenesArticulosDAO;
		}
		public void setImagenesArticulosDAO(
				GenericDAO<ImagenesArticulos> imagenesArticulosDAO) {
			this.imagenesArticulosDAO = imagenesArticulosDAO;
		}
		public GenericDAO<Parametrizacion> getParametrizacionDAO() {
			return parametrizacionDAO;
		}
		public void setParametrizacionDAO(GenericDAO<Parametrizacion> parametrizacionDAO) {
			this.parametrizacionDAO = parametrizacionDAO;
		}
		public GenteBasicDAO getExtendedGenteBasicDAO() {
			return extendedGenteBasicDAO;
		}
		public void setExtendedGenteBasicDAO(GenteBasicDAO extendedGenteBasicDAO) {
			this.extendedGenteBasicDAO = extendedGenteBasicDAO;
		}
		public GenericDAO<DondeEstaFactuMail> getDondeEstaFactuMailDAO() {
			return dondeEstaFactuMailDAO;
		}
		public void setDondeEstaFactuMailDAO(
				GenericDAO<DondeEstaFactuMail> dondeEstaFactuMailDAO) {
			this.dondeEstaFactuMailDAO = dondeEstaFactuMailDAO;
		}
		public ArtMadreDAO getExtendedArtMadreDAO() {
			return extendedArtMadreDAO;
		}
		public void setExtendedArtMadreDAO(ArtMadreDAO extendedArtMadreDAO) {
			this.extendedArtMadreDAO = extendedArtMadreDAO;
		}
		public TransacDAO getExtendedTransacDAO() {
			return extendedTransacDAO;
		}
		public void setExtendedTransacDAO(TransacDAO extendedTransacDAO) {
			this.extendedTransacDAO = extendedTransacDAO;
		}
		public GenteDAO getExtendedGenteDAO() {
			return extendedGenteDAO;
		}
		public void setExtendedGenteDAO(GenteDAO extendedGenteDAO) {
			this.extendedGenteDAO = extendedGenteDAO;
		}
		public SubFamDAO getExtendedSubFamDAO() {
			return extendedSubFamDAO;
		}
		public void setExtendedSubFamDAO(SubFamDAO extendedSubFamDAO) {
			this.extendedSubFamDAO = extendedSubFamDAO;
		}
		public ExiArtDAO getExtendedExiArtDAO() {
			return extendedExiArtDAO;
		}
		public void setExtendedExiArtDAO(ExiArtDAO extendedExiArtDAO) {
			this.extendedExiArtDAO = extendedExiArtDAO;
		}
		public StockDAO getExtendedStockDAO() {
			return extendedStockDAO;
		}
		public void setExtendedStockDAO(StockDAO extendedStockDAO) {
			this.extendedStockDAO = extendedStockDAO;
		}
		public FamDAO getExtendedFamDAO() {
			return extendedFamDAO;
		}
		public void setExtendedFamDAO(FamDAO extendedFamDAO) {
			this.extendedFamDAO = extendedFamDAO;
		}
		public GenericDAO<Existencias> getExistenciasDAO() {
			return existenciasDAO;
		}
		public void setExistenciasDAO(GenericDAO<Existencias> existenciasDAO) {
			this.existenciasDAO = existenciasDAO;
		}
		public GenericDAO<Fam> getFamDAO() {
			return famDAO;
		}
		public void setFamDAO(GenericDAO<Fam> famDAO) {
			this.famDAO = famDAO;
		}
		public GenericDAO<SubFam> getSubFamDAO() {
			return subFamDAO;
		}
		public void setSubFamDAO(GenericDAO<SubFam> subFamDAO) {
			this.subFamDAO = subFamDAO;
		}
		public GenericDAO<Stock> getStockDAO() {
			return stockDAO;
		}
		public void setStockDAO(GenericDAO<Stock> stockDAO) {
			this.stockDAO = stockDAO;
		}
		public GenericDAO<ExiArt> getExiArtDAO() {
			return exiArtDAO;
		}
		public void setExiArtDAO(GenericDAO<ExiArt> exiArtDAO) {
			this.exiArtDAO = exiArtDAO;
		}
 	  	
 	  	public List<Fam> getFamEnable(){
 	  		List<Fam> famList = null;
			try {
				famList = extendedFamDAO.getFamEnable();
			} catch (Exception e) {
				e.printStackTrace();
			}
 	  		return famList;
 	  	}
 	  	//@Transactional(readOnly = true)
 	  	public List<Stock> getArticuloProNombre(String filtro, boolean activos){
 	  		List<Stock> stockList = null;
			try {
				stockList = extendedStockDAO.getArticuloPorNombre(filtro,activos);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
 	  		return stockList;
 	  	}
		
		public Stock getArticuloPorPK(String clave) {		
			Stock stock = extendedStockDAO.getByPrimaryKey(clave); 
			
			
			stock.setFamStr(famDAO.getByPrimaryKey(stock.getFam()).getDesfam());
			stock.setSubFamStr(subFamDAO.getByPrimaryKey(stock.getSubFam()).getDesubfa());
			
			return stock;
		}
		
		public List<ExiArt> getExistenciaPorArticulo(String clave, Usuario usuario){			
			
			List<ExiArt> listArt = null;
			Double idPreferenciaDefault = PreferenciasUtil.comparePreferencia(usuario.getListPreferencias(), Constants.PREF_ID_EXISTENCIA_DEFAULT);
			Preferencias p = null;
			try {
				if (PreferenciasUtil.comparePreferencia(usuario.getListPreferencias(), Constants.PREF_ID_EXISTENCIA_DEFAULT) == -1){
					listArt = this.extendedExiArtDAO.getExistenciaPorNombre(clave);
				}else{
					listArt = this.extendedExiArtDAO.getExistenciaPorNombre(clave, idPreferenciaDefault);
				}
				
				
			} catch (Exception e) {

				e.printStackTrace();
			}
			
			return listArt;
		}

		public List<Fam> getListFam() {			
			List<Fam> listFam = null;
			try {
				listFam = extendedFamDAO.getFamEnable();
			} catch (Exception e) {

				e.printStackTrace();
			} 
			List<Fam> listFamOri = new ArrayList<Fam>();
			Fam fam = new Fam();
			fam.setNrfam(0l);
			fam.setDesfam("Seleccione una Familia");
			listFamOri.add(fam);
			listFamOri.addAll(listFam);			

			return listFamOri;
		}
		
		public List<SubFam> getListSubFam() {
			List<SubFam> listSubFam = null;
			try {
				listSubFam = extendedSubFamDAO.getSubFamEnable();
			} catch (Exception e) {

				e.printStackTrace();
			} 
			List<SubFam> listSubFamOri = new ArrayList<SubFam>();
			SubFam subFam = new SubFam();
			subFam.setNrsubfam(0l);
			subFam.setDesubfa("Seleccione una SubFamilia");
			listSubFamOri.add(subFam);
			listSubFamOri.addAll(listSubFam);			
			return listSubFamOri;
		}
		
		/**
		 * Obtiene los articulos por Fam o SubFam
		 */
		public List<Stock> getArticuloPorFliaSubFlia(Long nrfam, Long nrsubfam, boolean activos, Usuario usuario) {
			List<Stock> listArt = new ArrayList<Stock>();
			if(nrfam == 0 && nrsubfam != 0){
				listArt = extendedStockDAO.getArticuloPorNrSubFam(nrsubfam,activos);
			}
			if(nrfam != 0 && nrsubfam != 0){
				listArt = extendedStockDAO.getArticuloPorNrFamSubFam(nrfam, nrsubfam, activos);
			}
			if(nrfam != 0 && nrsubfam == 0){
				listArt = extendedStockDAO.getArticuloPorNrFam(nrfam,activos);
			}
			//Obtengo la existencia por Default
//			Double idExistencia = PreferenciasUtil.comparePreferencia(usuario.getListPreferencias(), Constants.PREF_ID_EXISTENCIA_DEFAULT);
//			for(Stock stock:listArt){
//				//Obtengo el Stock del producto
//				Double stockN = extendedExiArtDAO.getStockByExiClave(idExistencia, stock.getClave());
//				stock.setStockDefault(stockN);
//			}
			return listArt;
			
		}
		
		/**
		 * Obtiene los articulos por Fam o SubFam
		 */
		public List<Stock> getArticuloPorFliaSubFlia(Long nrfam, Long nrsubfam, boolean activos, Gente gente) {
			List<Stock> listArt = null;
			if(nrfam == 0 && nrsubfam != 0){
				listArt = extendedStockDAO.getArticuloPorNrSubFam(nrsubfam,activos);
			}
			if(nrfam != 0 && nrsubfam != 0){
				listArt = extendedStockDAO.getArticuloPorNrFamSubFam(nrfam, nrsubfam, activos);
			}
			if(nrfam != 0 && nrsubfam == 0){
				listArt = extendedStockDAO.getArticuloPorNrFam(nrfam,activos);
			}
			return listArt;
		}
		
		
		
		public List<Gente> getProveedorPorNombre(String nombreProveedor) {
			List<Gente> listGente = null;
			try {
				listGente = extendedGenteDAO.getProveedorPorNombre(nombreProveedor);
			} catch (Exception e) {

				e.printStackTrace();
			}	
			return listGente;
		}

		public List<Gente> getProveedorPorNombreVendedor(String nombreProveedor, Usuario usuario) {
			List<Gente> listGente = null;
			try {
				listGente = extendedGenteDAO.getProveedorPorNombreVendedor(nombreProveedor, usuario.getVendedorNr());
			} catch (Exception e) {

				e.printStackTrace();
			}	
			return listGente;
		}

		/**
		 * Obtiene los Articulos por el Proceedor
		 */		
		public List<Stock> getArticuloPorProveedor(Integer genteNr, boolean activos) {
			List<Stock> stockList = extendedStockDAO.getArticuloPorNrProveedor(genteNr,activos);			
			return stockList;
		}
		
		/**
		 * Lista los articulos madre por nombre de descripcion
		 */
		public List<ArtMadre> getArtMadrePorNombre(String descArtMad) {
			List<ArtMadre> artMadreList = extendedArtMadreDAO.getArtMadrePorDesc(descArtMad);
			return artMadreList;
		}
		
		/**
		 * Obtiene Articulos Hijos
		 */
		public List<Stock> getArticulosPorArtMadre(String codArtMad) {
			ArtMadre artMadre = extendedArtMadreDAO.getByPrimaryKey(codArtMad);
			return artMadre.getArticulosHijos();
		}

		public Gente getGentePorPK(Integer genteNr) {
			return extendedGenteDAO.getByPrimaryKey(genteNr);
		}
		
		public Fam getFamiliaPorPK(Long nrfam) {
			if (nrfam != null && nrfam != 0){
				return extendedFamDAO.getByPrimaryKey(nrfam);
			}else{
				return new Fam(nrfam);
			}			
		}
		
		public SubFam getSubFamiliaPorPK(Long nrsubfam) {
			if (nrsubfam != null && nrsubfam != 0){
				return extendedSubFamDAO.getByPrimaryKey(nrsubfam);
			}else{
				return new SubFam(nrsubfam);
			}			
		}

		public ArtMadre getArtMadreProPK(String codArtMad) {
				
			return extendedArtMadreDAO.getByPrimaryKey(codArtMad);
		}
		
		public List<Stock> getArticuloProNombreLimit(String filter, int i) {
 	  		List<Stock> stockList = null;
			try {
				stockList = extendedStockDAO.getArticuloPorNombreLimit(filter, i);
			} catch (Exception e) {
				e.printStackTrace();
			}			
 	  		return stockList;

		}

		public List<GenteBasic> getProveedorBasicPorNombre(String filter, Usuario usuario ) {
			List<GenteBasic> listGente = null;
			try { 
				if (usuario.getRol() == Constants.ID_USR_GERENTE){
					listGente = extendedGenteBasicDAO.getProveedorBasicPorNombre(filter);
				}
				if (usuario.getRol() == Constants.ID_USR_VENDEDOR){
					listGente = extendedGenteBasicDAO.getProveedorBasicPorNombreVendedor(filter, usuario.getVendedorNr());
				}
				
			} catch (Exception e) {

				e.printStackTrace();
			}	
			return listGente;
		}
		
		public GenteBasic getGentePorPKVendedor(Integer genteNr,
				Usuario usuario) {
			GenteBasic genteBasic = null;
			try {
				if (usuario.getRol() == Constants.ID_USR_GERENTE){
					genteBasic = extendedGenteBasicDAO.getByPrimaryKey(genteNr);
				}
				if (usuario.getRol() == Constants.ID_USR_VENDEDOR){
					genteBasic = extendedGenteBasicDAO.getProveedorBasicPorCodVendedor(genteNr, usuario.getVendedorNr());
				}
				
			} catch (Exception e) {

				e.printStackTrace();
			}	
			return genteBasic;

		}

		public String getFormulaStock(String clave, Usuario usuarioSesion, Integer isCarrito) {
			
			//Analizar que preferencia tiene la empresa con id Preferencia -1
			//Los valores son 
			//1 = Contenido por tabla completa
			//2 = Semaforo
			//3 = Formula Herramientas
			
			String result = "";
			Double valor = 2d;
			if (isCarrito == 1){
				valor = 2d;
			}else{
				if (usuarioSesion.getRol() != Constants.ID_USR_CLIENTE){
					try{
						valor = PreferenciasUtil.comparePreferencia(usuarioSesion.getListPreferencias(),Constants.PREF_ID_FORMA_MUESTRA_STOCK);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			switch (valor.intValue()) {
			case 1:
				result = getFormulaCompleta(clave, usuarioSesion);
				break;
			case 2:
				result = getFormulaSemaforo(clave, usuarioSesion, 0d);
				break;
			case 3:
				result = getResumenPedidos(clave, usuarioSesion);
				break;				
			case 4:
				result = getTableResumenPedidos(clave, usuarioSesion, isCarrito);
				break;				
			default:
				result = getFormulaSemaforo(clave, usuarioSesion, 0d);
				break;
			}
			return result;
			
		}
		
		private String getFormulaSemaforo(String clave, Usuario usuarioSesion, Double cantidad) {
			//Analizar Stock
			Double usaLogicaStockColor = PreferenciasUtil.comparePreferencia(usuarioSesion.getListPreferencias(), Constants.PREF_ID_USA_LOGICA_STOCK_COLOR);
			
			String rojo = "";

			String amarillo = "";

			String verde = "";
			
			Double idExistencia = PreferenciasUtil.comparePreferencia(usuarioSesion.getListPreferencias(), Constants.PREF_ID_EXISTENCIA_DEFAULT);
			
			Double stock = extendedExiArtDAO.getStockByExiClave(idExistencia, clave);
			String neutro = "";
			
			
			if (usaLogicaStockColor != -1){
				 rojo = "<img src=\"images/general/sema_rojo.png\" alt=\"\">";
	
				 amarillo = "<img src=\"images/general/sema_amarillo.png\" alt=\"\">";
	
				 verde = "<img src=\"images/general/sema_verde.png\" alt=\"\">";
				 
				 neutro = "<img src=\"images/general/sema_neutro.png\" alt=\"\">";
				
			
			}else{
				 rojo = "<img src=\"images/general/sema_rojo_chico.png\" alt=\"\">";

				 amarillo = "<img src=\"images/general/sema_amarillo_chico.png\" alt=\"\">";

				 verde = "<img src=\"images/general/sema_verde_chico.png\" alt=\"\">";
				 
				 neutro = "<img src=\"images/general/sema_neutro_chico.png\" alt=\"\">";
				 
				 stock = cantidad;
			}
			
			
			Stock art = extendedStockDAO.getByPrimaryKey(clave);
			
			Double minimo = art.getMinimoRepo();
			
			if (art.getPreEsp5().doubleValue()==(new BigDecimal(-1)).doubleValue()){
				return neutro;
			}
			if(stock > minimo){
				return verde;
			}
			if(stock <= 0){
				return rojo;
			}
			if(stock > 0 && stock <= minimo){
				return amarillo;
			}
			return rojo;
		}
		

		
		
		public String getTableResumenPedidos(String clave, Usuario usuarioSesion, Integer carrito){
			//Defino el objeto de salida
			String result = "<br>No posee pedidos de compra";
			//Defino Objeto de temporal de carga
			List<TransacJoin> resultList = new ArrayList<TransacJoin>();
			//Obtengo la lista de Pedidos de Compra ordenadas por fecha, transacnr
			List<TransacJoin> compraList = extendedTransacDAO.getTransacJoinOByFEntregaTransac(Constants.ID_TIPO_COMP_PEDIDO_COMPRA, clave);
			//Obtengo el total de pedidos de Venta
			Double cantidadPedidoVenta = extendedTransacDAO.getTotalDisponibleByArtComprob(clave, Constants.ID_TIPO_COMP_PEDIDO_VENTA);
			//Obtengo la existencia por Default
			Double idExistencia = PreferenciasUtil.comparePreferencia(usuarioSesion.getListPreferencias(), Constants.PREF_ID_EXISTENCIA_DEFAULT);
			//Obtengo el Stock del producto
			Double stock = extendedExiArtDAO.getStockByExiClave(idExistencia, clave);
			//Restar Stcok Fisico
			cantidadPedidoVenta = cantidadPedidoVenta - stock; 
			
			BigDecimal cantVtaBD = new BigDecimal(cantidadPedidoVenta);
			//Defino el valor temporal de cantidad de Vta a cubrir
			BigDecimal  vtaTemp = new BigDecimal(0);
			//Defino flag de salida por si alguna de las fechas de entrega es nula
			boolean entregaNull = false;
			//Recorro Pedido de Compra
//			for(TransacJoin com: compraList){
//				if(com.getFechaEntrega() == null)
//					entregaNull = true;
//				vtaTemp =  com.getCant1().subtract(cantVtaBD);
//				int subtract = vtaTemp.compareTo(BigDecimal.ZERO);
//				switch (subtract) {
//				case -1:
//					//En caso que el p de Compra sea menor al de vta
//					cantVtaBD =  cantVtaBD.subtract(com.getCant1());
//					//com.setDelete(true);
//					//compraList.remove(com);
//					break;
//				case 0:
//					//En caso que el p de Compra sea igual al de vta
//					//Elimino el p de compra para no mostrarlo
//					//compraList.remove(com);
//					//com.setDelete(true);
//					cantVtaBD= BigDecimal.ZERO;
//					break;
//				case 1:
//					//En caso que el p de Compra sea mayor al de vta
//					com.setCant1(vtaTemp);
//					cantVtaBD= BigDecimal.ZERO;
//					resultList.add(com);
//					break;
//				}
//				
//			}
			resultList = compraList;
			
			if (entregaNull){
				result = "<br>No se pudo realizar el analisis de entrega ya que existen ordenes de compra sin fecha de entrega cargada";
			}else{
				if (compraList.size() == 0){
					//Tomar de la base variable de resultante sin pedido de compra
				}else{
					//Convierto en Tabla HTML
					if (PreferenciasUtil.comparePreferencia(usuarioSesion.getListPreferencias(), Constants.PREF_ID_USA_LOGICA_STOCK_COLOR) == -1d){
						for(TransacJoin transaJoin: resultList){
							Transac transac = extendedTransacDAO.getByPrimaryKey(transaJoin.getTransacNr());
							transaJoin.setItemsList(transac.getItemsList());
							result = FormatUtil.getTableHTMLPedidoResumen(resultList,"Pedidos de Compra con disponibilidad", true);
						}
					}else{
						result = FormatUtil.getTableHTMLPedidoResumen(resultList,"Pedidos de Compra con disponibilidad", false);
					}
				}
			}
			
			if(carrito != 3){
				result = getFormulaSemaforo(clave,usuarioSesion,0d) + result;
			}
			return result;
		}
		
		private String getResumenPedidos(String clave, Usuario usuarioSesion) {
			
			String result = "";										
			
			//String result = "Disponible(<b>@resultante</b>) = Stock(@stock) - PedidoVta(@pedidoVta) + PedidoCompra(@pedidoCompra)";
			//Stock(@stock) - <h class="link" onClick="loadPedidoCompraResumen();"> Pedido de Compra Disponible(@pedidoCompra)<img style="display: none" alt="" src="images/general/loading1.gif"  id="idLoadPedidoCompraImg"></h>
			
			
			Double idExistencia = PreferenciasUtil.comparePreferencia(usuarioSesion.getListPreferencias(), Constants.PREF_ID_EXISTENCIA_DEFAULT);
			
			Double stock = extendedExiArtDAO.getStockByExiClave(idExistencia, clave);
			
			Double cantidadPedidoCompra = extendedTransacDAO.getTotalDisponibleByArtComprob(clave, Constants.ID_TIPO_COMP_PEDIDO_COMPRA);
			
			Double cantidadPedidoVenta = extendedTransacDAO.getTotalDisponibleByArtComprob(clave, Constants.ID_TIPO_COMP_PEDIDO_VENTA);
			
			if (cantidadPedidoCompra > cantidadPedidoVenta){
				result = parametrizacionDAO.getByPrimaryKey(Constants.ID_PARAM_MSG_VISTA_STOCK_RESUMEN).getValor();	
				result = result.replace("@stock", String.valueOf(stock)).replace("@pedidoCompra", String.valueOf(cantidadPedidoCompra - cantidadPedidoVenta));
			}else{
				result = "Stock(@stock)".replace("@stock", String.valueOf(stock));
			}
						
			return result;
		}

		
		private String getFormulaCompleta(String clave, Usuario usuarioSesion) {
			
			String result = "";										
			
			//String result = "Disponible(<b>@resultante</b>) = Stock(@stock) - PedidoVta(@pedidoVta) + PedidoCompra(@pedidoCompra)";
			
			result = parametrizacionDAO.getByPrimaryKey(Constants.ID_PARAM_MSG_VISTA_STOCK).getValor();
			
			Double idExistencia = PreferenciasUtil.comparePreferencia(usuarioSesion.getListPreferencias(), Constants.PREF_ID_EXISTENCIA_DEFAULT);
			
			Double stock = extendedExiArtDAO.getStockByExiClave(idExistencia, clave);
			
			Double cantidadPedidoCompra = extendedTransacDAO.getTotalDisponibleByArtComprob(clave, Constants.ID_TIPO_COMP_PEDIDO_COMPRA);
			
			Double cantidadPedidoVenta = extendedTransacDAO.getTotalDisponibleByArtComprob(clave, Constants.ID_TIPO_COMP_PEDIDO_VENTA);
			
			Double resultante = (stock - cantidadPedidoVenta) + cantidadPedidoCompra;
			
			result = result.replace("@resultante", String.valueOf(resultante)).replace("@stock", String.valueOf(stock)).replace("@pedidoVta", String.valueOf(cantidadPedidoVenta)).replace("@pedidoCompra", String.valueOf(cantidadPedidoCompra));
			
			return result;
		}

		public String updateArticulo(String clave, File imageFile) {
			String result = "";
			try{
				Stock stock = extendedStockDAO.getByPrimaryKey(clave);
				ImagenesArticulos imagenesArticulos = new ImagenesArticulos(clave, FileUtil.getBytesFromFile(imageFile)); 
				stock.setImagenesArticulos(imagenesArticulos);
				extendedStockDAO.update(stock);				
			}catch(Exception e){
				result = "error";
			} 
			
			return result;
		}

		public List<Stock> getImagenArticuloPorFliaSubFlia(Long nrfam,
				Long nrsubfam, boolean activos) {

			List<Stock> listArt = null;
			
			if(nrfam == 0 && nrsubfam != 0){
				listArt = extendedStockDAO.getImagenArticuloPorNrSubFam(nrsubfam,activos);
			}
			if(nrfam != 0 && nrsubfam != 0){
				listArt = extendedStockDAO.getImagenArticuloPorNrFamSubFam(nrfam, nrsubfam, activos);
			}
			if(nrfam != 0 && nrsubfam == 0){
				listArt = extendedStockDAO.getImagenArticuloPorNrFam(nrfam,activos);
			}
			return listArt;

		}
		
		public ImagenesArticulos getImageDefault() {
			String clave = "SINIMG_0000";
			return  imagenesArticulosDAO.getByPrimaryKey(clave);
		}
		
		public ImagenesArticulos getImageArticuloByPK(String clave) {
			return  imagenesArticulosDAO.getByPrimaryKey(clave);
		}
		
		
		public List<Stock> getArticuloPorFliaSubFlia(Long nrfam, Long nrsubfam,
				boolean activos, String propertySort, String orderSort,
				String[] propertyFilter, String[] valueFilter, Integer min,
				int pageSize) {
			List<Stock> listArt = null;
			
			if(nrfam == 0 && nrsubfam != 0){
				listArt = extendedStockDAO.getArticuloPorNrSubFam(nrsubfam,activos,propertySort,orderSort,propertyFilter,valueFilter,min,pageSize);
			}
			if(nrfam != 0 && nrsubfam != 0){
				listArt = extendedStockDAO.getArticuloPorNrFamSubFam(nrfam, nrsubfam, activos,propertySort,orderSort,propertyFilter,valueFilter,min,pageSize);
			}
			if(nrfam != 0 && nrsubfam == 0){
				listArt = extendedStockDAO.getArticuloPorNrFam(nrfam,activos,propertySort,orderSort,propertyFilter,valueFilter,min,pageSize);
			}
			
			return listArt;
		}
		
		public Long getCountArticuloPorFliaSubFlia(Long nrfam, Long nrsubfam,
				boolean activos, String propertySort, String orderSort,
				String[] propertyFilter, String[] valueFilter, Integer min,
				int pageSize) {
			Long count = 0l;
			if(nrfam == 0 && nrsubfam != 0){
				count = extendedStockDAO.getCountArticuloPorNrSubFam(nrsubfam,activos,propertySort,orderSort,propertyFilter,valueFilter,min,pageSize);
			}
			if(nrfam != 0 && nrsubfam != 0){
				count = extendedStockDAO.getCountArticuloPorNrFamSubFam(nrfam,nrsubfam,activos,propertySort,orderSort,propertyFilter,valueFilter,min,pageSize);
			}
			if(nrfam != 0 && nrsubfam == 0){
				count = extendedStockDAO.getCountArticuloPorNrFam(nrfam,activos,propertySort,orderSort,propertyFilter,valueFilter,min,pageSize);
			}
			 
			return count;
		}
		
		public Long getCountArticuloPorNombre(String descripcion,
				boolean articuloActivo, String propertySort, String orderSort,
				String[] propertyFilter, String[] valueFilter, Integer min,
				int pageSize) {
			Long count = extendedStockDAO.getCountArticuloPorNombre(descripcion,articuloActivo,propertySort,orderSort,propertyFilter,valueFilter,min,pageSize);			
			return count;
			
		}
		
		public List<Stock> getArticuloPorNombre(String descripcion,
				boolean activos, String propertySort, String orderSort,
				String[] propertyFilter, String[] valueFilter, Integer min,
				int pageSize) {
			
			return  extendedStockDAO.getArticuloPorNombre(descripcion,activos,propertySort,orderSort,propertyFilter,valueFilter,min,pageSize);
		}

		public List<Stock> getArticuloPorCodigoLike(String clave,
				boolean articuloActivo, Usuario usuario) {
			Double idPreferenciaDefault = PreferenciasUtil.comparePreferencia(usuario.getListPreferencias(), Constants.PREF_ID_MUESTRA_OTROS_ART_SI_HAY_UNO_IGUAL);
			if (idPreferenciaDefault == 0){
				//Pregunto si hay uno igual por string
				List<Stock> list = extendedStockDAO.getArticuloPorClaveList(clave, articuloActivo);
				if (list.size() > 0){
					return list;
				}
			}
			List<Stock> stockList = null;
			try {
				stockList = extendedStockDAO.getArticuloPorCodigoLike(clave,articuloActivo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
 	  		return stockList;
		}
		
		public Long getCountArticuloPorCodigo(String clave,
				boolean articuloActivo, String propertySort, String orderSort,
				String[] propertyFilter, String[] valueFilter, Integer min,
				int pageSize) {
			Long count = extendedStockDAO.getCountArticuloPorCodigo(clave,articuloActivo,propertySort,orderSort,propertyFilter,valueFilter,min,pageSize);			
			return count;
		}
		
		public List<Stock> getArticuloPorCodigo(String clave,
				boolean articuloActivo, String propertySort, String orderSort,
				String[] propertyFilter, String[] valueFilter, Integer min,
				int pageSize) {
			return  extendedStockDAO.getArticuloPorClave(clave,articuloActivo,propertySort,orderSort,propertyFilter,valueFilter,min,pageSize);
		}

		public List<StockPiezas> getStockPiezasPorArt(String clave, Usuario usuario) {
			List<StockPiezas> stockPiezasList =extendedStockPiezasDAO.getStockPiezasPorArticulo(clave);
			for(StockPiezas sp: stockPiezasList){
				sp.setImagenSemaforo(getFormulaSemaforo(clave, usuario, sp.getCant1()));		
			}
			if (stockPiezasList != null){
				Stock stock = extendedStockDAO.getByPrimaryKey(clave);
				if (stock != null){
				
					StockPiezas s = new StockPiezas();
					StockPiezasId si = new StockPiezasId();
					
					
					Colores colores = new Colores();
					colores.setDescrip("----");
					colores.setNr(0);
					
					si.setColores(colores); 
					si.setStock(stock);
					
					s.setCant1(0d);
					s.setId(si);
					
					stockPiezasList.add(s);
				
				}
			}
			
			return stockPiezasList;
		}

		public List<OptionsSelect> getOptionsSelect(String clave,
				Integer idCombo, Integer usaSeleccion) {
			List<OptionsSelect> optionsList = new ArrayList<OptionsSelect>();
			Integer tipoReporte = 0;
			switch (idCombo) {
			case 1:
				Integer fam = Integer.parseInt(clave);
				try {
					List<SubFam> subFamList = extendedSubFamDAO.getSubFamByFam(fam);
					optionsList = getOpetionsSelectSubFlia(subFamList, usaSeleccion );
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 2:
				tipoReporte = Integer.parseInt(clave);
				try {
					EstadiTipoReporte estadiTipoReporte = estadiTipoReporteDAO.getByPrimaryKey(tipoReporte);
					optionsList = getOptionsSelectOrigenReporte(estadiTipoReporte.getListEstadiOrigen(), usaSeleccion );
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 3:
				tipoReporte = Integer.parseInt(clave);
				try {
					EstadiTipoReporte estadiTipoReporte = estadiTipoReporteDAO.getByPrimaryKey(tipoReporte);
					optionsList = getOptionsSelectReporte(estadiTipoReporte.getListEstadiReporte(), usaSeleccion );
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
						
			return optionsList;
		}
		
		private List<OptionsSelect> getOpetionsSelectSubFlia(List<SubFam> subFamList, Integer usaSeleccion){
			List<OptionsSelect> optionsList = new ArrayList<OptionsSelect>();
			if (usaSeleccion == -1 ){
				OptionsSelect option = new OptionsSelect();
				option.setId(String.valueOf(0));
				option.setValue("[0] Seleccione una opcion");
				optionsList.add(option);				
			}
			if (subFamList != null){
				for(SubFam subFam: subFamList){
					OptionsSelect option = new OptionsSelect();
					option.setId(String.valueOf(subFam.getNrsubfam()));
					option.setValue(subFam.getDescripC());
					optionsList.add(option);
				}
			}
			return optionsList;
		}
		

		private List<OptionsSelect> getOptionsSelectOrigenReporte(List<EstadiOrigen> origenList, Integer usaSeleccion){
			List<OptionsSelect> optionsList = new ArrayList<OptionsSelect>();
			if (usaSeleccion == -1 ){
				OptionsSelect option = new OptionsSelect();
				option.setId(String.valueOf(0));
				option.setValue("[0] Seleccione una opcion");
				optionsList.add(option);				
			}
			if (origenList != null){
				for(EstadiOrigen estadiOrigen: origenList){
					OptionsSelect option = new OptionsSelect();
					option.setId(String.valueOf(estadiOrigen.getNr()));
					option.setValue(estadiOrigen.getDescrip());
					optionsList.add(option);
				}
			}
			return optionsList;
		}


		private List<OptionsSelect> getOptionsSelectReporte(List<EstadiReporte> reporteList, Integer usaSeleccion){
			List<OptionsSelect> optionsList = new ArrayList<OptionsSelect>();
			if (usaSeleccion == -1 ){
				OptionsSelect option = new OptionsSelect();
				option.setId(String.valueOf(0));
				option.setValue("[0] Seleccione una opcion");
				optionsList.add(option);				
			}
			if (reporteList != null){
				for(EstadiReporte estadiReporte: reporteList){
					OptionsSelect option = new OptionsSelect();
					option.setId(String.valueOf(estadiReporte.getNr()));
					option.setValue(estadiReporte.getDescrip());
					optionsList.add(option);
				}
			}
			return optionsList;
		}

		public List<SubFam> getOptionsDefault() {
			SubFam subFam = new SubFam();
			subFam.setDesubfa("Seleccione una opcion");
			subFam.setNrsubfam(0l);
			List<SubFam> subFamList = new ArrayList<SubFam>();
			subFamList.add(subFam);
			return subFamList;
		}
		
		public List<GenteBasic> getClienteBasicPorVendedor(Integer vendedorNr) {

			
			return extendedGenteBasicDAO.getClienteBasicPorVendedor(vendedorNr);
		}
		
		public List<GroupCantTransac> getOperacionByArtTipoCompr(String clave,
				Integer idTipoCompPedidoCompra) {
			return extendedTransacDAO.getOperacionByArtTipoCompr(clave,
					idTipoCompPedidoCompra);
			
		}

}
