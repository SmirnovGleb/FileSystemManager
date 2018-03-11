package com.epam.task.service;

import java.util.List;

import com.epam.task.entity.User;

/**
 * The Interface UserService.
 */
public interface UserService {

	/**
	 * Find all Users.
	 * 
	 * @return the list of Users.
	 */
	List<User> findAll();

	/**
	 * Find User by id.
	 * 
	 * @param id
	 *            is id of user.
	 * @return the User.
	 */
	User findByID(int id);

	/**
	 * Save User.
	 * 
	 * @param user
	 *            is an object of class User
	 */
	void save(User user);

	/**
	 * Delete User by id.
	 * 
	 * @param id
	 *            is id of user.
	 */
	void delete(int id);

	/**
	 * Update User.
	 * 
	 * @param user
	 *            is an object of class User
	 */
	void update(User user);

	/**
	 * Find all Users with role USER.
	 * 
	 * @return the list of USERs
	 */
	List<User> findOnlyUsers();

	/**
	 * Find all Users with role ADMIN.
	 * 
	 * @return the list of ADMINs
	 */
	List<User> findOnlyAdmins();
}
