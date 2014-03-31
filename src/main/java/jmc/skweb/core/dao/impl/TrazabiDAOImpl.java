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
import jmc.skweb.core.dao.ParametrizacionDAO;
import jmc.skweb.core.dao.StockDAO;
import jmc.skweb.core.dao.TransacDAO;
import jmc.skweb.core.dao.TrazabiDAO;
import jmc.skweb.core.model.Fam;
import jmc.skweb.core.model.Gente;
import jmc.skweb.core.model.Parametrizacion;
import jmc.skweb.core.model.Stock;
import jmc.skweb.core.model.Transac;
import jmc.skweb.core.model.report.TransacJoin;
import jmc.skweb.core.model.traza.Trazabi;
import jmc.skweb.util.Constants;

	/**
	 * 
	 * @author Juan Manuel
	 * 
	 */

	public class TrazabiDAOImpl extends GenericDAOImpl<Trazabi> implements TrazabiDAO {

		public TrazabiDAOImpl() {
			super(Trazabi.class);		 
		}


		public List<Trazabi> getTrazabiPorComprobante(String comprob) {
			List<Trazabi> trazabiList = null; 
			try{				
				String sql = "select tr from Trazabi tr where tr.nrRemitoPropio = '" + comprob + "'";
				
				trazabiList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return trazabiList;
		}

		
	}
