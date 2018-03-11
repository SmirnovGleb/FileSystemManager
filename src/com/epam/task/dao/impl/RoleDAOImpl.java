package com.epam.task.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.epam.task.dao.RoleDAO;
import com.epam.task.entity.Role;

/**
 * The Class RoleDAOImpl.
 */
@Repository
public class RoleDAOImpl extends BaseDAOImpl<Role> implements RoleDAO {

	@Override
	public Role findRoleByName(final String name) {
		final Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq(ROLE_FIELD_NAME, name));
		return (Role) criteria.uniqueResult();
	}
}
