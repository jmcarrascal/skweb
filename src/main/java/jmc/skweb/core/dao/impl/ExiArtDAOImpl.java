package jmc.skweb.core.dao.impl;


import java.util.List;

import jmc.skweb.core.dao.ExiArtDAO;
import jmc.skweb.core.model.ExiArt;

	/**
	 * 
	 * @author Juan Manuel
	 * 
	 */

	public class ExiArtDAOImpl extends GenericDAOImpl<ExiArt> implements ExiArtDAO {

		public ExiArtDAOImpl() {
			super(ExiArt.class);		 
		}
		
		public List<ExiArt> getExistenciaPorNombre(String clave)throws Exception {
			
			List<ExiArt> exiArtList = null; 
			try{
				
				String sql ="select e from ExiArt e where e.id.clave = '" + clave + "'" ;				 
			      
				exiArtList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return exiArtList;			
		}

		
		public Double getStockByExiClave(Double idExistencia, String clave) {
			List<ExiArt> exiArtList = null; 
			Double result = 0d;
			try{
				
				String sql ="select e from ExiArt e where e.id.clave = '" + clave + "' and e.id.existencias.nr = "+idExistencia ;				 
			      
				exiArtList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			if (exiArtList.size() > 0)
				result = exiArtList.get(0).getCantidad1();
			
			return result;
		}

		
		public List<ExiArt> getExistenciaPorNombre(String clave,
				Double idPreferenciaDefault) {
			List<ExiArt> exiArtList = null; 
			
			try{
				
				String sql ="select e from ExiArt e where e.id.clave = '" + clave + "' and e.id.existencias.nr = "+idPreferenciaDefault ;				 
			      
				exiArtList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			
			
			return exiArtList;
		}

			
}
