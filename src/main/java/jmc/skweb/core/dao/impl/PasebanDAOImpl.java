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
import jmc.skweb.core.dao.PasebanDAO;
import jmc.skweb.core.dao.StockDAO;
import jmc.skweb.core.dao.TransacDAO;
import jmc.skweb.core.model.Fam;
import jmc.skweb.core.model.Parametrizacion;
import jmc.skweb.core.model.Paseban;
import jmc.skweb.core.model.Stock;
import jmc.skweb.core.model.Transac;

	/**
	 * 
	 * @author Juan Manuel
	 * 
	 */

	public class PasebanDAOImpl extends GenericDAOImpl<Paseban> implements PasebanDAO {

		public PasebanDAOImpl() {
			super(Paseban.class);		 
		}


		public List<Paseban> getPasebanPorAgendado(Integer genteNr) {
			List<Paseban> pasebanList = null; 
			try{
				
				String sql ="select p from Paseban p where cobrado <> -1 and cajaNr = 4 and tipoMov = 5 and rechazado = -1 and genteNr = "  + genteNr + " order by fecha ";				 
			      
				pasebanList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return pasebanList;
		}

		public Double getPasebanTotalPorAgendado(Integer genteNr) {
			Double saldo = 0d;
			try{								
				String sql ="select SUM(p.importe) from Paseban p where cobrado <> -1 and cajaNr = 4 and tipoMov = 5 and rechazado = -1 and genteNr = "  + genteNr ;				 
			      
				List<BigDecimal> saldoList= getHibernateTemplate().find(sql);
				saldo = saldoList.get(0).doubleValue();
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return saldo;
		}
}
