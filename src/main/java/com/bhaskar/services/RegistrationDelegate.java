package com.bhaskar.services;

import com.bhaskar.dto.UserRegistrationDBDTO;

import java.util.List;

public interface RegistrationDelegate {
	
	UserRegistrationDBDTO saveUser(UserRegistrationDBDTO userRegistrationDBDTO);
	List<UserRegistrationDBDTO> getAllUser();

}
