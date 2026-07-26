package com.example.fresh_farm_products.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AddressRequest {

	@NotNull(message="Customer id is required")
    private String customerId;


    @NotBlank(message="Full name is required")
    private String fullName;


    @NotBlank(message="Mobile number is required")
    @Pattern(
        regexp="^[0-9]{10}$",
        message="Mobile number must contain 10 digits"
    )
    private String mobileNumber;


    @NotBlank(message="Address is required")
    private String addressLine;


    @NotBlank(message="Village/City is required")
    private String village;


    @NotBlank(message="city is required")
    private String city;


    @NotBlank(message="State is required")
    private String state;
    
    @NotBlank(message="Country is required")
    private String country;


    @NotBlank(message="Pincode is required")
    @Pattern(
        regexp="^[0-9]{6}$",
        message="Invalid pincode"
    )
    private String pincode;


    private String landmark;


    private Boolean defaultAddress;
}
