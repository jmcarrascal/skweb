package jmc.skweb.core.dao.impl;


import java.util.List;

import jmc.skweb.core.dao.GenteDAO;
import jmc.skweb.core.model.Gente;

	/**
	 * 
	 * @author Juan Manuel
	 * 
	 */

	public class GenteDAOImpl extends GenericDAOImpl<Gente> implements GenteDAO {

		public GenteDAOImpl() {
			super(Gente.class);		 
		}
	

		public List<Gente> getProveedorPorNombre(String nombre)
				throws Exception {
			List<Gente> genteList = null; 
			try{
				String[] filtros = nombre.split("\\+");
				String whereSql = "";
				boolean primerReg = true;
				for(String filtroDet: filtros){
					if (primerReg){
						whereSql = "g.razonSocial like '%"+ filtroDet +"%'";	
						primerReg = false;
					}else{
						whereSql = whereSql + " and g.razonSocial like '%"+ filtroDet +"%'";
					}	
				}

					String SQL_QUERY ="select g from Gente g where " + whereSql ;
					genteList = getHibernateTemplate().find(SQL_QUERY);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return genteList;
		}


		
		public List<Gente> getProveedorPorNombreVendedor(
				String nombreProveedor, Integer vendedorNr) {
			List<Gente> genteList = null; 
			try{
				String[] filtros = nombreProveedor.split("\\+");
				String whereSql = "";
				boolean primerReg = true;
				for(String filtroDet: filtros){
					if (primerReg){
						whereSql = "g.razonSocial like '%"+ filtroDet +"%'";	
						primerReg = false;
					}else{
						whereSql = whereSql + " and g.razonSocial like '%"+ filtroDet +"%'";
					}	
				}

					String SQL_QUERY ="select g from Gente g where " + whereSql + " and g.vendedorNr = " + vendedorNr;
					genteList = getHibernateTemplate().find(SQL_QUERY);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return genteList;
		}

}
