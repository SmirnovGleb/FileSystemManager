package com.epam.task.validator.impl;

import org.springframework.stereotype.Component;

import com.epam.task.validator.RegistrationDataValidator;

@Component
public class RegistrationDataValidatorImpl implements RegistrationDataValidator<String, String> {

	private static final String USER_NAME_AND_PASSWORD_REGEX = "^(?=.{3,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";

	@Override
	public boolean validate(final String username, final String password) {
		if (username == null || password == null)
			return false;
		if (!username.matches(USER_NAME_AND_PASSWORD_REGEX))
			return false;
		if (!password.matches(USER_NAME_AND_PASSWORD_REGEX))
			return false;
		return true;
	}

}
