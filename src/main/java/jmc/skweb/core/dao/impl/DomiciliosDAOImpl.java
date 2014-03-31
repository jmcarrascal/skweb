package jmc.skweb.core.dao.impl;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import jmc.skweb.core.dao.DomiciliosDAO;
import jmc.skweb.core.dao.FamDAO;
import jmc.skweb.core.dao.ParametrizacionDAO;
import jmc.skweb.core.dao.VendedorDAO;
import jmc.skweb.core.model.Domicilios;
import jmc.skweb.core.model.Fam;
import jmc.skweb.core.model.Gente;
import jmc.skweb.core.model.Parametrizacion;
import jmc.skweb.core.model.Vendedor;

	/**
	 * 
	 * @author Juan Manuel
	 * 
	 */

	public class DomiciliosDAOImpl extends GenericDAOImpl<Domicilios> implements DomiciliosDAO {

		public DomiciliosDAOImpl() {
			super(Domicilios.class);		 
		}
		

		public Domicilios getDomicilioPrincipal(Integer genteNr) {
			List<Domicilios> domiciliosList = null; 
			Domicilios domicilios = new Domicilios(); 
			try{
				 	
					String SQL_QUERY ="select d from Domicilios d where d.id.genteNr = " + genteNr + "and d.domiPrincipal = -1";
					
					
					domiciliosList = getHibernateTemplate().find(SQL_QUERY);
					
					if(domiciliosList != null && domiciliosList.size() > 0 ){
						domicilios = domiciliosList.get(0); 
					}
					
					
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}


			return domicilios;
		}


		
		public Domicilios getDomicilioAdm(Integer genteNr) {
			List<Domicilios> domiciliosList = null; 
			Domicilios domicilios = new Domicilios(); 
			try{
				 	
					String SQL_QUERY ="select d from Domicilios d where d.id.genteNr = " + genteNr + "and d.descripcion like '%@adm%'";
					
					
					domiciliosList = getHibernateTemplate().find(SQL_QUERY);
					
					if(domiciliosList != null && domiciliosList.size() > 0 ){
						domicilios = domiciliosList.get(0); 
					}
					
					
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}


			return domicilios;
		}


		public List<Domicilios> getDomiciliosPorGente(Gente gente) {
			List<Domicilios> domiciliosList = null; 
			Domicilios domicilios = new Domicilios(); 
			try{
				 	
					String SQL_QUERY ="select d from Domicilios d where d.id.genteNr = " + gente.getGenteNr() + " order by domiPrincipal asc";
					
					
					domiciliosList = getHibernateTemplate().find(SQL_QUERY);
					
					if(domiciliosList != null && domiciliosList.size() > 0 ){
						domicilios = domiciliosList.get(0); 
					}
					
					
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return domiciliosList;

		}

}
