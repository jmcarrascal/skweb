package jmc.skweb.core.dao.impl;


import java.util.List;

import jmc.skweb.core.dao.ArtMadreDAO;
import jmc.skweb.core.model.ArtMadre;

	/**
	 * 
	 * @author Juan Manuel
	 * 
	 */

	public class ArtMadreDAOImpl extends GenericDAOImpl<ArtMadre> implements ArtMadreDAO {

		public ArtMadreDAOImpl() {
			super(ArtMadre.class);		 
		}

		
		public List<ArtMadre> getArtMadrePorDesc(String descArtMad) {
			String[] filtros = descArtMad.split("\\+");
			String whereSql = "";
			boolean primerReg = true;
			for(String filtroDet: filtros){
				if (primerReg){
					whereSql = "a.descArtMad like '%"+ filtroDet +"%'";	
					primerReg = false;
				}else{
					whereSql = whereSql + " and a.descArtMad like '%"+ filtroDet +"%'";
				}	
			}
						
			List<ArtMadre> artMadreList = null; 
			try{
				
				String sql ="select a from ArtMadre a where " + whereSql ;				 
			      
				artMadreList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return artMadreList;
		}
		
}
