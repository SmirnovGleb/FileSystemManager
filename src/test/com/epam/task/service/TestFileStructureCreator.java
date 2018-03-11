package test.com.epam.task.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import org.mockito.junit.MockitoJUnitRunner;

import com.epam.task.entity.FileStructure;
import com.epam.task.entity.SimpleFile;
import com.epam.task.exception.FileSystemManagerDataException;
import com.epam.task.filesystem.FileSystemManager;
import com.epam.task.service.impl.FileStructureCreatorImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestFileStructureCreator {

	@InjectMocks
	private FileStructureCreatorImpl fileStructureCreator;

	@Mock
	private FileSystemManager fileSystemManager;

	private static final String CORRECT_PATH = "Correct Path";
	private static final String WRONG_PATH = "Wrong Path";

	private FileStructure expectedFileStructure;
	private File folder;
	private File nestedFolder;
	private File nestedFile;

	@Before
	public void init() throws IOException {
		String fileName = "unittest";
		List<String> path = new ArrayList<>();
		path.add("C:");
		path.add("Temp");
		path.add("unittest");
		List<SimpleFile> folders = new ArrayList<>();
		String[] FolderPath = { "C:", "Temp", "unittest", "app" };
		folders.add(new SimpleFile("app", Arrays.asList(FolderPath)));
		String[] FilePath = { "C:", "Temp", "unittest", "testfile" };
		List<SimpleFile> files = new ArrayList<>();
		files.add(new SimpleFile("testfile", Arrays.asList(FilePath)));
		expectedFileStructure = new FileStructure(fileName, path, folders, files);
		folder = new File("C:\\Temp\\unittest");
		folder.mkdir();
		nestedFolder = new File("C:\\Temp\\unittest\\app");
		nestedFolder.mkdir();
		nestedFile = new File("C:\\Temp\\unittest\\testfile");
		nestedFile.createNewFile();
	}

	@Test
	public void testGetFileStructureWithPath() throws FileSystemManagerDataException {
		when(fileSystemManager.getFileByPath("C:\\Temp\\unittest")).thenReturn(folder);
		FileStructure retrivedFileStructure = fileStructureCreator.getFileStructure("C:\\Temp\\unittest");
		Assert.assertEquals(expectedFileStructure, retrivedFileStructure);
	}

	@Test
	public void testGetRootFileStructure() throws FileSystemManagerDataException {
		String fileName = "Root Directory";
		List<String> path = new ArrayList<>();
		path.add("Desktop");
		List<SimpleFile> folders = new ArrayList<>();
		String[] FilePath = { "C:" };
		folders.add(new SimpleFile("C:\\", Arrays.asList(FilePath)));
		List<SimpleFile> files = new ArrayList<>();
		expectedFileStructure = new FileStructure(fileName, path, folders, files);

		File[] rootDir = { new File("C:") };
		when(fileSystemManager.getRootDirectory()).thenReturn(rootDir);
		FileStructure retrivedFileStructure = fileStructureCreator.getRootFileStructure();
		Assert.assertEquals(expectedFileStructure, retrivedFileStructure);
	}

	@Test
	public void testDelete() throws FileSystemManagerDataException {
		fileStructureCreator.deleteFile(CORRECT_PATH);
		verify(fileSystemManager, times(1)).deleteFile(CORRECT_PATH);
	}

	@Test
	public void testCreateNewFolder() throws FileSystemManagerDataException {
		fileStructureCreator.createNewFolder(CORRECT_PATH);
		verify(fileSystemManager, times(1)).createFolder(CORRECT_PATH);
	}

	@Test
	public void testCreateNewFile() throws FileSystemManagerDataException {
		fileStructureCreator.createNewFile(CORRECT_PATH);
		verify(fileSystemManager, times(1)).createNewFile(CORRECT_PATH);
	}

	@Test
	public void testGetFileStructureWithPathAndWrongData() throws FileSystemManagerDataException {
		when(fileSystemManager.getFileByPath(WRONG_PATH)).thenReturn(null);
		FileStructure retrivedFileStructure = fileStructureCreator.getFileStructure(WRONG_PATH);
		Assert.assertNull(retrivedFileStructure);
	}

	@After
	public void deleteTempFile() throws IOException {
		nestedFile.delete();
		nestedFolder.delete();
		folder.delete();
	}

}
