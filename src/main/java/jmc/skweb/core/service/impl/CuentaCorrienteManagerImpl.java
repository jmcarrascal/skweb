package jmc.skweb.core.service.impl;




import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import jmc.skweb.core.dao.ExiArtDAO;
import jmc.skweb.core.dao.FamDAO;
import jmc.skweb.core.dao.GenericDAO;
import jmc.skweb.core.dao.GenteDAO;
import jmc.skweb.core.dao.StockDAO;
import jmc.skweb.core.dao.SubFamDAO;
import jmc.skweb.core.dao.TransacDAO;
import jmc.skweb.core.model.DondeEstaFactuMail;
import jmc.skweb.core.model.ExiArt;
import jmc.skweb.core.model.Existencias;
import jmc.skweb.core.model.Fam;
import jmc.skweb.core.model.Gente;
import jmc.skweb.core.model.Stock;
import jmc.skweb.core.model.SubFam;
import jmc.skweb.core.model.TipoComprob;
import jmc.skweb.core.model.Transac;
import jmc.skweb.core.model.Usuario;
import jmc.skweb.core.model.report.SaldoAcumulado;
import jmc.skweb.core.service.ArticuloManager;
import jmc.skweb.core.service.CuentaCorrienteManager;
import jmc.skweb.core.service.UsuarioManager;
import jmc.skweb.util.DateUtil;
import jmc.skweb.util.FormatUtil;
import jmc.skweb.util.MathUtil;





/**
 * @author Juan Manuel Carrascal
 *
 */
/**
 * @author Administrator
 *
 */
public class CuentaCorrienteManagerImpl implements CuentaCorrienteManager{

 	  	private GenteDAO extendedGenteDAO;
 	  	private TransacDAO extendedTransacDAO; 	  	
 	  	private GenericDAO<TipoComprob> tipoComprobDAO;
 	  	private GenericDAO<DondeEstaFactuMail> dondeEstaFactuMailDAO;
 	  	
 	  	
 	  	
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
		 * Obtiene los resgistros de transac..... para Clientes
		 */
		public SaldoAcumulado getCuentaCorrienteVentasPorGente(Integer genteNr, Usuario usuario) {
			//Obtengo los registros de Transac del cliente genteNr donde
			//el tipo de comporbante de 1,2,4,5,6,7 donde 2,4,6 restan.
			SaldoAcumulado saldoAcumulado = new SaldoAcumulado();
			Integer[] listTipoComprVentas = {1,2,4,5,6,7};
			
			List<Transac> transacVentasList = extendedTransacDAO.getCuentaCorrienteVenta(listTipoComprVentas, genteNr);
			
			String rutaComprobantePdf = "";
			
			try{
				rutaComprobantePdf = dondeEstaFactuMailDAO.getByPrimaryKey(usuario.getEmpresaNrSk()).getDonde();	
			}catch (Exception e){
				e.printStackTrace();
			}
			
			if (rutaComprobantePdf != null){		
				if(transacVentasList != null){
					for(Transac transac : transacVentasList){
						//Obtengo el formato de fecha (mm/yyyy)
						String fechaStr = DateUtil.getFormatedShortDate(transac.getFecha());
						String rutaComprobantePdfTemp = rutaComprobantePdf + fechaStr + "\\" + FormatUtil.llenoConCeros(transac.getTransacNr().toString(), 8) + ".pdf";
						System.out.println(rutaComprobantePdfTemp);
						if (new File(rutaComprobantePdfTemp).exists()){
							transac.setRutaComprobantePdf(rutaComprobantePdfTemp);
						}
					}
				}
			}
			if (transacVentasList.size() > 0){			
				saldoAcumulado = calculoSaldoAcumulado(transacVentasList, null);
			}
			return saldoAcumulado;
		}
		
		private SaldoAcumulado calculoSaldoAcumulado(
				List<Transac> transacList, Double inicioSaldo) {
			SaldoAcumulado saldoAcumulado = new SaldoAcumulado();
			
			saldoAcumulado.setIncioAcumulado(inicioSaldo);
			Double saldoAnterior = inicioSaldo;
			for(Transac transac : transacList){
				if (saldoAnterior != null){
					saldoAnterior = saldoAnterior + FormatUtil.getSaldoCalculado(transac.getTipoComprob().getFactorCtaCte(),transac.getSaldo()).doubleValue();
					transac.setSaldoAcumulado(saldoAnterior);
				}else{
					if(inicioSaldo == null){
						inicioSaldo = FormatUtil.getSaldoCalculado(transac.getTipoComprob().getFactorCtaCte(),transac.getSaldo()).doubleValue();
					}
					transac.setSaldoAcumulado(inicioSaldo);
					
					saldoAnterior = FormatUtil.getSaldoCalculado(transac.getTipoComprob().getFactorCtaCte(),transac.getSaldo()).doubleValue();
				}
			}
			saldoAcumulado.setListTransac(transacList);
			if (saldoAnterior != null)
				saldoAnterior = MathUtil.redondear(saldoAnterior);
			saldoAcumulado.setFinAcumulado(saldoAnterior);
			return saldoAcumulado;
		}
		
		private SaldoAcumulado calculoTotalAcumulado(
				List<Transac> transacList, Double inicioSaldo) {
			SaldoAcumulado saldoAcumulado = new SaldoAcumulado();
			
			saldoAcumulado.setIncioAcumulado(inicioSaldo);
			Double saldoAnterior = inicioSaldo;
			for(Transac transac : transacList){
				if (saldoAnterior != null){
					saldoAnterior = saldoAnterior + FormatUtil.getSaldoCalculado(transac.getTipoComprob().getFactorCtaCte(),transac.getTotal()).doubleValue();
					transac.setSaldoAcumulado(saldoAnterior);
				}else{
					if(inicioSaldo == null){
						inicioSaldo = FormatUtil.getSaldoCalculado(transac.getTipoComprob().getFactorCtaCte(),transac.getTotal()).doubleValue();
					}
					transac.setSaldoAcumulado(inicioSaldo);
					
					saldoAnterior = FormatUtil.getSaldoCalculado(transac.getTipoComprob().getFactorCtaCte(),transac.getTotal()).doubleValue();
				}
			}
			saldoAcumulado.setListTransac(transacList);
			if (saldoAnterior != null)
				saldoAnterior = MathUtil.redondear(saldoAnterior);
			saldoAcumulado.setFinAcumulado(saldoAnterior);
			return saldoAcumulado;
		}
		
		public Double getTotalSaldoVentasPorGente(List<Transac> transacVentasList) {
			Double totalSaldo = 0d;
			
			for(Transac transac: transacVentasList){
				totalSaldo = totalSaldo + transac.getSaldoCalculado();
			}
			
			return MathUtil.redondear(totalSaldo);
		}
		
		
		
		public File getComprobantePdf(Integer transacNr, Usuario usuario) {
			
			Transac transac = extendedTransacDAO.getByPrimaryKey(transacNr);
			File file = null;
			String rutaComprobantePdf = "";			
			try{
				rutaComprobantePdf = dondeEstaFactuMailDAO.getByPrimaryKey(usuario.getEmpresaNrSk()).getDonde();	
				String fechaStr = DateUtil.getFormatedShortDate(transac.getFecha());
				String rutaComprobantePdfTemp = rutaComprobantePdf + fechaStr + "\\" + FormatUtil.llenoConCeros(transac.getTransacNr().toString(), 8) + ".pdf";
				file = new File(rutaComprobantePdfTemp);
			}catch (Exception e){
				e.printStackTrace();
			}					
			return file;
		}
		
		
		public SaldoAcumulado getResumenCuentaCorriente(Integer genteNr,
				Usuario usuario, String fechaDesde, String fechaHasta) {
			
			fechaDesde = DateUtil.composeCanonicalAAAAString(fechaDesde);
			fechaHasta = DateUtil.composeCanonicalAAAAString(fechaHasta);
			//Obtengo los registros de Transac del cliente genteNr donde
			//el tipo de comporbante de 1,2,4,5,6,7 donde 2,4,6 restan.
			SaldoAcumulado saldoAcumulado = new SaldoAcumulado();
			
			Integer[] listTipoComprVentas = {1,2,4,5,6,7};
			
			List<Transac> transacVentasList = extendedTransacDAO.getResumenCuentaCorriente(listTipoComprVentas, genteNr, fechaDesde, fechaHasta);
			
			String rutaComprobantePdf = "";
			
			try{
				rutaComprobantePdf = dondeEstaFactuMailDAO.getByPrimaryKey(usuario.getEmpresaNrSk()).getDonde();	
			}catch (Exception e){
				e.printStackTrace();
			}
			
			if (rutaComprobantePdf != null){							
				if (transacVentasList != null){
					for(Transac transac : transacVentasList){
						//Obtengo el formato de fecha (mm/yyyy)
						String fechaStr = DateUtil.getFormatedShortDate(transac.getFecha());
						String rutaComprobantePdfTemp = rutaComprobantePdf + fechaStr + "\\" + FormatUtil.llenoConCeros(transac.getTransacNr().toString(), 8) + ".pdf";
						System.out.println(rutaComprobantePdfTemp);
						if (new File(rutaComprobantePdfTemp).exists()){
							transac.setRutaComprobantePdf(rutaComprobantePdfTemp);
						}
					}
				}
			}
			Double saldoAnterior = getTotalAnterior(genteNr, listTipoComprVentas, fechaDesde);
			if (transacVentasList.size() > 0){			
				saldoAcumulado = calculoTotalAcumulado(transacVentasList, saldoAnterior);
			}
			
			
			return saldoAcumulado;		
			}
 	  	
 	  	
		private Double getSaldoAnterior(Integer genteNr, Integer[] listTipoComprVentas,String fechaDesde ){
			//Rocorro la lista de tipo de comprobantes
			Double result = 0d;
			for(Integer tipoCompr : listTipoComprVentas){
				//Obtengo la suma de este tipo comp de la base
				Double saldo = extendedTransacDAO.getSaldoPorAgendadoTipoCompr(tipoCompr, genteNr, fechaDesde);
				if (saldo == null)
					saldo = 0d;
				TipoComprob  tipoComprob =tipoComprobDAO.getByPrimaryKey(tipoCompr);
				saldo = saldo * tipoComprob.getFactorCtaCte();
				result = result + saldo;
			}						
			return result;
		}
		  
		private Double getTotalAnterior(Integer genteNr, Integer[] listTipoComprVentas,String fechaDesde ){
			//Rocorro la lista de tipo de comprobantes
			Double result = 0d;
			for(Integer tipoCompr : listTipoComprVentas){
				//Obtengo la suma de este tipo comp de la base
				Double saldo = extendedTransacDAO.getTotalPorAgendadoTipoCompr(tipoCompr, genteNr, fechaDesde);
				if (saldo == null)
					saldo = 0d;
				TipoComprob  tipoComprob =tipoComprobDAO.getByPrimaryKey(tipoCompr);
				saldo = saldo * tipoComprob.getFactorCtaCte();
				result = result + saldo;
			}	
			try{
				result = MathUtil.redondear(result);
			}catch(Exception e){
				
			}
			return result;
		}

		public SaldoAcumulado getCuentaCorrienteComprasPorGente(
				Integer genteNr, Usuario usuario) {
			//Obtengo los registros de Transac del cliente genteNr donde
			//el tipo de comporbante de  10,11,12,13,14.
			SaldoAcumulado saldoAcumulado = new SaldoAcumulado();
			Integer[] listTipoComprCompras = {10,11,12,13,14};
			
			List<Transac> transacComprasList = extendedTransacDAO.getCuentaCorrienteVenta(listTipoComprCompras, genteNr);
			
			String rutaComprobantePdf = "";
			
			try{
				rutaComprobantePdf = dondeEstaFactuMailDAO.getByPrimaryKey(usuario.getEmpresaNrSk()).getDonde();	
			}catch (Exception e){
				e.printStackTrace();
			}
			
			if (rutaComprobantePdf != null){							
				if (transacComprasList != null){
					for(Transac transac : transacComprasList){
						//Obtengo el formato de fecha (mm/yyyy)
						String fechaStr = DateUtil.getFormatedShortDate(transac.getFecha());
						String rutaComprobantePdfTemp = rutaComprobantePdf + fechaStr + "\\" + FormatUtil.llenoConCeros(transac.getTransacNr().toString(), 8) + ".pdf";
						System.out.println(rutaComprobantePdfTemp);
						if (new File(rutaComprobantePdfTemp).exists()){
							transac.setRutaComprobantePdf(rutaComprobantePdfTemp);
						}
					}
				}
			}
			if (transacComprasList.size() > 0){			
				saldoAcumulado = calculoSaldoAcumulado(transacComprasList, null);
			}
			return saldoAcumulado;
		}

		public SaldoAcumulado getResumenCuentaCorrienteCompras(Integer genteNr,
				Usuario usuario, String fechaDesde, String fechaHasta) {
			fechaDesde = DateUtil.composeCanonicalAAAAString(fechaDesde);
			fechaHasta = DateUtil.composeCanonicalAAAAString(fechaHasta);
			//Obtengo los registros de Transac del cliente genteNr donde
			//el tipo de comporbante de  10,11,12,13,14.
			SaldoAcumulado saldoAcumulado = new SaldoAcumulado();
			
			Integer[] listTipoComprCompras = {10,11,12,13,14};
			
			List<Transac> transacComprasList = extendedTransacDAO.getResumenCuentaCorriente(listTipoComprCompras, genteNr, fechaDesde, fechaHasta);
			
			String rutaComprobantePdf = "";
			
			try{
				rutaComprobantePdf = dondeEstaFactuMailDAO.getByPrimaryKey(usuario.getEmpresaNrSk()).getDonde();	
			}catch (Exception e){
				e.printStackTrace();
			}
			
			if (rutaComprobantePdf != null){							
				if(transacComprasList != null){
					for(Transac transac : transacComprasList){
						//Obtengo el formato de fecha (mm/yyyy)
						String fechaStr = DateUtil.getFormatedShortDate(transac.getFecha());
						String rutaComprobantePdfTemp = rutaComprobantePdf + fechaStr + "\\" + FormatUtil.llenoConCeros(transac.getTransacNr().toString(), 8) + ".pdf";
						System.out.println(rutaComprobantePdfTemp);
						if (new File(rutaComprobantePdfTemp).exists()){
							transac.setRutaComprobantePdf(rutaComprobantePdfTemp);
						}
					}
				}
			}
			Double saldoAnterior = getTotalAnterior(genteNr, listTipoComprCompras, fechaDesde);
			if (transacComprasList.size() > 0){			
				saldoAcumulado = calculoTotalAcumulado(transacComprasList, saldoAnterior);
			}
			
			
			return saldoAcumulado;		
		}



		public List<Transac> getTransacPorTipoComprob(Integer genteNr,
				Usuario usuarioSesion, int nr, String fechaDesde,
				String fechaHasta) {			
			fechaDesde = DateUtil.composeCanonicalAAAAString(fechaDesde);
			fechaHasta = DateUtil.composeCanonicalAAAAString(fechaHasta);
			
			List<Transac> transacList = extendedTransacDAO.getTransacPorTipoComprob(genteNr, nr, fechaDesde, fechaHasta);
			String rutaComprobantePdf = "";
			
			try{
				rutaComprobantePdf = dondeEstaFactuMailDAO.getByPrimaryKey(usuarioSesion.getEmpresaNrSk()).getDonde();	
			}catch (Exception e){
				e.printStackTrace();  
			}
			
			if (rutaComprobantePdf != null){		
				if(transacList != null){
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
			}
			return transacList;
		}

}

