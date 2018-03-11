package com.epam.task.validator;

public interface InputDataValidator<T> {
	boolean validate(T t);
}
