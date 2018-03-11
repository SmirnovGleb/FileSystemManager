package test.com.epam.task.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.epam.task.dao.UserDAO;
import com.epam.task.entity.User;
import com.epam.task.service.impl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestUserService {
	@InjectMocks
	private UserServiceImpl userService;
	@Mock
	private UserDAO userDAO;

	private List<User> expectedList;

	private User expectedUser;

	private static final int USER_ID = 1;

	@Before
	public void init() {
		expectedUser = new User();
		expectedList = new ArrayList<>();
		expectedList.add(expectedUser);
	}

	@Test
	public void testFindAll() {
		when(userDAO.findAll()).thenReturn(expectedList);
		List<User> retrivedList = userService.findAll();
		assertEquals(expectedList, retrivedList);
	}

	@Test
	public void testFindByID() {
		when(userDAO.findByID(USER_ID)).thenReturn(expectedUser);
		User retrivedUser = userService.findByID(USER_ID);
		assertEquals(expectedUser, retrivedUser);
	}

	@Test
	public void testSave() {
		userService.save(expectedUser);
		verify(userDAO, times(1)).save(expectedUser);
	}

	@Test
	public void testDelete() {
		userService.delete(USER_ID);
		verify(userDAO, times(1)).delete(USER_ID);
	}

	@Test
	public void testUpdate() {
		userService.update(expectedUser);
		verify(userDAO, times(1)).update(expectedUser);
	}

	@Test
	public void testFindOnlyUsers() {
		when(userDAO.findOnlyUsers()).thenReturn(expectedList);
		List<User> retrivedList = userService.findOnlyUsers();
		assertEquals(expectedList, retrivedList);
	}

	@Test
	public void testFindOnlyAdmins() {
		when(userDAO.findOnlyAdmins()).thenReturn(expectedList);
		List<User> retrivedList = userService.findOnlyAdmins();
		assertEquals(expectedList, retrivedList);
	}
}
