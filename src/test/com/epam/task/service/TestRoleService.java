package test.com.epam.task.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.epam.task.dao.RoleDAO;
import com.epam.task.entity.Role;
import com.epam.task.service.impl.RoleServiceImpl;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(MockitoJUnitRunner.class)
public class TestRoleService {
	@InjectMocks
	private RoleServiceImpl roleService;
	@Mock
	private RoleDAO roleDAO;

	private Role expectedRole = new Role();
	private static final String ROLE_NAME = "ROLE_USER";

	@Test
	public void testFindByID() {
		when(roleDAO.findRoleByName(ROLE_NAME)).thenReturn(expectedRole);
		Role retrivedRole = roleService.findRoleByName(ROLE_NAME);
		assertEquals(expectedRole, retrivedRole);
	}

}
