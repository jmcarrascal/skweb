package jmc.skweb.core.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

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
import jmc.skweb.core.model.EmpresaWeb;
import jmc.skweb.core.model.EstadiOrigen;
import jmc.skweb.core.model.EstadiReporte;
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
import jmc.skweb.core.service.ReportManager;
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
public class ReportManagerImpl implements ReportManager {

	private GenericDAO<Parametrizacion> parametrizacionDAO;
	private TransaccionManager transaccionManager;
	private TransacDAO extendedTransacDAO;
	private GenericDAO<EmpresaWeb> empresaWebDAO;
	private GenericDAO<EstadiReporte> estadiReporteDAO;
	private GenericDAO<EstadiOrigen> estadiOrigenDAO;;
	

	
	
	
	
	
	public GenericDAO<EstadiReporte> getEstadiReporteDAO() {
		return estadiReporteDAO;
	}

	public void setEstadiReporteDAO(GenericDAO<EstadiReporte> estadiReporteDAO) {
		this.estadiReporteDAO = estadiReporteDAO;
	}

	public GenericDAO<EstadiOrigen> getEstadiOrigenDAO() {
		return estadiOrigenDAO;
	}

	public void setEstadiOrigenDAO(GenericDAO<EstadiOrigen> estadiOrigenDAO) {
		this.estadiOrigenDAO = estadiOrigenDAO;
	}

	public GenericDAO<EmpresaWeb> getEmpresaWebDAO() {
		return empresaWebDAO;
	}

	public void setEmpresaWebDAO(GenericDAO<EmpresaWeb> empresaWebDAO) {
		this.empresaWebDAO = empresaWebDAO;
	}

	public TransacDAO getExtendedTransacDAO() {
		return extendedTransacDAO;
	}

	public void setExtendedTransacDAO(TransacDAO extendedTransacDAO) {
		this.extendedTransacDAO = extendedTransacDAO;
	}

	public GenericDAO<Parametrizacion> getParametrizacionDAO() {
		return parametrizacionDAO;
	}

	public void setParametrizacionDAO(GenericDAO<Parametrizacion> parametrizacionDAO) {
		this.parametrizacionDAO = parametrizacionDAO;
	}

	public TransaccionManager getTransaccionManager() {
		return transaccionManager;
	}

	public void setTransaccionManager(TransaccionManager transaccionManager) {
		this.transaccionManager = transaccionManager;
	}

	
	
	
			
		private static final String REPORT_PATH = "C:\\EstadisticaPedido.jrxml";
		private static final String REPORT_EXPORT_PATH = "C:\\EstadisticaPedido.pdf";
		
		/**
		 * Genera el pdf para el informe
		 * @param aux = Lista de objetos que mostraremos en el informe
		 * @param map = Resto de parametros que viajan al informe
		 * @throws JRException
		 */
		
		private static byte[] exportPDF (List aux, HashMap map, String rutaReporte) throws JRException
		{
				
				byte[] pdfTemp = null;
				// Definimos cual sera nuestra fuente de datos
				JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(aux);
				// Recuperamos el fichero fuente 
				
				JasperDesign jd=JRXmlLoader.load(rutaReporte);
				//JasperDesign jd=JRXmlLoader.load(rutaReporte);
				// Compilamos el informe jrxml
				JasperReport report = JasperCompileManager.compileReport(jd);
				// Rellenamos el informe con la conexion creada y sus parametros establecidos
				//JasperPrint print = JasperFillManager.fillReport(report,map, ds);
				
				// Exportamos el informe a formato PDF
				//JasperExportManager.exportReportToPdfFile(print, REPORT_EXPORT_PATH);
				
				pdfTemp = JasperRunManager.runReportToPdf(report,map, ds);
				
				return pdfTemp;
		}
		
		/**
		 * Genera el pdf para el informe
		 * @param aux = Lista de objetos que mostraremos en el informe
		 * @param map = Resto de parametros que viajan al informe
		 * @throws JRException
		 */
		
		private static byte[] exportXLS (List aux, HashMap map, String rutaReporte) throws Exception
		{
				// Definimos cual sera nuestra fuente de datos
				JRBeanCollectionDataSource ds =new JRBeanCollectionDataSource(aux);
				// Recuperamos el fichero fuente 
				JasperDesign jd=JRXmlLoader.load(rutaReporte);
				// Compilamos el informe jrxml
				JasperReport report = JasperCompileManager.compileReport(jd);
				// Rellenamos el informe con la conexion creada y sus parametros establecidos
				JasperPrint print = JasperFillManager.fillReport(report,map, ds);
				
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				 
				JRXlsExporter exporterXLS = new JRXlsExporter(); 
				
				exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, print); 
				
				exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, byteArrayOutputStream); 
				
				exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE); 
				
				//exporterXLS.setParameter(JRXlsExporterParameter.IS_AUTO_DETECT_CELL_TYPE, Boolean.TRUE); 
				
				exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE); 
				
				exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE); 
				
				exporterXLS.exportReport(); 
				
				return byteArrayOutputStream.toByteArray();
			
		}


		public byte[] generateEstadiReport(DatosReporte datosReporte,
				Usuario usuarioSesion) {
			byte[] result = null;
			
			if(usuarioSesion.getRol() == Constants.ID_USR_VENDEDOR && !datosReporte.getTodosClientes().equals("true")){
				datosReporte.setAgendadoHasta(datosReporte.getAgendadoDesde());
			} 
			
			List<EstadisticaPedido> estadisticaPedidoList = new ArrayList<EstadisticaPedido>();
			
			if (datosReporte.getReporte() == Constants.ID_REPORT_PEDIDO_VTA_AGENDADOS_ART){
				estadisticaPedidoList = extendedTransacDAO.getEstadisticaPedido(datosReporte);
			}
			if (datosReporte.getReporte() == Constants.ID_REPORT_VTA_AGENDADOS_ART){
				estadisticaPedidoList = extendedTransacDAO.getEstadisticaVentas(datosReporte);
			}
			String rutaReporte = estadiReporteDAO.getByPrimaryKey(datosReporte.getReporte()).getRutaReporteJrxml();
			//Map con los posibles otros parametros del informe  
	        HashMap map=buildParametersHeaderReport(datosReporte, usuarioSesion);
	        try {
				if (datosReporte.getFormatoReporte().equals("P")){
					result = exportPDF(estadisticaPedidoList,map,rutaReporte);	
				}
	        	if(datosReporte.getFormatoReporte().equals("X")){
	        		result = exportXLS(estadisticaPedidoList,map,rutaReporte);
	        	}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return result;
		}
		
		private HashMap buildParametersHeaderReport(DatosReporte datosReporte,
				Usuario usuarioSesion){			
	        HashMap map=new HashMap(); 
	        EmpresaWeb empresa = empresaWebDAO.getByPrimaryKey(usuarioSesion.getIdEmpresa());
	        map.put(JRParameter.REPORT_LOCALE, Locale.US);
	        map.put("empresa", empresa.getNombre());
	        map.put("fechaDesde", datosReporte.getFechaDesde());
	        map.put("fechaHasta",  datosReporte.getFechaHasta());
	        map.put("reporte", estadiReporteDAO.getByPrimaryKey(datosReporte.getReporte()).getDescrip());
	        map.put("origen", estadiOrigenDAO.getByPrimaryKey(datosReporte.getOrigen()).getDescrip());
	        
			return map;
		}

       
		
		
	
	
}
