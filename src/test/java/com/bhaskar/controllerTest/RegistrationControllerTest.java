package com.bhaskar.controllerTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.bhaskar.dto.UserRegistrationDBDTO;
import com.bhaskar.repository.RegistrationRepo;
import com.bhaskar.services.RegistrationDelegate;

@SpringBootTest()
public class RegistrationControllerTest {
	
@Mock
private RegistrationRepo registrationRepo;

@Autowired
RegistrationDelegate registrationDelegate;

@Value("${server.port}")
int port;
	
	

	
	
	@Test
	public void testSaveUserController()
	{
		
		UserRegistrationDBDTO userRegistrationDBDTO=new UserRegistrationDBDTO();
		userRegistrationDBDTO.setUserName("bhaskar");
		userRegistrationDBDTO.setUserEmail("bhaskaremail.com");
		userRegistrationDBDTO.setRmn("1234555");
		
		HttpEntity<UserRegistrationDBDTO> request=new HttpEntity<>(userRegistrationDBDTO);
		String hostAddress="";
		try {
			hostAddress=InetAddress.getLocalHost().getHostAddress();
			String hostName=InetAddress.getLocalHost().getHostName();
			System.out.println(hostName+": "+hostAddress);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		RestTemplate restTemplate=new RestTemplate();
		String uri="http://"+ hostAddress+":"+String.valueOf(port) +"/registration/save-user";
		ResponseEntity<UserRegistrationDBDTO> userResponseEntity=restTemplate.postForEntity(uri, request, UserRegistrationDBDTO.class);
		assertNotNull(userResponseEntity.getBody().getUserId());
		assertEquals(userResponseEntity.getBody().getUserName(), "bhaskar");
		assertEquals(userResponseEntity.getBody().getUserEmail(), "bhaskaremail.com");
		assertEquals(userResponseEntity.getBody().getRmn(), "1234555");
		
	}
}
