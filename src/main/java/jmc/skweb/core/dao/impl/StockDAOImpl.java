package jmc.skweb.core.dao.impl;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Settings;
import org.hibernate.cfg.SettingsFactory;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Table;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;



import jmc.skweb.core.dao.FamDAO;
import jmc.skweb.core.dao.ParametrizacionDAO;
import jmc.skweb.core.dao.StockDAO;
import jmc.skweb.core.model.Fam;
import jmc.skweb.core.model.Parametrizacion;
import jmc.skweb.core.model.Stock;
import jmc.skweb.util.GenerateHqlFilterAndSort;

	/**
	 * 
	 * @author Juan Manuel
	 * 
	 */

	
	public class StockDAOImpl extends GenericDAOImpl<Stock> implements StockDAO {

		public StockDAOImpl() {
			super(Stock.class);		 
		}
		
		public List<Stock> getArticuloPorNombre(String filtro, boolean activos) throws Exception {
			String[] filtros = filtro.split("\\+");
			String whereSql = "";
			boolean primerReg = true;
			for(String filtroDet: filtros){
				if (primerReg){
					whereSql = "s.descripcion like '%"+ filtroDet +"%'";	
					primerReg = false;
				}else{
					whereSql = whereSql + " and s.descripcion like '%"+ filtroDet +"%'";
				}	
			}
						
			List<Stock> stockList = null; 
			try{
				String sql = "";
				if (activos)
					sql ="select s from Stock s, Fam f where " + whereSql + " and s.fam = f.nrfam and f.skWebVisible = -1 and preEsp6 <> -1 order by s.descripcion " ;				 
				else
					sql ="select s from Stock s, Fam f where " + whereSql + " and s.fam = f.nrfam and f.skWebVisible = -1 order by s.descripcion " ;
				
				stockList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return stockList;
		}


		

		public List<Stock> getArticuloPorNrFamSubFam(Long nrfam, Long nrsubfam, boolean activos) {
			List<Stock> stockList = null; 
			try{
				
				String sql = "";
				if (activos)
					sql = "select s from Stock s where subFam = " + nrsubfam + " and fam = " + nrfam + " and preEsp6 <> -1 order by s.descripcion ";
				else
					sql = "select s from Stock s where subFam = " + nrsubfam + " and fam = " + nrfam + " order by s.descripcion ";
			      
				stockList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return stockList;
		}


		public List<Stock> getArticuloPorNrFam(Long nrfam, boolean activos) {
			List<Stock> stockList = null; 
			try{
				
				String sql = "";
				if(activos)
					sql = "select s from Stock s where fam = " + nrfam + " and preEsp6 <> -1 order by s.descripcion ";		
				else
					sql = "select s from Stock s where fam = " + nrfam + " order by s.descripcion ";
			      
				stockList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return stockList;
		}

		/**
		 * Obtiene los Articulos por proveedor
		 */
		public List<Stock> getArticuloPorNrProveedor(Integer genteNr, boolean activos) {
			List<Stock> stockList = null; 
			try{
				String sql = "";
				if (activos)
					sql ="select s from Stock s where proveedPrincip = " + genteNr + " and preEsp6 <> -1 order by s.descripcion ";				 
				else
					sql ="select s from Stock s where proveedPrincip = " + genteNr + " order by s.descripcion ";
				
				stockList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return stockList;
			
		}
				


		public List<Stock> getArticuloPorNombreLimit(String filter, int i) {
			return null;
			
		}

		
		public String getArticuloPorPK(String clave, boolean articuloActivo) {
			List<Stock> stockList = null; 
			String result = "";
			try{
				String sql = "";
				if (articuloActivo)
					sql ="select s from Stock s where s.clave = '" + clave + "' and preEsp6 <> -1";				 
				else
					sql ="select s from Stock s where s.clave = '" + clave + "'";
				
				stockList = getHibernateTemplate().find(sql);
				if(stockList.size() > 0)
					result  = stockList.get(0).getDescripC();
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return result;
		}		
		
		public List<Stock> getImagenArticuloPorNrSubFam(Long nrsubfam, boolean activos) {
			List<Stock> stockList = null; 
			try{
				
				String sql = "";
				if (activos)
					sql = "select s from Stock s, ImagenesArticulos i where s.clave = i.clave and subFam = " + nrsubfam + " and preEsp6 <> -1 order by s.descripcion ";
				else
					sql = "select s from Stock s, ImagenesArticulos i where s.clave = i.clave and subFam = " + nrsubfam + " order by s.descripcion ";
			      
				stockList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return stockList;
		}


		public List<Stock> getImagenArticuloPorNrFamSubFam(Long nrfam, Long nrsubfam, boolean activos) {
			List<Stock> stockList = null; 
			try{
				
				String sql = "";
				if (activos)
					sql = "select s from Stock s, ImagenesArticulos i where s.clave = i.clave and subFam = " + nrsubfam + " and fam = " + nrfam + " and preEsp6 <> -1 order by s.descripcion ";
				else
					sql = "select s from Stock s, ImagenesArticulos i where s.clave = i.clave and subFam = " + nrsubfam + " and fam = " + nrfam + " order by s.descripcion ";
			      
				stockList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return stockList;
		}


		public List<Stock> getImagenArticuloPorNrFam(Long nrfam, boolean activos) {
			List<Stock> stockList = null; 
			try{
				
				String sql = "";
				if(activos)
					sql = "select s from Stock s, ImagenesArticulos i where s.clave = i.clave and fam = " + nrfam + " and preEsp6 <> -1 order by s.descripcion ";		
				else
					sql = "select s from Stock s, ImagenesArticulos i where s.clave = i.clave and fam = " + nrfam + " order by s.descripcion ";
			      
				stockList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return stockList;
		}

		
		public List<Stock> getArticuloPorNrSubFam(Long nrsubfam, boolean activos) {
			List<Stock> stockList = null; 
			try{
				
				String sql = "";
				if(activos)
					sql = "select s from Stock s where subFam = " + nrsubfam + " and preEsp6 <> -1 order by s.descripcion ";		
				else
					sql = "select s from Stock s where subFam = " + nrsubfam + " order by s.descripcion ";
			      
				stockList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return stockList;
		}

		
		public List<Stock> getArticuloPorNrSubFam(Long nrsubfam, boolean activos,String propertySort, String orderSort, String propertyFilter[], String valueFilter[], Integer min, int max) {
			final Integer minSize = min;
			final Integer maxSize = max;
			String q = "";
			if (activos)
				q = "select s from Stock s where subFam = " + nrsubfam + " and preEsp6 <> -1 " + GenerateHqlFilterAndSort.getHqlFilterAndSort("Stock",propertySort,orderSort,"s",propertyFilter,"s",valueFilter,false);
			else
				q = "select s from Stock s where subFam = " + nrsubfam + GenerateHqlFilterAndSort.getHqlFilterAndSort("Stock",propertySort,orderSort,"s",propertyFilter,"s",valueFilter,false);
			
			List<Stock> stockList = null; 
				try{
					final String queryst = q;
					System.out.println("Query Final:" + queryst);
					stockList = getHibernateTemplate().executeFind((new HibernateCallback() {
	                    public Object doInHibernate(Session session) throws HibernateException, SQLException {
	                        Query query = session.createQuery(queryst);
	                        //query.setFetchSize(15);
	                        query.setMaxResults(maxSize.intValue());
	                        query.setFirstResult(minSize.intValue());
	                        return query.list();
	                    }
	                }));
					
			}catch (DataAccessException e) {
				e.printStackTrace();
				
			}
			return stockList;
		}

		
		public Long getCountArticuloPorNrSubFam(Long nrsubfam, boolean activos,
				String propertySort, String orderSort, String[] propertyFilter,
				String[] valueFilter, Integer min, int pageSize) {
			Long count = 0l;
			try{								
				String q = "";
				if (activos)
					q = "select count(s) from Stock s where subFam = " + nrsubfam + " and preEsp6 <> -1 " + GenerateHqlFilterAndSort.getHqlFilterAndSort("Stock",propertySort,orderSort,"s",propertyFilter,"s",valueFilter,false);
				else
					q = "select count(s) from Stock s where subFam = " + nrsubfam + GenerateHqlFilterAndSort.getHqlFilterAndSort("Stock",propertySort,orderSort,"s",propertyFilter,"s",valueFilter,false);
			     
				List<Long> saldoList= getHibernateTemplate().find(q);
				count = saldoList.get(0);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}

			return count;
		}

		public List<Stock> getArticuloPorNrFamSubFam(Long nrfam, Long nrsubfam,
				boolean activos, String propertySort, String orderSort,
				String[] propertyFilter, String[] valueFilter, Integer min,
				int pageSize) {
			final Integer minSize = min;
			final Integer maxSize = pageSize;
			String q = "";
			if (activos)
				q = "select s from Stock s where subFam = " + nrsubfam + " and fam = " + nrfam + " and preEsp6 <> -1 " + GenerateHqlFilterAndSort.getHqlFilterAndSort("Stock",propertySort,orderSort,"s",propertyFilter,"s",valueFilter,false);
			else
				q = "select s from Stock s where subFam = " + nrsubfam + " and fam = " + nrfam + GenerateHqlFilterAndSort.getHqlFilterAndSort("Stock",propertySort,orderSort,"s",propertyFilter,"s",valueFilter,false);
			
			List<Stock> stockList = null; 
				try{
					final String queryst = q;
					System.out.println("Query Final:" + queryst);
					stockList = getHibernateTemplate().executeFind((new HibernateCallback() {
	                    public Object doInHibernate(Session session) throws HibernateException, SQLException {
	                        Query query = session.createQuery(queryst);
	                        //query.setFetchSize(15);
	                        query.setMaxResults(maxSize.intValue());
	                        query.setFirstResult(minSize.intValue());
	                        return query.list();
	                    }
	                }));
					
			}catch (DataAccessException e) {
				e.printStackTrace();
				
			}
			return stockList;
		}

		
		public Long getCountArticuloPorNrFamSubFam(Long nrfam, Long nrsubfam,
				boolean activos, String propertySort, String orderSort,
				String[] propertyFilter, String[] valueFilter, Integer min,
				int pageSize) {
			Long count = 0l;
			try{								
				String q = "";
				if (activos)
					q = "select count(s) from Stock s where subFam = " + nrsubfam + " and fam = " + nrfam + " and preEsp6 <> -1 " + GenerateHqlFilterAndSort.getHqlFilterAndSort("Stock",propertySort,orderSort,"s",propertyFilter,"s",valueFilter,false);
				else
					q = "select count(s) from Stock s where subFam = " + nrsubfam + " and fam = " + nrfam + GenerateHqlFilterAndSort.getHqlFilterAndSort("Stock",propertySort,orderSort,"s",propertyFilter,"s",valueFilter,false);
			     
				List<Long> saldoList= getHibernateTemplate().find(q);
				count = saldoList.get(0);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}

			return count;
		}

	
		public Long getCountArticuloPorNrFam(Long nrfam, boolean activos,
				String propertySort, String orderSort, String[] propertyFilter,
				String[] valueFilter, Integer min, int pageSize) {
			Long count = 0l;
			try{								
				String q = "";
				if (activos)
					q = "select count(s) from Stock s where fam = " + nrfam + " and preEsp6 <> -1 " + GenerateHqlFilterAndSort.getHqlFilterAndSort("Stock",propertySort,orderSort,"s",propertyFilter,"s",valueFilter,false);
				else
					q = "select count(s) from Stock s where fam = " + nrfam + GenerateHqlFilterAndSort.getHqlFilterAndSort("Stock",propertySort,orderSort,"s",propertyFilter,"s",valueFilter,false);
			     
				List<Long> saldoList= getHibernateTemplate().find(q);
				count = saldoList.get(0);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}

			return count;
		}

		public List<Stock> getArticuloPorNrFam(Long nrfam, boolean activos,
				String propertySort, String orderSort, String[] propertyFilter,
				String[] valueFilter, Integer min, int pageSize) {
			
			final Integer minSize = min;
			final Integer maxSize = pageSize;
			String q = "";
			if (activos)
				q = "select s from Stock s where fam = " + nrfam + " and preEsp6 <> -1 " + GenerateHqlFilterAndSort.getHqlFilterAndSort("Stock",propertySort,orderSort,"s",propertyFilter,"s",valueFilter,false);
			else
				q = "select s from Stock s where fam = " + nrfam + GenerateHqlFilterAndSort.getHqlFilterAndSort("Stock",propertySort,orderSort,"s",propertyFilter,"s",valueFilter,false);
			
			List<Stock> stockList = null; 
				try{
					final String queryst = q;
					System.out.println("Query Final:" + queryst);
					stockList = getHibernateTemplate().executeFind((new HibernateCallback() {
	                    public Object doInHibernate(Session session) throws HibernateException, SQLException {
	                        Query query = session.createQuery(queryst);
	                        //query.setFetchSize(15);
	                        query.setMaxResults(maxSize.intValue());
	                        query.setFirstResult(minSize.intValue());
	                        return query.list();
	                    }
	                }));
					
			}catch (DataAccessException e) {
				e.printStackTrace();
				
			}
			return stockList;
		}

		public Long getCountArticuloPorNombre(String descripcion,boolean activos,
				String propertySort, String orderSort, String[] propertyFilter,
				String[] valueFilter, Integer min, int pageSize) {
			
			String[] filtros = descripcion.split("\\+");
			String whereSql = "s.descripcion like '%" + descripcion+ "%' ";
			boolean primerReg = true;
			for(String filtroDet: filtros){
				if (primerReg){
					whereSql = "s.descripcion like '%"+ filtroDet +"%' ";	
					primerReg = false;
				}else{
					whereSql = whereSql + " and s.descripcion like '%"+ filtroDet +"%' ";
				}	
			}
			Long count = 0l;
			try{								
				String q = "";
				if (activos)
					q = "select count(s) from Stock s, Fam f where " + whereSql + " and s.fam = f.nrfam and f.skWebVisible = -1 and preEsp6 <> -1 " + GenerateHqlFilterAndSort.getHqlFilterAndSort("Stock",propertySort,orderSort,"s",propertyFilter,"s",valueFilter,false);
				else
					q = "select count(s) from Stock s, Fam f where " + whereSql + " and s.fam = f.nrfam and f.skWebVisible = -1 " +GenerateHqlFilterAndSort.getHqlFilterAndSort("Stock",propertySort,orderSort,"s",propertyFilter,"s",valueFilter,false);
			     
				List<Long> saldoList= getHibernateTemplate().find(q);
				count = saldoList.get(0);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}

			return count;
		}


		public List<Stock> getArticuloPorNombre(String descripcion,
				boolean activos, String propertySort, String orderSort,
				String[] propertyFilter, String[] valueFilter, Integer min,
				int pageSize) {
			final Integer minSize = min;
			final Integer maxSize = pageSize;

			String[] filtros = descripcion.split("\\+");
			String whereSql = "s.descripcion like '%" + descripcion+ "%' ";
			boolean primerReg = true;
			for(String filtroDet: filtros){
				if (primerReg){
					whereSql = "s.descripcion like '%"+ filtroDet +"%' ";	
					primerReg = false;
				}else{
					whereSql = whereSql + " and s.descripcion like '%"+ filtroDet +"%' ";
				}	
			}

			String q = "";
			if (activos)
				q = "select s from Stock s, Fam f where " + whereSql + " and s.fam = f.nrfam and f.skWebVisible = -1 and preEsp6 <> -1 " + GenerateHqlFilterAndSort.getHqlFilterAndSort("Stock",propertySort,orderSort,"s",propertyFilter,"s",valueFilter,false);
			else
				q = "select s from Stock s, Fam f where " + whereSql + " and s.fam = f.nrfam and f.skWebVisible = -1 " + GenerateHqlFilterAndSort.getHqlFilterAndSort("Stock",propertySort,orderSort,"s",propertyFilter,"s",valueFilter,false);
		
			List<Stock> stockList = null; 
				try{
					final String queryst = q;
					System.out.println("Query Final:" + queryst);
					stockList = getHibernateTemplate().executeFind((new HibernateCallback() {
	                    public Object doInHibernate(Session session) throws HibernateException, SQLException {
	                        Query query = session.createQuery(queryst);
	                        //query.setFetchSize(15);
	                        query.setMaxResults(maxSize.intValue());
	                        query.setFirstResult(minSize.intValue());
	                        return query.list();
	                    }
	                }));
					
			}catch (DataAccessException e) {
				e.printStackTrace();
				
			}
			return stockList;
		}

		public List<Stock> getArticuloPorCodigoLike(String clave,
				boolean articuloActivo) {
			String[] filtros = clave.split("\\+");
			String whereSql = "";
			boolean primerReg = true;
			for(String filtroDet: filtros){
				if (primerReg){
					whereSql = "s.clave like '%"+ filtroDet +"%'";	
					primerReg = false;
				}else{
					whereSql = whereSql + " and s.clave like '%"+ filtroDet +"%'";
				}	
			}
						
			List<Stock> stockList = null; 
			try{
				String sql = "";
				if (articuloActivo)
					sql ="select s from Stock s, Fam f where " + whereSql + " and s.fam = f.nrfam and f.skWebVisible = -1 and preEsp6 <> -1 order by s.clave " ;				 
				else
					sql ="select s from Stock s, Fam f where " + whereSql + " and s.fam = f.nrfam and f.skWebVisible = -1 order by s.clave " ;
				
				stockList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return stockList;
		}

	
		public Long getCountArticuloPorCodigo(String clave,
				boolean articuloActivo, String propertySort, String orderSort,
				String[] propertyFilter, String[] valueFilter, Integer min,
				int pageSize) {
			String[] filtros = clave.split("\\+");
			String whereSql = "s.clave like '%" + clave+ "%' ";
			boolean primerReg = true;
			for(String filtroDet: filtros){
				if (primerReg){
					whereSql = "s.clave like '%"+ filtroDet +"%' ";	
					primerReg = false;
				}else{
					whereSql = whereSql + " and s.clave like '%"+ filtroDet +"%' ";
				}	
			}
			Long count = 0l;
			try{								
				String q = "";
				if (articuloActivo)
					q = "select count(s) from Stock s, Fam f where " + whereSql + " and s.fam = f.nrfam and f.skWebVisible = -1 and preEsp6 <> -1 " + GenerateHqlFilterAndSort.getHqlFilterAndSort("Stock",propertySort,orderSort,"s",propertyFilter,"s",valueFilter,false);
				else
					q = "select count(s) from Stock s, Fam f where " + whereSql + GenerateHqlFilterAndSort.getHqlFilterAndSort("Stock",propertySort,orderSort,"s",propertyFilter,"s",valueFilter,false);
			     
				List<Long> saldoList= getHibernateTemplate().find(q);
				count = saldoList.get(0);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}

			return count;
		}


		public List<Stock> getArticuloPorClave(String clave,
				boolean articuloActivo, String propertySort, String orderSort,
				String[] propertyFilter, String[] valueFilter, Integer min,
				int pageSize) {
			final Integer minSize = min;
			final Integer maxSize = pageSize;

			String[] filtros = clave.split("\\+");
			String whereSql = "s.clave like '%" + clave+ "%' ";
			boolean primerReg = true;
			for(String filtroDet: filtros){
				if (primerReg){
					whereSql = "s.clave like '%"+ filtroDet +"%' ";	
					primerReg = false;
				}else{
					whereSql = whereSql + " and s.clave like '%"+ filtroDet +"%' ";
				}	
			}

			String q = "";
			if (articuloActivo)
				q = "select s from Stock s, Fam f where " + whereSql + " and s.fam = f.nrfam and f.skWebVisible = -1 and preEsp6 <> -1 " + GenerateHqlFilterAndSort.getHqlFilterAndSort("Stock",propertySort,orderSort,"s",propertyFilter,"s",valueFilter,false);
			else
				q = "select s from Stock s, Fam f where " + whereSql + " and s.fam = f.nrfam and f.skWebVisible = -1 " + GenerateHqlFilterAndSort.getHqlFilterAndSort("Stock",propertySort,orderSort,"s",propertyFilter,"s",valueFilter,false);
		
			List<Stock> stockList = null; 
				try{
					final String queryst = q;
					System.out.println("Query Final:" + queryst);
					stockList = getHibernateTemplate().executeFind((new HibernateCallback() {
	                    public Object doInHibernate(Session session) throws HibernateException, SQLException {
	                        Query query = session.createQuery(queryst);
	                        //query.setFetchSize(15);
	                        query.setMaxResults(maxSize.intValue());
	                        query.setFirstResult(minSize.intValue());
	                        return query.list();
	                    }
	                }));
					
			}catch (DataAccessException e) {
				e.printStackTrace();
				
			}
			return stockList;
		}
		
		public List<Stock> getArticuloPorClaveList(String clave,
				boolean articuloActivo) {
			
			String q = "";
			if (articuloActivo)
				q = "select s from Stock s, Fam f where clave = '" + clave + "' and s.fam = f.nrfam and f.skWebVisible = -1 and preEsp6 <> -1 " ;
			else
				q = "select s from Stock s, Fam f where clave = '" + clave + "' and s.fam = f.nrfam and f.skWebVisible = -1 " ;
		
			List<Stock> stockList = null; 
				try{
					final String queryst = q;
					System.out.println("Query Final:" + queryst);
					stockList = getHibernateTemplate().executeFind((new HibernateCallback() {
	                    public Object doInHibernate(Session session) throws HibernateException, SQLException {
	                        Query query = session.createQuery(queryst);
	                       
	                        return query.list();
	                    }
	                }));
					
			}catch (DataAccessException e) {
				e.printStackTrace();
				
			}
			return stockList;
		}
}
