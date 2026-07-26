package com.example.fresh_farm_products.DTO;

import lombok.Data;

@Data
public class AddressResponse {

	private Long id;


    private String customerId;


    private String fullName;


    private String mobileNumber;


    private String addressLine;


    private String village;


    private String city;


    private String state;
    
    private String country;


    private String pincode;


    private String landmark;


    private Boolean defaultAddress;
}
