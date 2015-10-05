package jmc.skweb.core.dao.impl;


import java.util.List;

import jmc.skweb.core.dao.CondiDAO;
import jmc.skweb.core.model.Condi;

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
