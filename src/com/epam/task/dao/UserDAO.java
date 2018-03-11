package com.epam.task.dao;

import java.util.List;

import com.epam.task.entity.User;

/**
 * The Interface UserDAO.
 */
public interface UserDAO extends BaseDAO<User> {
	/**
	 * Find all Users with role USER.
	 * 
	 * @return the list of USERs
	 */
	public List<User> findOnlyUsers();

	/**
	 * Find all Users with role ADMIN.
	 * 
	 * @return the list of ADMINs
	 */
	public List<User> findOnlyAdmins();
}
