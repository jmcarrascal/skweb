package jmc.skweb.core.dao.impl;


import java.math.BigDecimal;
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


import jmc.skweb.core.dao.FamDAO;
import jmc.skweb.core.dao.ImagenesDAO;
import jmc.skweb.core.dao.ParametrizacionDAO;
import jmc.skweb.core.dao.PasebanDAO;
import jmc.skweb.core.dao.StockDAO;
import jmc.skweb.core.dao.TransacDAO;
import jmc.skweb.core.model.Fam;
import jmc.skweb.core.model.Imagenes;
import jmc.skweb.core.model.Parametrizacion;
import jmc.skweb.core.model.Paseban;
import jmc.skweb.core.model.Stock;
import jmc.skweb.core.model.Transac;

	/**
	 * 
	 * @author Juan Manuel
	 * 
	 */

	public class ImagenesDAOImpl extends GenericDAOImpl<Imagenes> implements ImagenesDAO {

		public ImagenesDAOImpl() {
			super(Imagenes.class);		 
		}


		public Imagenes getImagenesPorSubStringPK(String clave) {
			Imagenes imagenes = null; 
			try{
				
				String sql ="select i from Imagenes i where SUBSTRING(clave,11,27) = '" +clave+ "' ";				 
			      
				List<Imagenes> imagenesList = getHibernateTemplate().find(sql);
				if (imagenesList.size() > 0){
					imagenes = imagenesList.get(0);
				}
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return imagenes;
		}



		public boolean existeCheque(String serie) {
			boolean result = false;
			try{
				
				String sql ="select count(i.clave) from Imagenes i where SUBSTRING(clave,11,27) = '" +serie+ "' ";				 
			      
				List<Long> intList = getHibernateTemplate().find(sql);
				
				
				int count = intList.get(0).intValue();
				
				if (count > 0){
					result = true;
				}
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return result;
			
		}
}
