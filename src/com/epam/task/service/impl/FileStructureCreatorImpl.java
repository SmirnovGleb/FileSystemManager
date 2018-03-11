package com.epam.task.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.task.entity.FileStructure;
import com.epam.task.entity.SimpleFile;
import com.epam.task.exception.FileSystemManagerDataException;
import com.epam.task.filesystem.FileSystemManager;
import com.epam.task.service.FileStructureCreator;
import static com.epam.task.util.PathConverter.*;

/**
 * The Class FileStructureCreatorImpl creates FileStructure.
 */
@Service
public class FileStructureCreatorImpl implements FileStructureCreator {

	/** The file system manager. */
	@Autowired
	private FileSystemManager fileSystemManager;

	/** The Constant ROOT_DIRECTORY_NAME. */
	private static final String ROOT_DIRECTORY_NAME = "Root Directory";

	@Override
	public FileStructure getFileStructure(final String absolutePath) throws FileSystemManagerDataException {
		final File currentFile = fileSystemManager.getFileByPath(absolutePath);
		return createFileStructure(currentFile);
	}

	@Override
	public FileStructure getRootFileStructure() throws FileSystemManagerDataException {
		FileStructure currentFileStructure = null;

		final List<SimpleFile> currentFileList = new ArrayList<>();
		final List<SimpleFile> currentDirectoryList = new ArrayList<>();

		for (File rootDirictories : fileSystemManager.getRootDirectory()) {
			currentDirectoryList.add(
					new SimpleFile(rootDirictories.getAbsolutePath(), stringToList(rootDirictories.getAbsolutePath())));
			currentFileStructure = new FileStructure(ROOT_DIRECTORY_NAME,
					stringToList(FileSystemView.getFileSystemView().getHomeDirectory().getName()), currentDirectoryList,
					currentFileList);
		}

		return currentFileStructure;
	}

	@Override
	public void createNewFolder(final String absolutePath) throws FileSystemManagerDataException {
		fileSystemManager.createFolder(absolutePath);
	}

	@Override
	public void createNewFile(final String absolutePath) throws FileSystemManagerDataException {
		fileSystemManager.createNewFile(absolutePath);
	}

	@Override
	public void deleteFile(final String absolutePath) throws FileSystemManagerDataException {
		fileSystemManager.deleteFile(absolutePath);
	}

	private FileStructure createFileStructure(final File currentFile) {
		if (currentFile == null) {
			return null;
		}
		final File[] files = currentFile.listFiles();

		final List<SimpleFile> currentFileList = new ArrayList<>();
		final List<SimpleFile> currentDirectoryList = new ArrayList<>();

		if (currentFile.isDirectory()) {
			for (File file : files) {
				if (file.isFile()) {
					currentFileList.add(new SimpleFile(file.getName(), stringToList(file.getAbsolutePath())));
				}
				if (file.isDirectory()) {
					currentDirectoryList.add(new SimpleFile(file.getName(), stringToList(file.getAbsolutePath())));
				}
			}
		}
		final FileStructure currentFileStructure = new FileStructure(currentFile.getName(),
				stringToList(currentFile.getAbsolutePath()), currentDirectoryList, currentFileList);
		return currentFileStructure;
	}
}
