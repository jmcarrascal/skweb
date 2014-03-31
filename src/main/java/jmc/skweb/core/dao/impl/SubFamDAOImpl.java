package jmc.skweb.core.dao.impl;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import jmc.skweb.core.dao.FamDAO;
import jmc.skweb.core.dao.ParametrizacionDAO;
import jmc.skweb.core.dao.SubFamDAO;
import jmc.skweb.core.model.Fam;
import jmc.skweb.core.model.Parametrizacion;
import jmc.skweb.core.model.SubFam;

	/**
	 * 
	 * @author Juan Manuel
	 * 
	 */

	public class SubFamDAOImpl extends GenericDAOImpl<SubFam> implements SubFamDAO {

		public SubFamDAOImpl() {
			super(SubFam.class);		 
		}
		

		public List<SubFam> getSubFamEnable()throws Exception {
			List<SubFam> subFamList = null; 
			try{
				 	
					//String SQL_QUERY ="select sf from SubFam sf where sf.desubfa <> '.' and sf.desubfa <> '....................'" ;
					String SQL_QUERY ="select sf from SubFam sf" ;
					subFamList = getHibernateTemplate().find(SQL_QUERY);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}


			return subFamList;
		}
		
		
		public List<SubFam> getSubFamByFam(Integer clave)throws Exception {
			List<SubFam> subFamList = null; 
			try{
				 	
					//String SQL_QUERY ="select sf from SubFam sf where sf.desubfa <> '.' and sf.desubfa <> '....................'" ;
					String SQL_QUERY ="select sf from SubFam sf where famaso = " + clave ;
					subFamList = getHibernateTemplate().find(SQL_QUERY);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}


			return subFamList;
		}

}
