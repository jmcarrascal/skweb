package jmc.skweb.core.dao.impl;


import java.util.List;

import jmc.skweb.core.dao.TrazabiDAO;
import jmc.skweb.core.model.traza.Trazabi;

	/**
	 * 
	 * @author Juan Manuel
	 * 
	 */

	public class TrazabiDAOImpl extends GenericDAOImpl<Trazabi> implements TrazabiDAO {

		public TrazabiDAOImpl() {
			super(Trazabi.class);		 
		}


		public List<Trazabi> getTrazabiPorComprobante(String comprob) {
			List<Trazabi> trazabiList = null; 
			try{				
				String sql = "select tr from Trazabi tr where tr.nrRemitoPropio = '" + comprob + "'";
				
				trazabiList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return trazabiList;
		}

		
	}
