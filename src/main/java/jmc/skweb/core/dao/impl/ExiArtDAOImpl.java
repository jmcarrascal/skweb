package jmc.skweb.core.dao.impl;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Settings;
import org.hibernate.cfg.SettingsFactory;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Table;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;


import jmc.skweb.core.dao.ExiArtDAO;
import jmc.skweb.core.dao.FamDAO;
import jmc.skweb.core.dao.ParametrizacionDAO;
import jmc.skweb.core.dao.StockDAO;
import jmc.skweb.core.model.ExiArt;
import jmc.skweb.core.model.Fam;
import jmc.skweb.core.model.Parametrizacion;
import jmc.skweb.core.model.Stock;

	/**
	 * 
	 * @author Juan Manuel
	 * 
	 */

	public class ExiArtDAOImpl extends GenericDAOImpl<ExiArt> implements ExiArtDAO {

		public ExiArtDAOImpl() {
			super(ExiArt.class);		 
		}
		
		public List<ExiArt> getExistenciaPorNombre(String clave)throws Exception {
			
			List<ExiArt> exiArtList = null; 
			try{
				
				String sql ="select e from ExiArt e where e.id.clave = '" + clave + "'" ;				 
			      
				exiArtList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return exiArtList;			
		}

		
		public Double getStockByExiClave(Double idExistencia, String clave) {
			List<ExiArt> exiArtList = null; 
			Double result = 0d;
			try{
				
				String sql ="select e from ExiArt e where e.id.clave = '" + clave + "' and e.id.existencias.nr = "+idExistencia ;				 
			      
				exiArtList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			if (exiArtList.size() > 0)
				result = exiArtList.get(0).getCantidad1();
			
			return result;
		}

		
		public List<ExiArt> getExistenciaPorNombre(String clave,
				Double idPreferenciaDefault) {
			List<ExiArt> exiArtList = null; 
			
			try{
				
				String sql ="select e from ExiArt e where e.id.clave = '" + clave + "' and e.id.existencias.nr = "+idPreferenciaDefault ;				 
			      
				exiArtList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			
			
			return exiArtList;
		}

			
}
