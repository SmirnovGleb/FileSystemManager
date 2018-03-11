package com.epam.task.service;

import com.epam.task.entity.FileStructure;
import com.epam.task.exception.FileSystemManagerDataException;

public interface FileStructureCreator {
	/**
	 * Creates FileStructure from File
	 * 
	 * @param absolutePath
	 *            is the absolute path to the file
	 * @return the directory structure by the absolute path.
	 * @throws FileSystemManagerDataException
	 */
	FileStructure getFileStructure(String absolutePath) throws FileSystemManagerDataException;

	/**
	 * Creates FileStructure from the file array.
	 * 
	 * @return the root directory structure.
	 * @throws FileSystemManagerDataException
	 */
	FileStructure getRootFileStructure() throws FileSystemManagerDataException;

	/**
	 * Creates new folder.
	 * 
	 * @param absolutePath
	 *            is the absolute path to the object.
	 * @throws FileSystemManagerDataException
	 */
	void createNewFolder(String absolutePath) throws FileSystemManagerDataException;

	/**
	 * Creates new file.
	 * 
	 * @param absolutePath
	 *            is the absolute path to the object.
	 * @throws FileSystemManagerDataException
	 */
	void createNewFile(String absolutePath) throws FileSystemManagerDataException;

	/**
	 * Deletes file.
	 * 
	 * @param absolutePath
	 *            is the absolute path to the object.
	 * @throws FileSystemManagerDataException
	 */
	void deleteFile(String absolutePath) throws FileSystemManagerDataException;
}
