package com.epam.task.entity;

import java.util.List;
import java.util.Objects;

/**
 * The Class FileStructure describes the structure of a concrete directory.
 */
public class FileStructure {

	private String fileName;

	private List<String> path;

	private List<SimpleFile> folders;

	private List<SimpleFile> files;

	public FileStructure() {
	}

	public FileStructure(final String fileName, final List<String> path, final List<SimpleFile> folders,
			final List<SimpleFile> files) {
		this.fileName = fileName;
		this.path = path;
		this.folders = folders;
		this.files = files;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}

	public List<String> getPath() {
		return path;
	}

	public void setPath(final List<String> path) {
		this.path = path;
	}

	public List<SimpleFile> getFolders() {
		return folders;
	}

	public void setFolders(final List<SimpleFile> folders) {
		this.folders = folders;
	}

	public List<SimpleFile> getFiles() {
		return files;
	}

	public void setFiles(final List<SimpleFile> files) {
		this.files = files;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fileName, path, folders, files);
	}

	@Override
	public boolean equals(Object obj) {
		return Objects.equals(this, obj);
	}

}
