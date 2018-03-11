package com.epam.task.validator;

public interface RegistrationDataValidator<T1,T2> {
	boolean validate(T1 username, T2 password);
}
