package jmc.skweb.core.dao.impl;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;


import jmc.skweb.core.dao.FamDAO;
import jmc.skweb.core.dao.GenteDAO;
import jmc.skweb.core.dao.OperadoresDAO;
import jmc.skweb.core.dao.ParametrizacionDAO;
import jmc.skweb.core.model.Fam;
import jmc.skweb.core.model.Gente;
import jmc.skweb.core.model.Operadores;
import jmc.skweb.core.model.Parametrizacion;

	/**
	 * 
	 * @author Juan Manuel
	 * 
	 */

	public class OperadoresDAOImpl extends GenericDAOImpl<Operadores> implements OperadoresDAO {

		public OperadoresDAOImpl() {
			super(Operadores.class);		 
		}

		public List<Operadores> getOperadorPorNombre(String nombre)
				throws Exception {
			List<Operadores> operadoresList = null; 
			try{
				String[] filtros = nombre.split("\\+");
				String whereSql = "";
				boolean primerReg = true;
				for(String filtroDet: filtros){
					if (primerReg){
						whereSql = "o.operNombre like '%"+ filtroDet +"%'";	
						primerReg = false;
					}else{
						whereSql = whereSql + " and o.operNombre like '%"+ filtroDet +"%'";
					}	
				}

					String SQL_QUERY ="select o from Operadores o where " + whereSql ;
					operadoresList = getHibernateTemplate().find(SQL_QUERY);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return operadoresList;
		}

}
