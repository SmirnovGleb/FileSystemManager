package com.epam.task.filesystem;

import java.io.File;

import com.epam.task.exception.FileSystemManagerDataException;

public interface FileSystemManager {
	File[] getRootDirectory() throws FileSystemManagerDataException;

	File getFileByPath(final String path) throws FileSystemManagerDataException;

	void createFolder(final String absolutePath) throws FileSystemManagerDataException;

	void deleteFile(final String absolutePath) throws FileSystemManagerDataException;

	void createNewFile(final String absolutePath) throws FileSystemManagerDataException;
}
