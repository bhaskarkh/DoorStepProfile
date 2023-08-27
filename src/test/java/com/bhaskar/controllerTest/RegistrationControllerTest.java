package com.bhaskar.controllerTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.bhaskar.controller.RegistrationController;
import com.bhaskar.servicesimpl.RegistrationService;
import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import com.bhaskar.dto.UserRegistrationDBDTO;
import com.bhaskar.repository.RegistrationRepo;
import com.bhaskar.services.RegistrationDelegate;

@SpringBootTest()
public class RegistrationControllerTest {
    private MockMvc mockMvc;
    @Mock
    private RegistrationService userService;
    @InjectMocks
    private RegistrationController userController;
    @Value("${server.port}")
    int port;
    private final String SAVE_USER_URL="http://localhost:8001/registration/save-user";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }
    @Test
    public void testSaveUserController() {

        UserRegistrationDBDTO userRegistrationDBDTO = new UserRegistrationDBDTO();
        userRegistrationDBDTO.setUserName("bhaskar");
        userRegistrationDBDTO.setUserEmail("bhaskaremail.com");
        userRegistrationDBDTO.setRmn("1234555");

        HttpEntity<UserRegistrationDBDTO> request = new HttpEntity<>(userRegistrationDBDTO);
        String hostAddress = "";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
            String hostName = InetAddress.getLocalHost().getHostName();
            System.out.println(hostName + ": " + hostAddress);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://" + hostAddress + ":" + String.valueOf(port) + "/registration/save-user";
        ResponseEntity<UserRegistrationDBDTO> userResponseEntity = restTemplate.postForEntity(uri, request, UserRegistrationDBDTO.class);
        assertNotNull(userResponseEntity.getBody().getUserId());
        assertEquals(userResponseEntity.getBody().getUserName(), "bhaskar");
        assertEquals(userResponseEntity.getBody().getUserEmail(), "bhaskaremail.com");
        assertEquals(userResponseEntity.getBody().getRmn(), "1234555");

    }

    @Test
    public void testSaveUser_Success() throws Exception {
        UserRegistrationDBDTO userToSave = new UserRegistrationDBDTO();
        userToSave.setUserName("guddu sharma");
        userToSave.setUserEmail("bhas646@gmail.com");
        userToSave.setRmn("123456780");

        when(userService.saveUser(any(UserRegistrationDBDTO.class))).thenReturn(userToSave);

        mockMvc.perform(post(SAVE_USER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"rmn\": \"123456780\",\n" +
                                "    \"userName\": \"guddu sharma\",\n" +
                                "    \"userEmail\": \"bhas646@gmail.com\",\n" +
                                "    \"userPhoto\": \"https://media.nojoto.com/content/media/29810/2018/04/profile/e6bcbc94adbeda06877366d83b327bc6_29810.jpg\",\n" +
                                "    \"role\": \"admin\",\n" +
                                "    \"isRmnVerified\":true,\n" +
                                "    \"userRegisteredDate\":\"2023-08-07\",\n" +
                                "    \"userStatus\": \"active\",\n" +
                                "    \"addressDTOList\": [{\n" +
                                "        \"name\":\"Topa\",\n" +
                                "        \"house_no\":\"73\",\n" +
                                "        \"area_colony\":\"3rd lane\",\n" +
                                "        \"landmark\":\"dav topa\",\n" +
                                "        \"city\":\"ramgarh\",\n" +
                                "        \"state\":\"jharkhand\",\n" +
                                "        \"pincode\":825330,\n" +
                                "        \"deliveryMobileNo\":\"123366\",\n" +
                                "        \"typeOfAddress\":\"Home\",\n" +
                                "        \"isPrimaryAddress\":false\n" +
                                "\n" +
                                "\n" +
                                "    },{\n" +
                                "        \"name\":\"soso\",\n" +
                                "        \"house_no\":\"83\",\n" +
                                "        \"area_colony\":\"maday tola\",\n" +
                                "        \"landmark\":\"1 km ahead of toll gate\",\n" +
                                "        \"city\":\"ramgarh\",\n" +
                                "        \"state\":\"jharkhand\",\n" +
                                "        \"pincode\":829110,\n" +
                                "        \"deliveryMobileNo\":\"8523697412\",\n" +
                                "        \"typeOfAddress\":\"Home\",\n" +
                                "        \"isPrimaryAddress\":true\n" +
                                "    }]\n" +
                                "}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userName").value("guddu sharma"))
                .andExpect(jsonPath("$.userEmail").value("bhas646@gmail.com"));

        verify(userService, times(1)).saveUser(any(UserRegistrationDBDTO.class));
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testSaveUser_ValidationFailure() throws Exception {
        // Simulate validation failure by sending invalid JSON data
        mockMvc.perform(post(SAVE_USER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"invalid_field\":\"value\"}"))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(userService);
    }

    @Test
    public void testSaveUser_ServiceException() throws Exception {
        when(userService.saveUser(any(UserRegistrationDBDTO.class))).thenThrow(new ServiceException("Error saving user"));

        mockMvc.perform(post(SAVE_USER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"testuser\",\"email\":\"test@example.com\"}"))
                .andExpect(status().isInternalServerError());

        verify(userService, times(1)).saveUser(any(UserRegistrationDBDTO.class));
    }
}
