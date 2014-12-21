package com.drop.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T extends Serializable> {

	public T getEntity(final long id);
	
	public T loadEntity(final long id);

	public List<T> findAll();

	public void create(final T entity);

	public T update(final T entity);

	public void delete(final T entity);

	public void deleteById(final long entityId);
	
	public T getByProperty(final String propertyName, final String value);
	
	public void saveOrUpdate(final T entity);
	
	public List<T> getFirstNEntities(int startIndex, int noOfEntities);
}
