package jmc.skweb.core.dao.impl;


import java.math.BigDecimal;
import java.util.List;

import jmc.skweb.core.dao.PasebanDAO;
import jmc.skweb.core.model.Paseban;

	/**
	 * 
	 * @author Juan Manuel
	 * 
	 */

	public class PasebanDAOImpl extends GenericDAOImpl<Paseban> implements PasebanDAO {

		public PasebanDAOImpl() {
			super(Paseban.class);		 
		}


		public List<Paseban> getPasebanPorAgendado(Integer genteNr) {
			List<Paseban> pasebanList = null; 
			try{
				
				String sql ="select p from Paseban p where cobrado <> -1 and cajaNr = 4 and tipoMov = 5 and rechazado = -1 and genteNr = "  + genteNr + " order by fecha ";				 
			      
				pasebanList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return pasebanList;
		}

		public Double getPasebanTotalPorAgendado(Integer genteNr) {
			Double saldo = 0d;
			try{								
				String sql ="select SUM(p.importe) from Paseban p where cobrado <> -1 and cajaNr = 4 and tipoMov = 5 and rechazado = -1 and genteNr = "  + genteNr ;				 
			      
				List<BigDecimal> saldoList= getHibernateTemplate().find(sql);
				saldo = saldoList.get(0).doubleValue();
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return saldo;
		}
}
