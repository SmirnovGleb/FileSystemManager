package com.epam.task.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.task.dao.UserDAO;
import com.epam.task.entity.User;
import com.epam.task.service.UserService;

/**
 * The Class UserServiceImpl.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	/** The user DAO. */
	@Autowired
	private UserDAO userDAO;

	@Override
	public List<User> findAll() {
		return userDAO.findAll();
	}

	@Override
	public User findByID(final int id) {
		return userDAO.findByID(id);
	}

	@Override
	public void save(final User user) {
		userDAO.save(user);
	}

	@Override
	public void delete(final int id) {
		userDAO.delete(id);
	}

	@Override
	public void update(final User user) {
		userDAO.update(user);
	}

	@Override
	public List<User> findOnlyUsers() {
		return userDAO.findOnlyUsers();
	}

	@Override
	public List<User> findOnlyAdmins() {
		return userDAO.findOnlyAdmins();
	}

}
