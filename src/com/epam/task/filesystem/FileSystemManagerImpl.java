package com.epam.task.filesystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.epam.task.exception.FileSystemManagerDataException;

/**
 * The Class FileSystemManagerImpl works with file system of environment it's
 * hosted on.
 */
@Service
public class FileSystemManagerImpl implements FileSystemManager {

	/** The Constant LOGGER. */
	private final static Logger LOGGER = LoggerFactory.getLogger(FileSystemManagerImpl.class);

	/**
	 * Gets array of file system discs.
	 * 
	 * @return array of available file system disk
	 * @throws FileSystemManagerDataException
	 */
	public File[] getRootDirectory() throws FileSystemManagerDataException {
		final File[] currentDirectories = File.listRoots();
		if (currentDirectories.length < 1) {
			throw new FileSystemManagerDataException();
		}
		return currentDirectories;
	}

	/**
	 * Gets file by path
	 * 
	 * @param path
	 *            is the absolute path
	 * @return the file by path
	 * @throws FileSystemManagerDataException
	 */
	public File getFileByPath(final String path) throws FileSystemManagerDataException {
		final File currentFile = new File(path);
		if (!currentFile.exists()) {
			throw new FileSystemManagerDataException();
		}
		return currentFile;
	}

	/**
	 * Creates folder by given name and absolutePath
	 * 
	 * @param absolutePath
	 *            is the absolute path to file
	 * @throws FileSystemManagerDataException
	 */
	public void createFolder(final String absolutePath) throws FileSystemManagerDataException {
		final File newFolder = new File(absolutePath);
		if (!newFolder.mkdir())
			throw new FileSystemManagerDataException("Folder isn't created, the folder name is already taken");
		if (!newFolder.exists()) {
			throw new FileSystemManagerDataException("Folder isn't created");
		}
	}

	/**
	 * Delete file or folder by given absolute Path
	 * 
	 * @param absolutePath
	 *            is the absolute path to file
	 * @throws FileSystemManagerDataException
	 */
	public void deleteFile(final String absolutePath) throws FileSystemManagerDataException {
		final Path currentPath = Paths.get(absolutePath);
		try {
			Files.delete(currentPath);
		} catch (IOException e) {
			LOGGER.error("an error occurred while trying to delete the file", e);
			throw new FileSystemManagerDataException(e);
		}
	}

	/**
	 * Creates file by given name and absolutePath
	 * 
	 * @param absolutePath
	 *            is the absolute path to file
	 * @throws FileSystemManagerDataException
	 */
	public void createNewFile(final String absolutePath) throws FileSystemManagerDataException {
		try {
			final File newFile = new File(absolutePath);
			if (!newFile.createNewFile())
				throw new FileSystemManagerDataException("The named file already exists");
		} catch (IOException e) {
			LOGGER.error("an error occurred while trying to create the file", e);
			throw new FileSystemManagerDataException(e);
		}
	}
}
