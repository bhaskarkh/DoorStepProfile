package com.bhaskar.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

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
@Entity
@Table(name="address")
public class AddressDTO  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int address_id;
    private String name;
    private String house_no;
    private String area_colony;
    private String landmark;
    private String city;
    private String state;
    private int pincode;
    private String deliveryMobileNo;
    private String typeOfAddress;
    private boolean isPrimaryAddress;
    
    @ManyToOne()
    @JoinColumn(name="user_id")
    private UserRegistrationDBDTO userRegistrationDBDTO;
   

   
}
