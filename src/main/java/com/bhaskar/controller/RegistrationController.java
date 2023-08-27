package com.bhaskar.controller;



import com.bhaskar.servicesimpl.RegistrationService;
import com.sun.xml.bind.v2.TODO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bhaskar.dto.UserRegistrationDBDTO;
import com.bhaskar.services.RegistrationDelegate;

import java.util.List;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
	
	@Autowired
    RegistrationService registrationService;
	
	static final Logger log=LoggerFactory.getLogger(RegistrationController.class);
	

	
	@PostMapping("/save-user")
	public ResponseEntity<UserRegistrationDBDTO> saveUser(@RequestBody UserRegistrationDBDTO userRegistrationDBDTO)
	{
		log.info("userRegistrationDBDTO in controller= "+userRegistrationDBDTO.toString());
		return new ResponseEntity<>(registrationService.saveUser(userRegistrationDBDTO),HttpStatus.CREATED);
	}
	//TODO
	// For Testing Purpose only need to remove once testing is done
	@GetMapping("/get-all-users")
	public ResponseEntity<List<UserRegistrationDBDTO>> getAllUser()
	{
		return new ResponseEntity<>(registrationService.getAllUser(),HttpStatus.ACCEPTED);
	}


}
