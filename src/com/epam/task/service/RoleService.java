package com.epam.task.service;

import com.epam.task.entity.Role;

/**
 * The Interface RoleService.
 */
public interface RoleService {

	/**
	 * Finds role using name of role.
	 * 
	 * @param name
	 *            is a name of role.
	 * @return the Role.
	 */
	Role findRoleByName(String name);
}
