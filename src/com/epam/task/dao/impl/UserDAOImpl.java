package com.epam.task.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.epam.task.dao.UserDAO;
import com.epam.task.entity.Role;
import com.epam.task.entity.User;

/**
 * The Class UserDAOimpl.
 */
@Repository
public class UserDAOImpl extends BaseDAOImpl<User> implements UserDAO {

	public List<User> findOnlyUsers() {
		final Criteria criteria = createEntityCriteria().createCriteria(ROLE_FIELD_NAME);
		criteria.add(Restrictions.eq(ROLE_FIELD_NAME, Role.ROLE_USER));
		return (List<User>) criteria.list();
	}

	@Override
	public List<User> findOnlyAdmins() {
		final Criteria criteria = createEntityCriteria().createCriteria(ROLE_FIELD_NAME);
		criteria.add(Restrictions.eq(ROLE_FIELD_NAME, Role.ROLE_ADMIN));
		return (List<User>) criteria.list();
	}
}
