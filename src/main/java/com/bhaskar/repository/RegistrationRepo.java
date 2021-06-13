package com.bhaskar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhaskar.dto.UserRegistrationDBDTO;
import com.bhaskar.dto.UserRegistrationDTO;

@Repository
public interface RegistrationRepo extends JpaRepository<UserRegistrationDBDTO, Integer> {

}
