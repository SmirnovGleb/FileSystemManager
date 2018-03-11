package com.epam.task.exception;

public class FileSystemManagerDataException extends Exception {

	private static final long serialVersionUID = 3251241077391769169L;

	public FileSystemManagerDataException() {
	}

	public FileSystemManagerDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileSystemManagerDataException(String message) {
		super(message);
	}

	public FileSystemManagerDataException(Throwable cause) {
		super(cause);
	}

}
