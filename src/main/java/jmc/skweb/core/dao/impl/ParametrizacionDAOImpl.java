package jmc.skweb.core.dao.impl;

import java.util.List;

import jmc.skweb.core.dao.ParametrizacionDAO;
import jmc.skweb.core.model.Parametrizacion;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

	/**
	 * 
	 * @author Juan Manuel
	 * 
	 */

	public class ParametrizacionDAOImpl implements ParametrizacionDAO {

		private Session session = null;
		private SessionFactory sessionFactory;
		
		
		public SessionFactory getSessionFactory() {
			return sessionFactory;
		}

		public void setSessionFactory(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
		}

		public ParametrizacionDAOImpl(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
		}

		public Parametrizacion getByPrimaryKey(Long idParametrizacion)
				throws Exception {
			Parametrizacion parametrizacion = null; 
			try{
				// This step will read hibernate.cfg.xml and prepare hibernate for use
				SessionFactory sessionFactory = this.getSessionFactory();
				 session =sessionFactory.getCurrentSession();
				 session.beginTransaction();
					//Using from Clause
				 	String SQL_QUERY ="from Parametrizacion parametrizacion where parametrizacion.idParametrizacion = " + idParametrizacion;
				 	Query query = session.createQuery(SQL_QUERY);
				 	List<Parametrizacion> parametrizacionList = query.list();
				 	if (parametrizacionList.size() > 0)
				 		parametrizacion = (Parametrizacion) parametrizacionList.get(0);			 	
			        //session.close();
				 	session.getTransaction().commit();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}


			return parametrizacion;
		}

}
