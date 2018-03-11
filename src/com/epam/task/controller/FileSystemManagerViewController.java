package com.epam.task.controller;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.epam.task.entity.Role;
import com.epam.task.entity.User;
import com.epam.task.service.RoleService;
import com.epam.task.service.UserService;
import com.epam.task.validator.RegistrationDataValidator;

/**
 * The Class FileSystemManagerViewController returns views.
 */
@Controller
public class FileSystemManagerViewController {

	private UserService userService;

	private RoleService roleService;

	private RegistrationDataValidator<String, String> validator;

	private static final boolean PERMITTED_USER = true;

	private static final String ATTRIBUTE_ERROR_INFO = "errorinfo";

	@Autowired
	public FileSystemManagerViewController(UserService userService, RoleService roleService,
			RegistrationDataValidator<String, String> validator) {
		super();
		this.userService = userService;
		this.roleService = roleService;
		this.validator = validator;
	}

	/**
	 * @return the welcome page view.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcomePage() {
		return "welcome";
	}

	/**
	 * @return the login page view.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

	/**
	 * @return the manager page view.
	 */
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public String managerPage() {
		return "manager";
	}

	/**
	 * @return the registration page view.
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String loginRegistration() {
		return "registration";
	}

	/**
	 * Register new user
	 * 
	 * @param username
	 *            is the name of new user
	 * @param password
	 *            is the password of new user
	 * @return login page view
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String saveNewUser(final ModelMap model, @RequestParam final String username,
			@RequestParam final String password) {
		if (!validator.validate(username, password)) {
			model.addAttribute(ATTRIBUTE_ERROR_INFO, "Incorrect user name or password");
			return "registration";
		}
		Role roleUser = roleService.findRoleByName(Role.ROLE_USER);
		User user = new User(username, password, PERMITTED_USER, roleUser);
		try {
			userService.save(user);
		} catch (ConstraintViolationException e) {
			model.addAttribute(ATTRIBUTE_ERROR_INFO, "User name is already taken");
			return "registration";
		}
		return "login";
	}

	/**
	 * Sends user list and admin list to UI
	 * 
	 * @return the changerole page view.
	 */
	@RequestMapping(value = "/changerole", method = RequestMethod.GET)
	@Secured("ROLE_ADMIN")
	public String getListUsers(final ModelMap model) {
		addUsersAndAdminsToModel(model);
		return "changerole";
	}

	/**
	 * Change User role and sends user list and admin list to UI
	 * 
	 * @return the changerole page view.
	 */
	@RequestMapping(value = "/change", method = RequestMethod.POST)
	@Secured("ROLE_ADMIN")
	public String changeUserRole(final ModelMap model, @RequestParam(name = "userid") final int userId,
			@RequestParam(name = "rolename") final String roleName) {
		final User user = userService.findByID(userId);
		final Role role = roleService.findRoleByName(roleName);
		user.setRole(role);
		userService.update(user);
		addUsersAndAdminsToModel(model);
		return "changerole";
	}

	private void addUsersAndAdminsToModel(ModelMap model) {
		model.addAttribute("users", userService.findOnlyUsers());
		model.addAttribute("admins", userService.findOnlyAdmins());
	}
}
