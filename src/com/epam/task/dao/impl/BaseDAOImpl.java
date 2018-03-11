package com.epam.task.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.task.dao.BaseDAO;
import com.epam.task.entity.AbstractEntity;

/**
 * The Class BaseDAOImpl.
 *
 * @param <T>
 *            the generic type
 */
public class BaseDAOImpl<T extends AbstractEntity> implements BaseDAO<T> {

	/** The Constant ROLE_FIELD_NAME. */
	public static final String ROLE_FIELD_NAME = "role";

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Gets the session.
	 * 
	 * @return the session
	 */
	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public List<T> findAll() {
		final Criteria criteria = createEntityCriteria();
		return (List<T>) criteria.list();
	}

	@Override
	public T findByID(final int id) {
		return (T) getSession().get(getPersistentClass(), (Serializable) id);
	}

	@Override
	public void save(final T entity) {
		getSession().persist(entity);
	}

	@Override
	public void update(final T entity) {
		getSession().update(entity);
	}

	@Override
	public void delete(final int id) {
		final T entity = findByID(id);
		getSession().delete(entity);
	}

	/**
	 * Gets the persistent class.
	 * 
	 * @return the persistent class
	 */
	private Class getPersistentClass() {
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * Creates the entity criteria.
	 * 
	 * @return the criteria
	 */
	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(getPersistentClass());
	}
}
