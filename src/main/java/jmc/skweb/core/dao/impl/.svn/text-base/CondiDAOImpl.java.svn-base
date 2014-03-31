package jmc.skweb.core.dao.impl;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import jmc.skweb.core.dao.CondiDAO;
import jmc.skweb.core.dao.DomiciliosDAO;
import jmc.skweb.core.dao.FamDAO;
import jmc.skweb.core.dao.ParametrizacionDAO;
import jmc.skweb.core.dao.VendedorDAO;
import jmc.skweb.core.model.Condi;
import jmc.skweb.core.model.Domicilios;
import jmc.skweb.core.model.Fam;
import jmc.skweb.core.model.Gente;
import jmc.skweb.core.model.Parametrizacion;
import jmc.skweb.core.model.Vendedor;

	/**
	 * 
	 * @author Juan Manuel
	 * 
	 */

	public class CondiDAOImpl extends GenericDAOImpl<Condi> implements CondiDAO {

		public CondiDAOImpl() {
			super(Condi.class);		 
		}
		

	

		public List<Condi> getCondiAll() {
			List<Condi> condiList = null; 
			try{
				 	
					String SQL_QUERY ="select c from Condi c where c.descrip is not null";
					
					
					condiList = getHibernateTemplate().find(SQL_QUERY);
					

					
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return condiList;
		}

}
