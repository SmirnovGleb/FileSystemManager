package com.epam.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.task.entity.FileStructure;
import com.epam.task.exception.FileSystemManagerDataException;
import com.epam.task.service.FileStructureCreator;
import static com.epam.task.util.PathConverter.*;
import com.epam.task.validator.InputDataValidator;

/**
 * The Class FileSystemManagerDataController returns data based on the user
 * query
 */
@Controller
public class FileSystemManagerDataController {

	/** The file structure creator. */
	private FileStructureCreator fileStructureCreator;

	/** The file path validator. */
	private InputDataValidator<String> validator;

	@Autowired
	public FileSystemManagerDataController(FileStructureCreator fileStructureCreator,
			InputDataValidator<String> validator) {
		super();
		this.fileStructureCreator = fileStructureCreator;
		this.validator = validator;
	}

	/**
	 * @return the root directory structure.
	 */
	@RequestMapping(value = "/folder", method = RequestMethod.GET)
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ResponseEntity<FileStructure> getRootDirectory() {
		try {
			FileStructure fileStructure = fileStructureCreator.getRootFileStructure();
			return new ResponseEntity<FileStructure>(fileStructure, HttpStatus.OK);
		} catch (FileSystemManagerDataException e) {
			return new ResponseEntity<FileStructure>(HttpStatus.I_AM_A_TEAPOT);
		}
	}

	/**
	 * @param String
	 *            the path which is contains absolute path to the object.
	 * @return the directory structure.
	 */
	@RequestMapping(value = "/folder/{path}", method = RequestMethod.GET)
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ResponseEntity<?> getDirectoryStructure(@PathVariable(name = "path", required = true) final String path) {
		if (!validator.validate(path)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			final String stringPath = convert(path);
			FileStructure fileStructure = fileStructureCreator.getFileStructure(stringPath);
			return new ResponseEntity<FileStructure>(fileStructure, HttpStatus.OK);
		} catch (FileSystemManagerDataException e) {
			return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
		}
	}

	/**
	 * Deletes file.
	 * 
	 * @param String
	 *            the path which is contains absolute path to the object.
	 * @return the FileStructure of parent's directory
	 */
	@RequestMapping(value = "/folder/{path}", method = RequestMethod.DELETE)
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> deleteFile(@PathVariable(name = "path", required = true) final String path) {
		if (!validator.validate(path)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			fileStructureCreator.deleteFile(convert(path));
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (FileSystemManagerDataException e) {
			return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
		}
	}

	/**
	 * Creates new folder.
	 * 
	 * @param String
	 *            the path which is contains absolute path to the object.
	 * @return the FileStructure of parent's directory
	 */
	@RequestMapping(value = "/folder/{path}", method = RequestMethod.PUT)
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> createFolder(@PathVariable(name = "path", required = true) final String path) {
		if (!validator.validate(path)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			final String convertedPath = convert(path);
			fileStructureCreator.createNewFolder(convertedPath);
		} catch (FileSystemManagerDataException e) {
			return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * Creates new file.
	 *
	 * @param String
	 *            the path which is contains absolute path to the object.
	 * @return the FileStructure of parent's directory
	 */
	@RequestMapping(value = "/file/{path}", method = RequestMethod.PUT)
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> createFile(@PathVariable(name = "path", required = true) final String path) {
		if (!validator.validate(path)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			final String convertedPath = convert(path);
			fileStructureCreator.createNewFile(convertedPath);
		} catch (FileSystemManagerDataException e) {
			return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
