package com.drop.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.drop.dao.IGenericDao;

@Repository("GenricDao")
public class GenericDaoImpl<T extends Serializable> implements IGenericDao<T> {

	private Class<T> clazz;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public GenericDaoImpl() {
		
	}
	
	public GenericDaoImpl(Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}
	
	public final void setClazz(Class<T> clazzToSet){
		this.clazz = clazzToSet;
	}
	
	protected final Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
		
	@Override
	@SuppressWarnings("unchecked")
	public T getEntity(long id){
		return (T) getCurrentSession().get(clazz, id );
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public T loadEntity(long id){
		return (T) getCurrentSession().load(clazz, id );
	}
		
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll(){
		return getCurrentSession().createQuery("from " + clazz.getName()).list();
	}
		
	@Override
	public void create(T entity){
		getCurrentSession().persist(entity);
	}
		
	@Override
	@SuppressWarnings("unchecked")
	public T update(T entity){
		return (T)getCurrentSession().merge(entity);
	}
	
	@Override
	public void delete(T entity){
		getCurrentSession().delete(entity);
	}
		
	@Override
	public void deleteById(long entityId){
		T entity = loadEntity(entityId);
		delete(entity);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public T getByProperty(String property, String value) {
		Session session = getCurrentSession();
		T entity = null;
		String queryString = "from " + clazz.getName() + " where " + property + " = :param";
		Query query = session.createQuery(queryString);
		query.setParameter("param", value);
		entity = (T) query.uniqueResult();
		return entity;
	}

	@Override
	public void saveOrUpdate(T entity) {
		getCurrentSession().saveOrUpdate(entity);
	}
	
	@Override
	public List<T> getFirstNEntities(int startIndex, int noOfEntities) {
		Session session = getCurrentSession();
		String queryString = "from " + clazz.getName();
		Query query = session.createQuery(queryString);
		query.setFirstResult(startIndex);
		query.setMaxResults(noOfEntities);
		return query.list();
	}
}
