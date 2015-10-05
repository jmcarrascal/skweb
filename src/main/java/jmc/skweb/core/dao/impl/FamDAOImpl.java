package jmc.skweb.core.dao.impl;


import java.util.List;

import jmc.skweb.core.dao.FamDAO;
import jmc.skweb.core.model.Fam;

	/**
	 * 
	 * @author Juan Manuel
	 * 
	 */

	public class FamDAOImpl extends GenericDAOImpl<Fam> implements FamDAO {

		public FamDAOImpl() {
			super(Fam.class);		 
		}
		

		public List<Fam> getFamEnable()throws Exception {
			List<Fam> famList = null; 
			try{
				 	
					String SQL_QUERY ="select f from Fam f where f.skWebVisible = -1" ;
					//String SQL_QUERY ="select f from Fam f where f.desfam <> '.' and f.desfam <> '....................'" ;
					famList = getHibernateTemplate().find(SQL_QUERY);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}


			return famList;
		}

}
