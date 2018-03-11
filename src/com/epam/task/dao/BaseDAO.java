package com.epam.task.dao;

import java.util.List;

import com.epam.task.entity.AbstractEntity;

public interface BaseDAO<T extends AbstractEntity> {
	
	/**
	 * Find all children of AbstractEntity.
	 * 
	 * @return the list of children of AbstractEntity
	 */
	List<T> findAll();

	/**
	 * Find child of AbstractEntity by id.
	 * 
	 * @param id
	 *            is an AbstractEntity's id
	 * @return the child of AbstractEntity.
	 */
	T findByID(int id);

	/**
	 * Save child of AbstractEntity.
	 * 
	 * @param entity
	 *            is an object of AbstractEntity class.
	 */
	void save(T entity);
	/**
	 * Delete child of AbstractEntity by id.
	 * 
	 * @param id
	 *            is an AbstractEntity's id
	 */
	void delete(int id);

	/**
	 * Update child of AbstractEntity.
	 * 
	 * @param entity
	 *            is an object of AbstractEntity class.
	 */
	void update(T entity);
}