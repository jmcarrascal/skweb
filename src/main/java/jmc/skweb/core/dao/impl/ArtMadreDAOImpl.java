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
import jmc.skweb.core.dao.FamDAO;
import jmc.skweb.core.dao.ParametrizacionDAO;
import jmc.skweb.core.dao.StockDAO;
import jmc.skweb.core.model.ArtMadre;
import jmc.skweb.core.model.Fam;
import jmc.skweb.core.model.Parametrizacion;
import jmc.skweb.core.model.Stock;

	/**
	 * 
	 * @author Juan Manuel
	 * 
	 */

	public class ArtMadreDAOImpl extends GenericDAOImpl<ArtMadre> implements ArtMadreDAO {

		public ArtMadreDAOImpl() {
			super(ArtMadre.class);		 
		}

		
		public List<ArtMadre> getArtMadrePorDesc(String descArtMad) {
			String[] filtros = descArtMad.split("\\+");
			String whereSql = "";
			boolean primerReg = true;
			for(String filtroDet: filtros){
				if (primerReg){
					whereSql = "a.descArtMad like '%"+ filtroDet +"%'";	
					primerReg = false;
				}else{
					whereSql = whereSql + " and a.descArtMad like '%"+ filtroDet +"%'";
				}	
			}
						
			List<ArtMadre> artMadreList = null; 
			try{
				
				String sql ="select a from ArtMadre a where " + whereSql ;				 
			      
				artMadreList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return artMadreList;
		}
		
}
