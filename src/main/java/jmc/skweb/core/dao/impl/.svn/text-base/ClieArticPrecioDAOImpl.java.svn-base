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


import jmc.skweb.core.dao.ArtMadreDAO;
import jmc.skweb.core.dao.ClieArticPrecioDAO;
import jmc.skweb.core.dao.FamDAO;
import jmc.skweb.core.dao.ParametrizacionDAO;
import jmc.skweb.core.dao.StockDAO;
import jmc.skweb.core.model.ArtMadre;
import jmc.skweb.core.model.ClieArticPrecio;
import jmc.skweb.core.model.Fam;
import jmc.skweb.core.model.Parametrizacion;
import jmc.skweb.core.model.Stock;

	/**
	 * 
	 * @author Juan Manuel
	 * 
	 */

	public class ClieArticPrecioDAOImpl extends GenericDAOImpl<ClieArticPrecio> implements ClieArticPrecioDAO {

		public ClieArticPrecioDAOImpl() {
			super(ClieArticPrecio.class);		 
		}


		public ClieArticPrecio getPrecioClienteArticulo(Integer genteNr,
				String clave) {
			List<ClieArticPrecio> clieArticPrecioList = null;
			ClieArticPrecio clieArticPrecio = null;
			try{
				
				String sql ="select c from ClieArticPrecio c where c.id.gente.genteNr = " + genteNr + " and c.id.stock.clave = '" + clave + "'"; 				 
			      
				clieArticPrecioList = getHibernateTemplate().find(sql);
				if (clieArticPrecioList.size() > 0){
					clieArticPrecio = clieArticPrecioList.get(0); 
				}
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return clieArticPrecio;

		}

}
