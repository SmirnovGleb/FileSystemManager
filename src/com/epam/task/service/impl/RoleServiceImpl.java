package com.epam.task.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.task.dao.RoleDAO;
import com.epam.task.entity.Role;
import com.epam.task.service.RoleService;

/**
 * The Class RoleServiceImpl.
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	/** The Role Service layer . */
	@Autowired
	private RoleDAO roleDAO;

	@Override
	public Role findRoleByName(String name) {
		return roleDAO.findRoleByName(name);
	}

}
