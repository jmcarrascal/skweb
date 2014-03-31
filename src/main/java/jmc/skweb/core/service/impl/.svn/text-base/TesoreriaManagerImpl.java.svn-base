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
import jmc.skweb.core.dao.ImagenesDAO;
import jmc.skweb.core.dao.PasebanDAO;
import jmc.skweb.core.dao.StockDAO;
import jmc.skweb.core.dao.SubFamDAO;
import jmc.skweb.core.dao.TransacDAO;
import jmc.skweb.core.model.DondeEstaFactuMail;
import jmc.skweb.core.model.ExiArt;
import jmc.skweb.core.model.Existencias;
import jmc.skweb.core.model.Fam;
import jmc.skweb.core.model.Gente;
import jmc.skweb.core.model.Imagenes;
import jmc.skweb.core.model.Paseban;
import jmc.skweb.core.model.Stock;
import jmc.skweb.core.model.SubFam;
import jmc.skweb.core.model.TipoComprob;
import jmc.skweb.core.model.Transac;
import jmc.skweb.core.model.Usuario;
import jmc.skweb.core.model.report.SaldoAcumulado;
import jmc.skweb.core.service.ArticuloManager;
import jmc.skweb.core.service.CuentaCorrienteManager;
import jmc.skweb.core.service.TesoreriaManager;
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
public class TesoreriaManagerImpl implements TesoreriaManager{

 	  	private GenteDAO extendedGenteDAO;
 	  	private TransacDAO extendedTransacDAO; 	  	
 	  	private GenericDAO<TipoComprob> tipoComprobDAO;
 	  	private PasebanDAO extendedPasebanDAO;
 	  	private ImagenesDAO extendedImagenesDAO;
 	  	
 	  	
 	  	
 	  	
 	  	public ImagenesDAO getExtendedImagenesDAO() {
			return extendedImagenesDAO;
		}
		public void setExtendedImagenesDAO(ImagenesDAO extendedImagenesDAO) {
			this.extendedImagenesDAO = extendedImagenesDAO;
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
		public GenericDAO<TipoComprob> getTipoComprobDAO() {
			return tipoComprobDAO;
		}
		public void setTipoComprobDAO(GenericDAO<TipoComprob> tipoComprobDAO) {
			this.tipoComprobDAO = tipoComprobDAO;
		}
		public PasebanDAO getExtendedPasebanDAO() {
			return extendedPasebanDAO;
		}
		public void setExtendedPasebanDAO(PasebanDAO extendedPasebanDAO) {
			this.extendedPasebanDAO = extendedPasebanDAO;
		}
		
		public List<Paseban> getPasebanPorAgendado(Integer genteNr) {
			List<Paseban> pasebanList = extendedPasebanDAO.getPasebanPorAgendado(genteNr);	
			return pasebanList;
		}

		public byte[] getCheque(String serie) {
				Imagenes imagenes = extendedImagenesDAO.getImagenesPorSubStringPK(serie);
				if (imagenes != null)
					return imagenes.getImagen1();
				else
					return null;
		}

		public boolean existeCheque(String serie) {
			boolean existe = extendedImagenesDAO.existeCheque(serie);
			return existe;
		}
		
		public Double getPasebanTotalPorAgendado(Integer genteNr) {
			Double total = extendedPasebanDAO.getPasebanTotalPorAgendado(genteNr);	
			total = MathUtil.redondearEn2(total);
			return total;
		}
 	  	


}

