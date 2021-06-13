package com.bhaskar.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bhaskar.dto.UserRegistrationDBDTO;
import com.bhaskar.repository.RegistrationRepo;
import com.bhaskar.services.RegistrationDelegate;

@SpringBootTest
public class UserRegistrationServiceTest {

	@Mock
	private RegistrationRepo registrationRepo;

	@Autowired
	RegistrationDelegate registrationDelegate;
	
	@Test
	public void testSaveUserService()
	{
		System.out.println("inside testSaveUser");
		UserRegistrationDBDTO us=new UserRegistrationDBDTO();
		us.setUserId(1);
		us.setUserName("testName");
		
	when(registrationRepo.save(Mockito.any(UserRegistrationDBDTO.class))).thenReturn(us);
	
	UserRegistrationDBDTO recievedUserRegistrationDBDTO=registrationDelegate.saveUser(us);
	assertEquals("testName",recievedUserRegistrationDBDTO.getUserName());
	assertEquals(1, recievedUserRegistrationDBDTO.getUserId());
		
		
		
	}
}
