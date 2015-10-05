package jmc.skweb.core.dao.impl;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import jmc.skweb.core.dao.GenericDAO;
import jmc.skweb.util.exception.DataAccessDPIv1Exception;

import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author Juan Manuel Carrascal
 * @version 1.0
 * @created 12-Jan-2010 11:19:57 AM
 */

public class GenericDAOImpl<T> extends HibernateDaoSupport implements GenericDAO<T>{

	private Class<T> objectType;

	public GenericDAOImpl() {
		super();
	}
	
	public GenericDAOImpl(Class<T> objectType){
		this.objectType=objectType;
	}
	
	/*
	 * Realiza una consulta por los valores que esten seteados en el objetos, like y sin importar las mayusculas
	 */
	//@Transactional
	@SuppressWarnings("unchecked")
	public List<T> findByObjectCriteria(T object)throws DataAccessDPIv1Exception{
		List<T> result = new ArrayList<T>();	
		try{
			Example example = Example.create(object).enableLike();
			result = getSession().createCriteria(objectType).add(example).list();
			
		}catch (DataAccessException e) {
			e.printStackTrace();
			throw new DataAccessDPIv1Exception(DataAccessDPIv1Exception.GET_ERROR,e);
		}
		return result;

	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByObjectCriteria(HashMap<String, Object> res) throws DataAccessDPIv1Exception{
		List<T> result = new ArrayList<T>();
		try{
			Criteria criteria = getSession().createCriteria(objectType);
			for (Iterator iter = res.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				criteria.add(Restrictions.eq(name, res.get(name)));
			}
			result = criteria.list();
		}catch (DataAccessException e) {
			e.printStackTrace();
			throw new DataAccessDPIv1Exception(DataAccessDPIv1Exception.GET_ERROR,e);
		}
		return result;
	}	
	
	@SuppressWarnings("unchecked")
	public List<T> getAll() throws DataAccessDPIv1Exception {
		try{			
			return getHibernateTemplate().find("from "+objectType.getSimpleName());
		
		}catch (DataAccessException e){
			e.printStackTrace();
			throw new DataAccessDPIv1Exception(DataAccessDPIv1Exception.GET_ERROR,e);
		}
	}

	@SuppressWarnings("unchecked")
	public T getByPrimaryKey(Long id)throws DataAccessDPIv1Exception {
		T returnValue = null;
		try{
			
			returnValue= (T) getHibernateTemplate().get(objectType.getName(),id);
		
		}catch (DataAccessException e) {
			e.printStackTrace();
			throw new DataAccessDPIv1Exception(DataAccessDPIv1Exception.GET_ERROR,e);
		}
		return returnValue;
	}


	@SuppressWarnings("unchecked")
	public T getByPrimaryKey(Integer id)throws DataAccessDPIv1Exception {
		T returnValue = null;
		try{
			
			returnValue= (T) getHibernateTemplate().get(objectType.getName(),id);
		
		}catch (DataAccessException e) {
			e.printStackTrace();
			throw new DataAccessDPIv1Exception(DataAccessDPIv1Exception.GET_ERROR,e);
		}
		return returnValue;
	}

	@SuppressWarnings("unchecked")
	public T getByPrimaryKey(String id)throws DataAccessDPIv1Exception {
		T returnValue = null;
		try{
			
			returnValue= (T) getHibernateTemplate().get(objectType.getName(),id);
		
		}catch (DataAccessException e) {
			e.printStackTrace();
			throw new DataAccessDPIv1Exception(DataAccessDPIv1Exception.GET_ERROR,e);
		}
		return returnValue;
	}

	public void remove(T persistenceInstance) throws DataAccessDPIv1Exception{
		try{
		
			this.getSession().setFlushMode(FlushMode.COMMIT);			
			getHibernateTemplate().delete(persistenceInstance);
			this.getSession().flush();
		}catch (DataAccessException e) {
			e.printStackTrace();
			throw new DataAccessDPIv1Exception(DataAccessDPIv1Exception.DELETE_ERROR,e);
		}		
	}
	
	public void save(T newInstance) throws DataAccessDPIv1Exception {
		try{			
			getHibernateTemplate().save(newInstance);
			
		}catch (DataAccessException e) {
			e.printStackTrace();
			throw new DataAccessDPIv1Exception(DataAccessDPIv1Exception.SAVE_ERROR,e);
		}
	}
	
	public void update(T persistenceInstance)throws RuntimeException{
		try{	
			getHibernateTemplate().update(persistenceInstance);
		
		}catch (DataAccessException e) {
			e.printStackTrace();
			throw new DataAccessDPIv1Exception(DataAccessDPIv1Exception.UPDATE_ERROR,e);
		}		
	}
	//no va
	@SuppressWarnings("unchecked")
	public List<T> getByCriterio(DetachedCriteria criteria) {
		return getHibernateTemplate().findByCriteria(criteria);
	}

	public void removeAll(List<T> objects) throws DataAccessDPIv1Exception {
		try{	
			getHibernateTemplate().deleteAll(objects);
		}catch (DataAccessException e) {
			e.printStackTrace();
			throw new DataAccessDPIv1Exception(DataAccessDPIv1Exception.DELETE_ERROR,e);
		}		
	}

	public void saveOrUpdateAll(List<T> objects) throws DataAccessDPIv1Exception {
		try{	
			getHibernateTemplate().saveOrUpdateAll(objects);
		}catch (DataAccessException e) {
			e.printStackTrace();
			throw new DataAccessDPIv1Exception(DataAccessDPIv1Exception.DELETE_ERROR,e);
		}		
	}

	@SuppressWarnings("unchecked")
	public List<T> getListbyQuery(String query) throws DataAccessException {
		try{			
			return getHibernateTemplate().find(query);
		
		}catch (DataAccessException e){
			e.printStackTrace();
			throw new DataAccessDPIv1Exception(DataAccessDPIv1Exception.DELETE_ERROR,e);
		}
	}

}
