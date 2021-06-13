package com.bhaskar.services;

import com.bhaskar.dto.UserRegistrationDBDTO;

public interface RegistrationDelegate {
	
	UserRegistrationDBDTO saveUser(UserRegistrationDBDTO userRegistrationDBDTO);

}
