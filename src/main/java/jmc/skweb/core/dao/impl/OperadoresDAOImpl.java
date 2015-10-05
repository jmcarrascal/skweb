package jmc.skweb.core.dao.impl;


import java.util.List;

import jmc.skweb.core.dao.OperadoresDAO;
import jmc.skweb.core.model.Operadores;

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
