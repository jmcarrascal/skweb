package jmc.skweb.core.dao.impl;


import java.util.List;

import jmc.skweb.core.dao.ImagenesDAO;
import jmc.skweb.core.model.Imagenes;

	/**
	 * 
	 * @author Juan Manuel
	 * 
	 */

	public class ImagenesDAOImpl extends GenericDAOImpl<Imagenes> implements ImagenesDAO {

		public ImagenesDAOImpl() {
			super(Imagenes.class);		 
		}


		public Imagenes getImagenesPorSubStringPK(String clave) {
			Imagenes imagenes = null; 
			try{
				
				String sql ="select i from Imagenes i where SUBSTRING(clave,11,27) = '" +clave+ "' ";				 
			      
				List<Imagenes> imagenesList = getHibernateTemplate().find(sql);
				if (imagenesList.size() > 0){
					imagenes = imagenesList.get(0);
				}
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return imagenes;
		}



		public boolean existeCheque(String serie) {
			boolean result = false;
			try{
				
				String sql ="select count(i.clave) from Imagenes i where SUBSTRING(clave,11,27) = '" +serie+ "' ";				 
			      
				List<Long> intList = getHibernateTemplate().find(sql);
				
				
				int count = intList.get(0).intValue();
				
				if (count > 0){
					result = true;
				}
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return result;
			
		}
}
