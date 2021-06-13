package com.bhaskar.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


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
