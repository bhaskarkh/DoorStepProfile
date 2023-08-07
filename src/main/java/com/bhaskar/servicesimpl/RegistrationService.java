package com.bhaskar.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhaskar.dto.UserRegistrationDBDTO;
import com.bhaskar.repository.RegistrationRepo;
import com.bhaskar.services.RegistrationDelegate;

import java.util.List;

@Service
public class RegistrationService implements RegistrationDelegate {

    @Autowired
    RegistrationRepo registrationRepo;

    @Override
    public UserRegistrationDBDTO saveUser(UserRegistrationDBDTO userRegistrationDBDTO) {
        return registrationRepo.save(userRegistrationDBDTO);
    }

    @Override
    public List<UserRegistrationDBDTO> getAllUser() {
        return registrationRepo.findAll();
    }

}
