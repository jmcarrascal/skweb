package jmc.skweb.core.dao.impl;


import java.util.List;

import jmc.skweb.core.dao.StockPiezasDAO;
import jmc.skweb.core.model.Colores;
import jmc.skweb.core.model.StockPiezas;
import jmc.skweb.core.model.StockPiezasId;

	/**
	 * 
	 * @author Juan Manuel
	 * 
	 */

	
	public class StockPiezasDAOImpl extends GenericDAOImpl<StockPiezas> implements StockPiezasDAO {

		public StockPiezasDAOImpl() {
			super(StockPiezas.class);		 
		}
		
		public List<StockPiezas> getStockPiezasPorArticulo(String clave) {
			List<StockPiezas> stockPiezasList = null; 
			try{
				
				String sql ="select sp from StockPiezas sp where sp.id.stock.clave = '" + clave + "'";				 
				
				stockPiezasList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			
			
			
			return stockPiezasList;
		}


		
}
