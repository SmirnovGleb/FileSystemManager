package test.com.epam.task.controller;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doThrow;

import com.epam.task.controller.FileSystemManagerDataController;
import com.epam.task.entity.FileStructure;
import com.epam.task.entity.SimpleFile;
import com.epam.task.exception.FileSystemManagerDataException;
import com.epam.task.service.FileStructureCreator;
import com.epam.task.validator.InputDataValidator;

@RunWith(MockitoJUnitRunner.class)
public class TestDataController {

	@InjectMocks
	private FileSystemManagerDataController fileSystemManagerDataController;

	@Mock
	private FileStructureCreator fileStructureCreator;

	@Mock
	private InputDataValidator<String> validator;

	private ResponseEntity<FileStructure> expectedResponseEntity;

	private ResponseEntity<FileStructure> expectedResponseEntityWithBadRequestStatus;

	private ResponseEntity<FileStructure> expectedResponseEntityWithImATeapotStatus;

	private FileStructure expectedFileStructure;

	private static final String INCORRECT_PATH = "incorrect path";

	@Before
	public void init() {
		expectedFileStructure = new FileStructure("test directory", new ArrayList<String>(),
				new ArrayList<SimpleFile>(), new ArrayList<SimpleFile>());
		expectedResponseEntity = new ResponseEntity<FileStructure>(expectedFileStructure, HttpStatus.OK);
		expectedResponseEntityWithBadRequestStatus = new ResponseEntity<FileStructure>(HttpStatus.BAD_REQUEST);
		expectedResponseEntityWithImATeapotStatus = new ResponseEntity<FileStructure>(HttpStatus.I_AM_A_TEAPOT);
	}

	@Test
	public void testGetRootDirectory() throws FileSystemManagerDataException {
		when(fileStructureCreator.getRootFileStructure()).thenReturn(expectedFileStructure);
		ResponseEntity<FileStructure> retrivedResponseEntity = fileSystemManagerDataController.getRootDirectory();
		Assert.assertEquals(retrivedResponseEntity, expectedResponseEntity);
	}

	@Test
	public void testGetDirectoryStructure() throws FileSystemManagerDataException {
		when(validator.validate("")).thenReturn(true);
		when(fileStructureCreator.getFileStructure("")).thenReturn(expectedFileStructure);
		String path = "";
		ResponseEntity<?> retrivedResponseEntity = fileSystemManagerDataController.getDirectoryStructure(path);
		Assert.assertEquals(expectedResponseEntity, retrivedResponseEntity);
	}

	@Test
	public void testDeleteFile() throws FileSystemManagerDataException {
		when(validator.validate("test\\dir\\")).thenReturn(true);
		fileSystemManagerDataController.deleteFile("test\\dir\\");
		verify(fileStructureCreator, times(1)).deleteFile("test\\dir\\");
	}

	@Test
	public void testCreateFolder() throws FileSystemManagerDataException {
		when(validator.validate("test\\dir\\")).thenReturn(true);
		fileSystemManagerDataController.createFolder("test\\dir\\");
		verify(fileStructureCreator, times(1)).createNewFolder("test\\dir\\");
	}

	@Test
	public void testCreateFile() throws FileSystemManagerDataException {
		when(validator.validate("test\\dir\\")).thenReturn(true);
		fileSystemManagerDataController.createFile("test\\dir\\");
		verify(fileStructureCreator, times(1)).createNewFile("test\\dir\\");
	}

	@Test
	public void testGetDirectoryStructureWithBadData() {
		ResponseEntity<?> retrivedResponseEntity = fileSystemManagerDataController
				.getDirectoryStructure(INCORRECT_PATH);
		Assert.assertEquals(expectedResponseEntityWithBadRequestStatus, retrivedResponseEntity);
	}

	@Test
	public void testDeleteFileWithBadData() {
		ResponseEntity<?> retrivedResponseEntity = fileSystemManagerDataController.deleteFile(INCORRECT_PATH);
		Assert.assertEquals(expectedResponseEntityWithBadRequestStatus, retrivedResponseEntity);
	}

	@Test
	public void testCreateFolderWithBadData() {
		ResponseEntity<?> retrivedResponseEntity = fileSystemManagerDataController.createFolder(INCORRECT_PATH);
		Assert.assertEquals(expectedResponseEntityWithBadRequestStatus, retrivedResponseEntity);
	}

	@Test
	public void testCreateFileWithBadData() {
		ResponseEntity<?> retrivedResponseEntity = fileSystemManagerDataController.createFile(INCORRECT_PATH);
		Assert.assertEquals(expectedResponseEntityWithBadRequestStatus, retrivedResponseEntity);
	}

	@Test
	public void testGetRootDirectoryNoDirectorys() throws FileSystemManagerDataException {
		when(fileStructureCreator.getRootFileStructure()).thenThrow(new FileSystemManagerDataException());
		ResponseEntity<FileStructure> retrivedResponseEntity = fileSystemManagerDataController.getRootDirectory();
		Assert.assertEquals(expectedResponseEntityWithImATeapotStatus, retrivedResponseEntity);
	}

	@Test
	public void testGetDirectoryStructureExpectedImATeapotStatus() throws FileSystemManagerDataException {
		when(validator.validate(INCORRECT_PATH)).thenReturn(true);
		when(fileStructureCreator.getFileStructure(INCORRECT_PATH)).thenThrow(new FileSystemManagerDataException());
		ResponseEntity<?> retrivedResponseEntity = fileSystemManagerDataController
				.getDirectoryStructure(INCORRECT_PATH);
		Assert.assertEquals(expectedResponseEntityWithImATeapotStatus, retrivedResponseEntity);
	}

	@Test
	public void testDeleteFileExpectedImATeapotStatus() throws FileSystemManagerDataException {
		when(validator.validate(INCORRECT_PATH)).thenReturn(true);
		doThrow(new FileSystemManagerDataException()).when(fileStructureCreator).deleteFile(INCORRECT_PATH);
		ResponseEntity<?> retrivedResponseEntity = fileSystemManagerDataController.deleteFile(INCORRECT_PATH);
		Assert.assertEquals(expectedResponseEntityWithImATeapotStatus, retrivedResponseEntity);
	}

	@Test
	public void testCreateFolderExpectedImATeapotStatus() throws FileSystemManagerDataException {
		when(validator.validate(INCORRECT_PATH)).thenReturn(true);
		doThrow(new FileSystemManagerDataException()).when(fileStructureCreator).createNewFolder(INCORRECT_PATH);
		ResponseEntity<?> retrivedResponseEntity = fileSystemManagerDataController.createFolder(INCORRECT_PATH);
		Assert.assertEquals(expectedResponseEntityWithImATeapotStatus, retrivedResponseEntity);
	}

	@Test
	public void testCreateFileExpectedImATeapotStatus() throws FileSystemManagerDataException {
		when(validator.validate(INCORRECT_PATH)).thenReturn(true);
		doThrow(new FileSystemManagerDataException()).when(fileStructureCreator).createNewFile(INCORRECT_PATH);
		ResponseEntity<?> retrivedResponseEntity = fileSystemManagerDataController.createFile(INCORRECT_PATH);
		Assert.assertEquals(expectedResponseEntityWithImATeapotStatus, retrivedResponseEntity);
	}

}
