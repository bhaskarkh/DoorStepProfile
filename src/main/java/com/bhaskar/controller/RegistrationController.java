package com.bhaskar.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhaskar.dto.UserRegistrationDBDTO;
import com.bhaskar.services.RegistrationDelegate;

@RestController
@RequestMapping("/registration")

public class RegistrationController {
	
	@Autowired
	RegistrationDelegate registrationDelegate;
	
	static final Logger log=LoggerFactory.getLogger(RegistrationController.class);
	

	
	@PostMapping("/save-user")
	public ResponseEntity<UserRegistrationDBDTO> saveUser(@RequestBody UserRegistrationDBDTO userRegistrationDBDTO)
	{
		
		
		log.info("userRegistrationDBDTO in controller= "+userRegistrationDBDTO);
		
		
		
		
		return new ResponseEntity<>(registrationDelegate.saveUser(userRegistrationDBDTO),HttpStatus.CREATED);
	}


}
