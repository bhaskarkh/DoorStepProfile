package com.bhaskar.dto;

import java.io.File;

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
public class GoogleSignInDTO {
    String userName;
    String userGivenName;
    String userFamilyName;
    String userEmail;
    String userId;
    File userPhoto;


   
}
