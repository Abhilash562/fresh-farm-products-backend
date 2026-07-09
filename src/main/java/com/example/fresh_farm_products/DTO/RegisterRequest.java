package com.example.fresh_farm_products.DTO;

import lombok.Data;

@Data
public class RegisterRequest {
	
	private String fullName;
	
	private String mobileNumber;

    private String email;

    private String village;

    private String password;    

}
