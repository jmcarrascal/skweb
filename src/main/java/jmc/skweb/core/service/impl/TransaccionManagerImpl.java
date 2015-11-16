package jmc.skweb.core.service.impl;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.criterion.Restrictions;

import jmc.skweb.core.dao.ClieArticPrecioDAO;
import jmc.skweb.core.dao.CondiDAO;
import jmc.skweb.core.dao.DomiciliosDAO;
import jmc.skweb.core.dao.ExiArtDAO;
import jmc.skweb.core.dao.FamDAO;
import jmc.skweb.core.dao.GenericDAO;
import jmc.skweb.core.dao.GenteDAO;
import jmc.skweb.core.dao.ItemsDAO;
import jmc.skweb.core.dao.StockDAO;
import jmc.skweb.core.dao.SubFamDAO;
import jmc.skweb.core.dao.TransacDAO;
import jmc.skweb.core.dao.TrazabiDAO;
import jmc.skweb.core.model.ClieArticPrecio;
import jmc.skweb.core.model.Colores;
import jmc.skweb.core.model.Condi;
import jmc.skweb.core.model.Domicilios;
import jmc.skweb.core.model.DondeEstaFactuMail;
import jmc.skweb.core.model.EstadiOrigen;
import jmc.skweb.core.model.EstadiReporte;
import jmc.skweb.core.model.EstadiTipoReporte;
import jmc.skweb.core.model.ExiArt;
import jmc.skweb.core.model.Existencias;
import jmc.skweb.core.model.Fam;
import jmc.skweb.core.model.Gente;
import jmc.skweb.core.model.Impuestos;
import jmc.skweb.core.model.Items;
import jmc.skweb.core.model.ItemsId;
import jmc.skweb.core.model.Numeraciones;
import jmc.skweb.core.model.Parametrizacion;
import jmc.skweb.core.model.Stock;
import jmc.skweb.core.model.SubFam;
import jmc.skweb.core.model.TipoComprob;
import jmc.skweb.core.model.Transac;
import jmc.skweb.core.model.Usuario;
import jmc.skweb.core.model.UsuarioWeb;
import jmc.skweb.core.model.report.DatosReporte;
import jmc.skweb.core.model.report.EstadisticaPedido;
import jmc.skweb.core.model.report.SaldoAcumulado;
import jmc.skweb.core.model.report.TipoReporte;
import jmc.skweb.core.model.report.TransacJoin;
import jmc.skweb.core.model.traza.Trazabi;
import jmc.skweb.core.service.ArticuloManager;
import jmc.skweb.core.service.CuentaCorrienteManager;
import jmc.skweb.core.service.TransaccionManager;
import jmc.skweb.core.service.UsuarioManager;
import jmc.skweb.util.Constants;
import jmc.skweb.util.DateUtil;
import jmc.skweb.util.FormatUtil;
import jmc.skweb.util.MathUtil;
import jmc.skweb.util.PreferenciasUtil;
import jmc.skweb.util.email.Email;

/**
 * @author Juan Manuel Carrascal
 *
 */
/**
 * @author Administrator
 *
 */
/**
 * @author vaio
 * 
 */
public class TransaccionManagerImpl implements TransaccionManager {

	private GenteDAO extendedGenteDAO;
	private TransacDAO extendedTransacDAO;
	private DomiciliosDAO extendedDomiciliosDAO;
	private ItemsDAO extendedItemsDAO;
	private GenericDAO<TipoComprob> tipoComprobDAO;
	private GenericDAO<DondeEstaFactuMail> dondeEstaFactuMailDAO;
	private ClieArticPrecioDAO extendedClieArticPrecioDAO;
	private StockDAO extendedStockDAO;
	private GenericDAO<Numeraciones> numeracionesDAO;
	private UsuarioManager usuarioManager;
	private GenericDAO<Parametrizacion> parametrizacionDAO;
	private TrazabiDAO extendedTrazabiDAO;
	private GenericDAO<Colores> coloresDAO;
	private CondiDAO extendedCondiDAO;
	private GenericDAO<EstadiTipoReporte> estadiTipoReporteDAO;
	private GenericDAO<EstadiOrigen> estadiOrigenDAO;
	private GenericDAO<EstadiReporte> estadiReporteDAO;
	
	
	public GenericDAO<EstadiTipoReporte> getEstadiTipoReporteDAO() {
		return estadiTipoReporteDAO;
	}

	public void setEstadiTipoReporteDAO(
			GenericDAO<EstadiTipoReporte> estadiTipoReporteDAO) {
		this.estadiTipoReporteDAO = estadiTipoReporteDAO;
	}

	public GenericDAO<EstadiOrigen> getEstadiOrigenDAO() {
		return estadiOrigenDAO;
	}

	public void setEstadiOrigenDAO(GenericDAO<EstadiOrigen> estadiOrigenDAO) {
		this.estadiOrigenDAO = estadiOrigenDAO;
	}

	public GenericDAO<EstadiReporte> getEstadiReporteDAO() {
		return estadiReporteDAO;
	}

	public void setEstadiReporteDAO(GenericDAO<EstadiReporte> estadiReporteDAO) {
		this.estadiReporteDAO = estadiReporteDAO;
	}

	public CondiDAO getExtendedCondiDAO() {
		return extendedCondiDAO;
	}

	public void setExtendedCondiDAO(CondiDAO extendedCondiDAO) {
		this.extendedCondiDAO = extendedCondiDAO;
	}

	public GenericDAO<Colores> getColoresDAO() {
		return coloresDAO;
	}

	public void setColoresDAO(GenericDAO<Colores> coloresDAO) {
		this.coloresDAO = coloresDAO;
	}

	public TrazabiDAO getExtendedTrazabiDAO() {
		return extendedTrazabiDAO;
	}

	public void setExtendedTrazabiDAO(TrazabiDAO extendedTrazabiDAO) {
		this.extendedTrazabiDAO = extendedTrazabiDAO;
	}

	public GenericDAO<Parametrizacion> getParametrizacionDAO() {
		return parametrizacionDAO;
	}

	public void setParametrizacionDAO(GenericDAO<Parametrizacion> parametrizacionDAO) {
		this.parametrizacionDAO = parametrizacionDAO;
	}

	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}

	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}

	public GenericDAO<Numeraciones> getNumeracionesDAO() {
		return numeracionesDAO;
	}

	public void setNumeracionesDAO(GenericDAO<Numeraciones> numeracionesDAO) {
		this.numeracionesDAO = numeracionesDAO;
	}

	public StockDAO getExtendedStockDAO() {
		return extendedStockDAO;
	}

	public void setExtendedStockDAO(StockDAO extendedStockDAO) {
		this.extendedStockDAO = extendedStockDAO;
	}	
	
	public DomiciliosDAO getExtendedDomiciliosDAO() {
		return extendedDomiciliosDAO;
	}

	public void setExtendedDomiciliosDAO(DomiciliosDAO extendedDomiciliosDAO) {
		this.extendedDomiciliosDAO = extendedDomiciliosDAO;
	}

	public ClieArticPrecioDAO getExtendedClieArticPrecioDAO() {
		return extendedClieArticPrecioDAO;
	}

	public void setExtendedClieArticPrecioDAO(
			ClieArticPrecioDAO extendedClieArticPrecioDAO) {
		this.extendedClieArticPrecioDAO = extendedClieArticPrecioDAO;
	}

	public ItemsDAO getExtendedItemsDAO() {
		return extendedItemsDAO;
	}

	public void setExtendedItemsDAO(ItemsDAO extendedItemsDAO) {
		this.extendedItemsDAO = extendedItemsDAO;
	}

	public GenericDAO<DondeEstaFactuMail> getDondeEstaFactuMailDAO() {
		return dondeEstaFactuMailDAO;
	}

	public void setDondeEstaFactuMailDAO(
			GenericDAO<DondeEstaFactuMail> dondeEstaFactuMailDAO) {
		this.dondeEstaFactuMailDAO = dondeEstaFactuMailDAO;
	}

	public GenericDAO<TipoComprob> getTipoComprobDAO() {
		return tipoComprobDAO;
	}

	public void setTipoComprobDAO(GenericDAO<TipoComprob> tipoComprobDAO) {
		this.tipoComprobDAO = tipoComprobDAO;
	}

	public GenteDAO getExtendedGenteDAO() {
		return extendedGenteDAO;
	}

	public void setExtendedGenteDAO(GenteDAO extendedGenteDAO) {
		this.extendedGenteDAO = extendedGenteDAO;
	}

	public TransacDAO getExtendedTransacDAO() {
		return extendedTransacDAO;
	}

	public void setExtendedTransacDAO(TransacDAO extendedTransacDAO) {
		this.extendedTransacDAO = extendedTransacDAO;
	}

	/**
	 * Agrega un Item a la Lista (non-Javadoc)
	 * 
	 * @see jmc.skweb.core.service.TransaccionManager#addItem(java.util.List,
	 *      jmc.skweb.core.model.Items)
	 */
	public List<Items> addItem(List<Items> itemsSessionList, Items itemsOrig, Usuario usuario, boolean setPrecio) {		
		try{
			itemsOrig.setDescrip(extendedStockDAO.getByPrimaryKey(itemsOrig.getClave()).getDescripcion().trim() + " " + itemsOrig.getColores().getNr() + ":"+ itemsOrig.getColores().getDescrip());
			
			
		}catch(Exception e){
			itemsOrig.setDescrip(extendedStockDAO.getByPrimaryKey(itemsOrig.getClave()).getDescripcion());
		}
		Items items = new Items();
		try {
			BeanUtils.copyProperties(items, itemsOrig);
		} catch (IllegalAccessException e) {			
			e.printStackTrace();
		} catch (InvocationTargetException e) {			
			e.printStackTrace();
		}
		
		Gente genteTemp = extendedGenteDAO.getByPrimaryKey(usuario.getGenteSession().getGenteNr());
		if (setPrecio){
			items.setPrecio(getPrecioClienteArticulo(usuario.getGenteSession().getGenteNr(), items.getClave(), usuario, true));
			items.setBonif(new BigDecimal(genteTemp.getBonifStd()));
			if (items.getObser() == "00+00+00+00" && genteTemp.getBonifStd() > 0f){
				items.setObser(FormatUtil.llenoConCeros(String.valueOf(genteTemp.getBonifStd().intValue()),2) + "+00+00+00");
			}
		}else{
			if (usuario.getRol() == Constants.ID_USR_CLIENTE){
				items.setPrecio(getPrecioClienteArticulo(usuario.getGenteSession().getGenteNr(), items.getClave(), usuario, true));
				items.setBonif(new BigDecimal(usuario.getGenteSession().getBonifStd()));
				if (items.getObser() == "00+00+00+00" && genteTemp.getBonifStd() > 0f){
					items.setObser(FormatUtil.llenoConCeros(String.valueOf(usuario.getGenteSession().getBonifStd().intValue()),2) + "+00+00+00");
				}
			}		
		}
		Integer cantidad = itemsSessionList.size();
		items.setArticulo(items.getClave().toUpperCase());
		
		items.setTalle(".");	
		try{
		items.setExi(PreferenciasUtil.comparePreferencia(usuario.getListPreferencias(), Constants.PREF_ID_EXISTENCIA_DEFAULT).intValue());
		}catch(NumberFormatException ne){
			ne.printStackTrace();
		}
		if (items.getColores() != null && items.getColores().getNr() != null){
			Colores color = coloresDAO.getByPrimaryKey(items.getColores().getNr());
			items.setColores(color);
		}else{
			Colores color = coloresDAO.getByPrimaryKey(0);
			items.setColores(color);			
		}
		items.setFechaTransac(DateUtil.composeCanonicalFechPresentationDate(DateUtil.getFormatedSTDDate(new Timestamp(System.currentTimeMillis()))));
		items.setFecha(DateUtil.getFormatSkDate(new Timestamp(System.currentTimeMillis())));
		//items.setFecha(DateUtil.composeCanonicalFechPresentationDate(DateUtil.getFormatedSTDDate(new Timestamp(System.currentTimeMillis()))));
		items.setNrFabInt("."); 
		items.setLetra("X");
		//if (PreferenciasUtil.comparePreferencia(usuario.getListPreferencias(), Constants.PREF_ID_VALOR_SI_ES_SIMPA).intValue() == -1d){
			items.setCant2(items.getCant1());
		//}
		cantidad++;
		items.getId().setItemNr(cantidad);
		//BigDecimal bonif = MathUtil.redondearEn4BD(items.getBonif().divide(new BigDecimal(100)));
		BigDecimal bonif = MathUtil.redondearEn4BD(itemsOrig.getBonif().divide(new BigDecimal(100)));
		BigDecimal bruto = (items.getPrecio().multiply(items.getCant1()));
		BigDecimal totalItem = bruto.subtract(bruto.multiply(bonif));
		items.setNrCompInt(String.valueOf(MathUtil.redondearEn2BD(totalItem)));
		totalItem = MathUtil.redondearEn2BD(totalItem);
		items.setTotalItem(totalItem);
		// Calculo el impueto del Item
		items = generateImpuesto(items);
		itemsSessionList.add(items);
		Integer i = 1;
		for (Items itemsL : itemsSessionList) {
			ItemsId id = new ItemsId();
			id.setItemNr(i);
			id.setTransac(itemsL.getId().getTransac());
			itemsL.setId(id);
			i++;
		}
		return itemsSessionList;
	}

	/**
	 * Calculo impuesto de un articulo
	 */
	private Items generateImpuesto(Items item) {
		Stock articulo = extendedStockDAO.getByPrimaryKey(item.getArticulo());
		Impuestos impuesto = articulo.getImpuestos();
		item.setPorcentajeImpuesto(impuesto.getAlicuota());
		item.setTotalImpuesto(item.getTotalItem()
				.multiply(new BigDecimal(impuesto.getAlicuota()))
				.divide(new BigDecimal(100)));
		return item;
	}

	/**
	 * Elimina un Item
	 */
	public List<Items> removeItem(List<Items> itemsSessionList, Items items) {
		itemsSessionList.remove(items.getId().getItemNr() - 1);
		Integer i = 1;
		for (Items itemsL : itemsSessionList) {
			itemsL.getId().setItemNr(i);
			i++;
		}
		return itemsSessionList;
	}

	public Transac calculoTotales(List<Items> itemsSessionList) {
		Transac transac = new Transac();
		BigDecimal subTotal = new BigDecimal(0);
		BigDecimal totalImpuestos = new BigDecimal(0);
		for (Items itemsL : itemsSessionList) {
			subTotal = subTotal.add(itemsL.getTotalItem());
			totalImpuestos = totalImpuestos.add(itemsL.getTotalImpuesto());
		}
		subTotal = MathUtil.redondearEn2BD(subTotal);
		totalImpuestos = MathUtil.redondearEn2BD(totalImpuestos);
		transac.setNetoGrav(subTotal);
		transac.setIva(totalImpuestos);
		transac.setTotal(subTotal.add(totalImpuestos));
		return transac;
	}

	public BigDecimal getPrecioClienteArticulo(Integer genteNr, String clave, Usuario usuario, boolean muestraEnPesos) {
		
		BigDecimal precio = new BigDecimal(0);
		// Obtengo la lista de Precios
		
		
		ClieArticPrecio clieArticPrecio = null;
		Integer listaPrecio = 1;
		try{
			listaPrecio = Integer.valueOf(String.valueOf(PreferenciasUtil.comparePreferencia(usuario.getListPreferencias(), Constants.PREF_ID_LISTA_DEFAULT_PRECIO)));
		}catch(Exception we){
			
		}
			
		Stock stock = extendedStockDAO.getByPrimaryKey(clave);
		if (genteNr != null){
			Gente gente = extendedGenteDAO.getByPrimaryKey(genteNr);
			clieArticPrecio = extendedClieArticPrecioDAO
					.getPrecioClienteArticulo(genteNr, clave);
			listaPrecio = gente.getListaPrecio();			
		}

		if (clieArticPrecio != null) {
			switch (listaPrecio) {
			case 1:
				precio = clieArticPrecio.getP1();
				break;
			case 2:
				precio = clieArticPrecio.getP2();
				break;
			case 3:
				precio = clieArticPrecio.getP3();
				break;
			case 4:
				precio = clieArticPrecio.getP4();
				break;
			case 5:
				precio = clieArticPrecio.getP5();
				break;
			case 6:
				precio = clieArticPrecio.getP6();
				break;
			default:
				precio = new BigDecimal(0);
				break;
			}
		} else {
			
			switch (listaPrecio) {
			case 1:
				precio = stock.getPrecio1();
				break;
			case 2:
				precio = stock.getPrecio2();
				break;
			case 3:
				precio = stock.getPrecio3();
				break;
			case 4:
				precio = stock.getPrecio4();
				break;
			case 5:
				precio = stock.getPrecio5();
				break;
			case 6:
				precio = stock.getPrecio6();
				break;
			default:
				precio = new BigDecimal(0);
				break;
			}

		}
		if (muestraEnPesos){
			if (stock.getTipoCalculo().equals(Short.parseShort("6"))){
				//Tomo el valor de conversi�n del dolar 
				Double valorMoneda = PreferenciasUtil.comparePreferencia(usuario.getListPreferencias(), Constants.PREF_ID_VALOR_DOLAR);
				precio = precio.multiply(new BigDecimal(valorMoneda));
			}
		}
		precio = MathUtil.redondearEn2BD(precio);
		
		return precio;
	}

	public String saveTransac_OP(List<Items> itemsSessionList, Integer genteNr,
			Integer empresaNrSk, String fechaEntrega, Transac transacRequest, int tipoComprobNr, Usuario usuario) throws Exception {
		Transac transac = new Transac();
		//
		// Obtengo el numero de transacci�n
		Integer transacNr = getUltimaNumeracion(Constants.ID_NUMERACIONES_TRANSAC);
		transacNr = Integer.parseInt(String.valueOf(transacNr) + empresaNrSk);
		
		transac = buildTransac(itemsSessionList, transacNr, genteNr, tipoComprobNr, usuario);
		
		//Casteo la fecha entrega
		if (fechaEntrega != null && !fechaEntrega.trim().equals("")){
			transac.setFechaEntrega(DateUtil.composeCanonicalFechPresentation(fechaEntrega));
		}
		//Seteo las Observaciones
		transac.setObservaciones(transacRequest.getObservaciones());
		transac.setCondiciones(transacRequest.getCondiciones());
		String result = "error";
		try {
			String oficialCuentaIdOpe= null;
			//Env�o el Mail al Oficial de Cuenta
			UsuarioWeb activUser = usuarioManager.getUsuarioByPK(usuario.getIdUsuario());
			String subject = "";
			String msg = "";					
			Email email = null;
			switch (usuario.getRol()) {
			case Constants.ID_USR_GERENTE:			
				subject = parametrizacionDAO.getByPrimaryKey(Constants.ID_PARAM_SUBJECT_NEW_TRANSAC).getValor().replace("@Vendedor", activUser.getDescripC()).replace("@tipoUsuario", "Operador");
				msg = parametrizacionDAO.getByPrimaryKey(Constants.ID_PARAM_MSG_NEW_TRANSAC).getValor().replace("@tipoComprob", transac.getTipoComprob().getDescripcion()).replace("@transacNr", String.valueOf(transac.getTransacNr()));					
				email = new Email(subject,msg,null,activUser.getEmail());
				oficialCuentaIdOpe = String.valueOf(activUser.getOperadorNr());
				usuarioManager.sendMail(email);													
				break;
			case Constants.ID_USR_OPERADOR:				
				subject = parametrizacionDAO.getByPrimaryKey(Constants.ID_PARAM_SUBJECT_NEW_TRANSAC).getValor().replace("@Vendedor", activUser.getDescripC()).replace("@tipoUsuario", "Operador");
				msg = parametrizacionDAO.getByPrimaryKey(Constants.ID_PARAM_MSG_NEW_TRANSAC).getValor().replace("@tipoComprob", transac.getTipoComprob().getDescripcion()).replace("@transacNr", String.valueOf(transac.getTransacNr()));					
				email = new Email(subject,msg,null,activUser.getEmail());
				usuarioManager.sendMail(email);									
				oficialCuentaIdOpe = String.valueOf(activUser.getOperadorNr());
				break;
			case Constants.ID_USR_VENDEDOR:						
				if (activUser != null){
					if (activUser.getRol().getIdRol() == Constants.ID_USR_VENDEDOR){
						UsuarioWeb oficialCuenta = usuarioManager.getUsuarioByPK(activUser.getIdOficialCuenta());
						if (oficialCuenta != null){					
							subject = parametrizacionDAO.getByPrimaryKey(Constants.ID_PARAM_SUBJECT_NEW_TRANSAC).getValor().replace("@Vendedor", activUser.getDescripC()).replace("@tipoUsuario", "Vendedor");
							msg = parametrizacionDAO.getByPrimaryKey(Constants.ID_PARAM_MSG_NEW_TRANSAC).getValor().replace("@tipoComprob", transac.getTipoComprob().getDescripcion()).replace("@transacNr", String.valueOf(transac.getTransacNr()));					
							email = new Email(subject,msg,null,oficialCuenta.getEmail());
							usuarioManager.sendMail(email);					
							oficialCuentaIdOpe = String.valueOf(oficialCuenta.getOperadorNr());
						}
					}
				}				
				break;		
			case Constants.ID_USR_CLIENTE:						
				if (activUser != null){
					String nombreCliente = extendedGenteDAO.getByPrimaryKey(activUser.getGenteNr()).getDescripC(); 
					// Buscar su vendedor 					
					UsuarioWeb vendedor = usuarioManager.getUsuarioByVendedor(activUser.getGenteNr());
					if (vendedor != null){
					//Envio Mail al vendedor
					subject = parametrizacionDAO.getByPrimaryKey(Constants.ID_PARAM_SUBJECT_NEW_TRANSAC).getValor().replace("@Vendedor", nombreCliente).replace("@tipoUsuario", "Cliente");
					msg = parametrizacionDAO.getByPrimaryKey(Constants.ID_PARAM_MSG_NEW_TRANSAC).getValor().replace("@tipoComprob", transac.getTipoComprob().getDescripcion()).replace("@transacNr", String.valueOf(transac.getTransacNr()));					
					email = new Email(subject,msg,null,vendedor.getEmail());
					usuarioManager.sendMail(email);													
					//Encuentro al Oficial de cuentas
						UsuarioWeb oficialCuenta = usuarioManager.getUsuarioByPK(vendedor.getIdOficialCuenta());
						if (oficialCuenta != null){					
							subject = parametrizacionDAO.getByPrimaryKey(Constants.ID_PARAM_SUBJECT_NEW_TRANSAC).getValor().replace("@Vendedor", nombreCliente).replace("@tipoUsuario", "Cliente");
							msg = parametrizacionDAO.getByPrimaryKey(Constants.ID_PARAM_MSG_NEW_TRANSAC).getValor().replace("@tipoComprob", transac.getTipoComprob().getDescripcion()).replace("@transacNr", String.valueOf(transac.getTransacNr()));					
							email = new Email(subject,msg,null,oficialCuenta.getEmail());
							usuarioManager.sendMail(email);					
							oficialCuentaIdOpe = String.valueOf(oficialCuenta.getOperadorNr());
						}					
					}
				}				
				break;		
			}
			transac.setProce(oficialCuentaIdOpe);
			transac.setNrDomicilioEnt(transacRequest.getNrDomicilioEnt());
			transac.setOrdenCompra(transacRequest.getOrdenCompra());
			extendedTransacDAO.save(transac);
			result = String.valueOf(transacNr);
			

			
			
			
			usuario.getIdUsuario();
		} catch (Exception e) {
			e.printStackTrace();
			result = "error";
		}
		return result;
	}

	public Transac buildTransac(List<Items> itemsSessionList,
			Integer transacNr, Integer genteNr, int tipoComprobNr, Usuario usuario) {
		Transac transac = calculoTotales(itemsSessionList);
		transac.setTransacNr(transacNr);
		// Obtengo el agendado
		Gente gente = extendedGenteDAO.getByPrimaryKey(genteNr);
		transac.setGente(gente);
		transac.setFecha(DateUtil.composeCanonicalFechPresentationDate(DateUtil.getFormatedSTDDate(new Timestamp(System.currentTimeMillis()))));
		transac.setFechaTransac(DateUtil.composeCanonicalFechPresentationDate(DateUtil.getFormatedSTDDate(new Timestamp(System.currentTimeMillis()))));
		// Obtengo el tipo de comprobante
		TipoComprob tipoComprob = tipoComprobDAO.getByPrimaryKey(tipoComprobNr);
		transac.setTipoComprob(tipoComprob);
		transac.setLetra("X");
		transac.setTranFactCred(0);
		transac.setObservaciones(".");
		// Agrego valores a los Items
		for (Items item : itemsSessionList) {
			item.setTipoComprob(tipoComprob);
			//item.setFecha(new Date(System.currentTimeMillis()));
			item.setGenteNr(gente.getGenteNr());
			item.setFechaTransac(new Date(System.currentTimeMillis()));
			item.getId().setTransac(transac);
		}
		//Cargo Prfijo FIJO en 0000
		transac.setPrefijo("0000");
		transac.setVendedor(gente.getVendedorNr());
		transac.setCuotas(gente.getComisVta().shortValue());
		transac.setCobrador(gente.getCobradorNr());
		if (transac.getCondiciones() == null || transac.getCondiciones() == 0 ){
			transac.setCondiciones(gente.getCondVta());
		}
		transac.setTipoIva(gente.getImpuestos().getNr());
		transac.setAlicuotaIva(gente.getImpuestos().getAlicuota());
		transac.setItemsList(itemsSessionList);
		transac.setSaldo(transac.getTotal());
		transac.setNrDomicilioEnt(extendedDomiciliosDAO.getDomicilioPrincipal(gente.getGenteNr()).getId().getDomiNr());
		Integer idNumeracionComprom = 0; 
			switch (tipoComprobNr) {
			case 8:
				idNumeracionComprom = getUltimaNumeracion(Constants.ID_NUMERACIONES_PEDIDO_VTA);
				break;
			case 15:
				idNumeracionComprom = getUltimaNumeracion(Constants.ID_NUMERACIONES_PEDIDO_COMPRA);
				break;
			case 16:
				idNumeracionComprom = getUltimaNumeracion(Constants.ID_NUMERACIONES_PROFORMA);
				break;
			}			
		transac.setNrComprob(FormatUtil.llenoConCeros(String.valueOf(idNumeracionComprom), 8));
		if (usuario.getOperadorNr() != null){
			transac.setNrOperador(usuario.getOperadorNr());
		}else{
			transac.setNrOperador(99);
		}		
		transac.setDestino(-5);
		return transac;
	}

	public Integer getUltimaNumeracion(Integer tipoNumeracion) {
		Numeraciones ultimaNumeracion = numeracionesDAO
				.getByPrimaryKey(tipoNumeracion);
		Integer valor = ultimaNumeracion.getLetraA() + 1;
		ultimaNumeracion.setLetraA(valor);
		numeracionesDAO.update(ultimaNumeracion);
		return valor;
	}

	public String getArticuloPorClave(String clave) {
		Stock stock = extendedStockDAO.getByPrimaryKey(clave);
		if (stock != null) {
			return stock.getDescripcion();
		} else {
			return "";
		}

	}

	public BigDecimal getBonifPorCadena(String bonif1, String bonif2,
			String bonif3, String bonif4) {
		BigDecimal[] cadena = new BigDecimal[4];
		BigDecimal cero = new BigDecimal(0);
		BigDecimal result = new BigDecimal(0);
		if (bonif1 == null || bonif1.trim().equals("")) {
			bonif1 = "0";
		}
		try {
			cadena[0] = new BigDecimal(bonif1);
		} catch (NumberFormatException ne) {
			cadena[0] = cero;
		}

		if (bonif2 == null || bonif2.trim().equals("")) {
			bonif2 = "0";
		}
		try {
			cadena[1] = new BigDecimal(bonif2);
		} catch (NumberFormatException ne) {
			cadena[1] = cero;
		}

		if (bonif3 == null || bonif3.trim().equals("")) {
			bonif3 = "0";
		}
		try {
			cadena[2] = new BigDecimal(bonif3);
		} catch (NumberFormatException ne) {
			cadena[2] = cero;
		}

		if (bonif4 == null || bonif4.trim().equals("")) {
			bonif4 = "0";
		}
		try {
			cadena[3] = new BigDecimal(bonif4);
		} catch (NumberFormatException ne) {
			cadena[3] = cero;
		}
		result = MathUtil.getPorcentajeEnCadena(cadena);
		return result;
	}

	public String getFormatCadena(String bonif1, String bonif2, String bonif3,
			String bonif4) {

		String result = FormatUtil.llenoDosCeros(bonif1) + "+"
				+ FormatUtil.llenoDosCeros(bonif2) + "+"
				+ FormatUtil.llenoDosCeros(bonif3) + "+"
				+ FormatUtil.llenoDosCeros(bonif4);
		return result;
	}

	
	public List<Items> editItem(List<Items> itemsSessionList, Items items, Usuario usuario) {
		if (usuario.getRol() == Constants.ID_USR_CLIENTE){
			items.setPrecio(getPrecioClienteArticulo(usuario.getGenteSession().getGenteNr(), items.getClave(), usuario, true));
			items.setBonif(new BigDecimal(usuario.getGenteSession().getBonifStd()));
		}
		
		items.setArticulo(items.getClave().toUpperCase());				
		BigDecimal bonif = MathUtil.redondearEn4BD(items.getBonif().divide(
				new BigDecimal(100)));
		BigDecimal bruto = (items.getPrecio().multiply(items.getCant1()));
		BigDecimal totalItem = bruto.subtract(bruto.multiply(bonif));
		items.setNrCompInt(String.valueOf(MathUtil.redondearEn2BD(totalItem)));
		totalItem = MathUtil.redondearEn2BD(totalItem);
		items.setTotalItem(totalItem);
		// Calculo el impueto del Item
		items.setTalle(".");
		try{
		items.setExi(PreferenciasUtil.comparePreferencia(usuario.getListPreferencias(), Constants.PREF_ID_EXISTENCIA_DEFAULT).intValue());
		}catch(NumberFormatException ne){
			ne.printStackTrace();
		}
		//items.setColo(0);
		items.setFechaTransac(DateUtil.composeCanonicalFechPresentationDate(DateUtil.getFormatedSTDDate(new Timestamp(System.currentTimeMillis()))));
		items.setFecha(DateUtil.getFormatSkDate(new Timestamp(System.currentTimeMillis())));
		//items.setFecha(DateUtil.composeCanonicalFechPresentationDate(DateUtil.getFormatedSTDDate(new Timestamp(System.currentTimeMillis()))));
		items.setNrFabInt(".");
		items.setLetra("X");
		items = generateImpuesto(items);
		List<Items> listItemsNueva = new ArrayList<Items>();
		for(Items itemsU:itemsSessionList){
			if(itemsU.getId().getItemNr() == items.getId().getItemNr()){
				listItemsNueva.add(items);
			}else{
				listItemsNueva.add(itemsU);
			}
		}
		return listItemsNueva;
	}

	
	public List<TipoComprob> getTipoComprobEnable(Integer rol) {
	List<TipoComprob> tipoComprobList = new ArrayList<TipoComprob>();
	switch (rol) {
	case Constants.ID_USR_GERENTE:
		tipoComprobList.add(tipoComprobDAO.getByPrimaryKey(8));
		tipoComprobList.add(tipoComprobDAO.getByPrimaryKey(15));
		tipoComprobList.add(tipoComprobDAO.getByPrimaryKey(16));	
		break;

	case Constants.ID_USR_VENDEDOR:
		tipoComprobList.add(tipoComprobDAO.getByPrimaryKey(8));
		tipoComprobList.add(tipoComprobDAO.getByPrimaryKey(16));
		break;
		
	case Constants.ID_USR_CLIENTE:
		tipoComprobList.add(tipoComprobDAO.getByPrimaryKey(8));
		//tipoComprobList.add(tipoComprobDAO.getByPrimaryKey(16));
		break;
	default:
		break;
	}
		return tipoComprobList;
	}


	public TipoComprob getTipoComprobByPK(int nr) {
		TipoComprob tipoComprob = new TipoComprob();
		tipoComprob = tipoComprobDAO.getByPrimaryKey(nr);
		
		return tipoComprob; 
	}


	public List<TipoComprob> getTipoComprobAll() {
		List<TipoComprob> tipoComprobList = tipoComprobDAO.getAll();
		return tipoComprobList;
	}


	public List<TipoReporte> getTipoReporteAll() {
		TipoReporte tipoReporte1 = new TipoReporte(1, "Solo Impagos Ventas");
		TipoReporte tipoReporte2 = new TipoReporte(2, "Resumen Cuenta Corriente Ventas");
		TipoReporte tipoReporte3 = new TipoReporte(3, "Solo Impagos Compras");
		TipoReporte tipoReporte4 = new TipoReporte(4, "Resumen Cuenta Corriente Compras");
		TipoReporte tipoReporte5 = new TipoReporte(7, "Pedidos Pendientes");
		List<TipoReporte> tipoReporteList = new ArrayList<TipoReporte>();
		tipoReporteList.add(tipoReporte1);
		tipoReporteList.add(tipoReporte2);
		tipoReporteList.add(tipoReporte3);
		tipoReporteList.add(tipoReporte4);
		tipoReporteList.add(tipoReporte5);
		return tipoReporteList;
	}

	
	public Gente getGenteConDomiPrinc(Integer genteNr) {
		Gente gente = extendedGenteDAO.getByPrimaryKey(genteNr);
		gente.setDomicilioPricipal(extendedDomiciliosDAO.getDomicilioPrincipal(genteNr));
		return gente;
	}

	
	public List<TipoReporte> getTipoReporteTesoreria() {
		TipoReporte tipoReporte1 = new TipoReporte(5, "Cheques Rechazados");
		List<TipoReporte> tipoReporteList = new ArrayList<TipoReporte>();
		tipoReporteList.add(tipoReporte1);
		return tipoReporteList;
	}

	
	public List<TipoReporte> getTipoReporteEstadistica() {
		TipoReporte tipoReporte1 = new TipoReporte(8, "Por Articulo Agendado");
		List<TipoReporte> tipoReporteList = new ArrayList<TipoReporte>();
		tipoReporteList.add(tipoReporte1);
		return tipoReporteList;
	}
	
	public String getArticuloPorClave(String clave, boolean articuloActivo) {		
		return extendedStockDAO.getArticuloPorPK(clave, articuloActivo);
	}

	
	public String getTransacJoin(int nr, String clave, Usuario usuarioSesion) {
		List<TransacJoin> transacJoinList = extendedTransacDAO.getTransacJoin(nr,clave);
		String tipoComprob = tipoComprobDAO.getByPrimaryKey(nr).getDescripcion();
		String result = FormatUtil.getTableHTMLPedido(transacJoinList,tipoComprob);		
		return result;
	}

	public Transac getTransacByPK(Integer transacNr) {
		return extendedTransacDAO.getByPrimaryKey(transacNr);		
	}
	
	public Domicilios getDomiciliosAdm(Gente gente) {
		return extendedDomiciliosDAO.getDomicilioAdm(gente.getGenteNr());
	}
	
	public List<Transac> getRemitosPendientesPorClienteTraza(
			Usuario usuarioSesion) {
		Gente gente = extendedGenteDAO.getByPrimaryKey(usuarioSesion.getGenteNr());
		List<Transac> transacList = extendedTransacDAO.getRemitosPendientesPorClienteTraza(gente);
		return transacList;
	}
 

	public List<Trazabi> getTrazabiPorComprobante(Integer transacNr) {
		Transac transac = extendedTransacDAO.getByPrimaryKey(transacNr);
		String comprob = transac.getPrefijo() + "-" + transac.getNrComprob();
		List<Trazabi> trazabiList = extendedTrazabiDAO.getTrazabiPorComprobante(comprob);
		return trazabiList;
	}

	
	public List<Transac> getPedidosPendientes(Gente gente, Usuario usuario) {
		List<Transac> transacList = extendedTransacDAO.getPedidosPendientes(gente);
		String rutaComprobantePdf = "";
		try{
			rutaComprobantePdf = dondeEstaFactuMailDAO.getByPrimaryKey(usuario.getEmpresaNrSk()).getDonde();	
		}catch (Exception e){
			e.printStackTrace();
		}
		if (transacList != null){
			for(Transac transac : transacList){
				//Obtengo el formato de fecha (mm/yyyy)
				String fechaStr = DateUtil.getFormatedShortDate(transac.getFecha());
				String rutaComprobantePdfTemp = rutaComprobantePdf + fechaStr + "\\" + FormatUtil.llenoConCeros(transac.getTransacNr().toString(), 8) + ".pdf";
				System.out.println(rutaComprobantePdfTemp);
				if (new File(rutaComprobantePdfTemp).exists()){
					transac.setRutaComprobantePdf(rutaComprobantePdfTemp);
				}
			}
		}
		return transacList;
	}

	public List<Items> getEstadisticaArtGente(Integer genteNr,
			Usuario usuarioSesion, String fechaDesde, String fechaHasta) {
		
		fechaDesde = DateUtil.composeCanonicalAAAAString(fechaDesde);
		fechaHasta = DateUtil.composeCanonicalAAAAString(fechaHasta);
		
		int idTipoComprob = Constants.ID_TIPO_COMP_PEDIDO_VENTA;
		
		List<Items> itemsList = extendedTransacDAO.getEstadisticaArtGente(genteNr, fechaDesde, fechaHasta, idTipoComprob);
		
		return itemsList;
	}


	public List<Domicilios> getDomiciliosPorGente(Gente gente) {
		List<Domicilios> domiciliosList = extendedDomiciliosDAO.getDomiciliosPorGente(gente);		
		return domiciliosList;
	}

	public List<Condi> getCondiAll() {		
		return extendedCondiDAO.getCondiAll();
	}


	public List<EstadisticaPedido> getEstadisticaPedido() {
		
		
		int idTipoComprob = Constants.ID_TIPO_COMP_PEDIDO_VENTA;
		
		//List<EstadisticaPedido> estadisticaPedidoList = extendedTransacDAO.getEstadisticaPedido();
		
		//return estadisticaPedidoList;
	
		return null;
	}

	public List<EstadiTipoReporte> getEstadiTipoReporte() {
		
		List<EstadiTipoReporte> estadiTipoReporteList = new ArrayList<EstadiTipoReporte>();
		
		List<EstadiTipoReporte> estadiTipoReporteListF = new ArrayList<EstadiTipoReporte>();
		
		EstadiTipoReporte e1 = new EstadiTipoReporte();
		e1.setDescrip("[0] Seleccione una opcion");
		e1.setNr(0);
		
		estadiTipoReporteListF.add(e1);
		
		estadiTipoReporteList = estadiTipoReporteDAO.getAll();
		
		estadiTipoReporteListF.addAll(estadiTipoReporteList);
		
		
		return estadiTipoReporteListF;
	}

	public byte[] generateEstadiReport(DatosReporte datosReporte,
			Usuario usuarioSesion) {
		
			
		return null;
	}

	
	public List<Items> changeValorMasivo(List<Items> itemsSessionList,
			Items items, Integer tipoValorMasivo, String itemsSelecionados,
			Usuario usuarioSesion) {
		//Pregunto que tipo de cambio de valor masivo es
		String [] idItemsArray = itemsSelecionados.split(",");
		List<Items> itemsListFinal = new ArrayList<Items>();
		List<Items> itemsList = new ArrayList<Items>();
		//Tengo que cambiar la casdena a todos los items seleccionados
		//Recorro la cadena
		for(Items itemsR : itemsSessionList){
			for(String valor : idItemsArray){
				try{
					Integer id = Integer.parseInt(valor);
					if (id == itemsR.getId().getItemNr()){
						switch (tipoValorMasivo) {
						case 1:
							itemsR.setObser(getFormatCadena(items.getBonif1(),items.getBonif2(),items.getBonif3(),items.getBonif4()));
							itemsR.setBonif(getBonifPorCadena(items.getBonif1(),items.getBonif2(),items.getBonif3(),items.getBonif4()));
							break;
						case 2:
							itemsR.setObser(getFormatCadena("00","00","00","00"));
							itemsR.setBonif(items.getBonif());
						case 3:
							switch (items.getTipoVariacionPrecio()) {
							case 1:
								try{
									itemsR.setPrecio(itemsR.getPrecio().add(items.getVariacionPrecio()));
								}catch(NumberFormatException ne){
									ne.printStackTrace();
								}
							break;
							case 2:
								try{
									if (itemsR.getPrecio().compareTo(items.getVariacionPrecio()) == 1){
										itemsR.setPrecio(itemsR.getPrecio().subtract(items.getVariacionPrecio()));	
									}									
								}catch(NumberFormatException ne){
									ne.printStackTrace();
								}

							break;
							case 3:
								try{
									if (items.getVariacionPrecio().compareTo(BigDecimal.ZERO) > 0){
										//Obtengo porcentaje
										BigDecimal por = itemsR.getPrecio().multiply(items.getVariacionPrecio()).divide(new BigDecimal(100));
										itemsR.setPrecio(itemsR.getPrecio().add(por));	
									}									
								}catch(NumberFormatException ne){
									ne.printStackTrace();
								}

							break;
							case 4:
								try{
									if (items.getVariacionPrecio().compareTo(BigDecimal.ZERO) > 0 && items.getVariacionPrecio().compareTo(new BigDecimal(100)) < 0){
										//Obtengo porcentaje
										BigDecimal por = itemsR.getPrecio().multiply(items.getVariacionPrecio()).divide(new BigDecimal(100));
										itemsR.setPrecio(itemsR.getPrecio().subtract(por));	
									}									
								}catch(NumberFormatException ne){
									ne.printStackTrace();
								}

							break;
							case 5:
								try{
									if (items.getVariacionPrecio().compareTo(BigDecimal.ZERO) > 0){
										//Obtengo porcentaje
										itemsR.setPrecio(items.getVariacionPrecio());	
									}									
								}catch(NumberFormatException ne){
									ne.printStackTrace();
								}

							break;

							
							}
						break;
						case 4:
							//Tomo la cadena que tiene el item lo llevo a un String [] para analizarlo
							String cadena = itemsR.getObser();
							try{
							String[] bonifs = cadena.split("\\+");
							int i = 0;
							boolean termine = false;
							for(String bonif: bonifs){
								if (bonif.equals("00")){
									if (!termine){
										if (items.getBonif().doubleValue() < 100){
											bonif = items.getBonif().toString();
											bonifs[i] = items.getBonif().toString();
										}
										termine = true;
									}
								}
								i++;
							}
							itemsR.setObser(getFormatCadena(bonifs[0],bonifs[1],bonifs[2],bonifs[3]));
							itemsR.setBonif(getBonifPorCadena(bonifs[0],bonifs[1],bonifs[2],bonifs[3]));
							}catch(Exception e){
								e.printStackTrace();
							}
						}							
					}
				}catch(NumberFormatException ne){
					
				}
				
			}
			itemsListFinal = addItem(itemsList, itemsR, usuarioSesion, false);
		}
			

		return itemsListFinal;
	}

	
	public List<Transac> getPedidosVtaPendienteAprobar(Gente gente,
			Usuario usuario) {
		List<Transac> transacList = extendedTransacDAO.getPedidosVtaPendienteAprobar(gente);
		
		
		return transacList;		
	}

}
