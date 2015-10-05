package jmc.skweb.core.dao.impl;


import java.util.List;

import jmc.skweb.core.dao.VendedorDAO;
import jmc.skweb.core.model.Vendedor;

	/**
	 * 
	 * @author Juan Manuel
	 * 
	 */

	public class VendedorDAOImpl extends GenericDAOImpl<Vendedor> implements VendedorDAO {

		public VendedorDAOImpl() {
			super(Vendedor.class);		 
		}
		

		public List<Vendedor> getVendedorEnable() {
			List<Vendedor> vendedorList = null; 
			try{
				 	
					String SQL_QUERY ="select v from Vendedor v where v.descrip <> '.' and v.descrip <> '....................' and v.descrip is not null" ;
					vendedorList = getHibernateTemplate().find(SQL_QUERY);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}


			return vendedorList;
		}

}
