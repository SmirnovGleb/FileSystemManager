package com.epam.task.dao;

import com.epam.task.entity.Role;

/**
 * The Interface RoleDAO.
 */
public interface RoleDAO extends BaseDAO<Role> {

	/**
	 * Find role by name.
	 *
	 * @param name
	 *            the name of role.
	 * @return the role
	 */
	Role findRoleByName(String name);
}
