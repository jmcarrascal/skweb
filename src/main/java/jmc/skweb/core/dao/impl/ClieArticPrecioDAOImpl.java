package jmc.skweb.core.dao.impl;


import java.util.List;

import jmc.skweb.core.dao.ClieArticPrecioDAO;
import jmc.skweb.core.model.ClieArticPrecio;

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
