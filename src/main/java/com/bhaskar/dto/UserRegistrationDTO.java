package com.bhaskar.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRegistrationDTO{
	UserRegistrationDBDTO userRegistrationDBDTO;
    private List<AddressDTO> addressDTOList;

}
