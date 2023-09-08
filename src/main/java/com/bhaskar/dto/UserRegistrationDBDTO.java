package com.bhaskar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="user_registration")
public class UserRegistrationDBDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int userId;
	private String rmn;
    private String userName;
    private String userEmail;
    private String userPhoto;
    private String role;
    private boolean isRmnVerified;
    private String  userRegisteredDate;
    private String userStatus;
    
    @OneToMany(mappedBy = "userRegistrationDBDTO",cascade = CascadeType.ALL)
    private List<AddressDTO> addressDTOList;
}
