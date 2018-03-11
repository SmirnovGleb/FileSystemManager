package com.epam.task.entity;

import java.util.List;
import java.util.Objects;

/**
 * The Class SimpleFile contains the absolute path to file and the file name.
 */
public class SimpleFile {

	private String name;
	private List<String> path;

	public SimpleFile() {

	}

	public SimpleFile(String name, List<String> path) {
		super();
		this.name = name;
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getPath() {
		return path;
	}

	public void setPath(List<String> path) {
		this.path = path;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, path);
	}

	@Override
	public boolean equals(Object obj) {
		return Objects.equals(this, obj);
	}

}
