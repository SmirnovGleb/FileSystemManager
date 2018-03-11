package com.epam.task.validator.impl;

import org.springframework.stereotype.Component;

import com.epam.task.validator.InputDataValidator;

@Component
public class InputPathValidator implements InputDataValidator<String> {
	/** The Constant REGEX_FILE_NAME_VALIDATION. */
	private static final String REGEX_FILE_NAME_VALIDATION = "^[^<:;,?\\\"*|/]+$";

	public boolean validate(final String path) {
		if (path == null) return false;
		final String[] pathArray = path.split("<");
		if(pathArray.length==1) {
			return true;
		}
		final String fileName = pathArray[pathArray.length - 1];
		return fileName.matches(REGEX_FILE_NAME_VALIDATION);
	}

}