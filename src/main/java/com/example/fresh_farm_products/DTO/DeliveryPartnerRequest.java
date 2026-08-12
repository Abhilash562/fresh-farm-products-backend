package com.example.fresh_farm_products.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DeliveryPartnerRequest {
	
	@NotBlank(message = "Partner ID is required")
    private String partnerId;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100)
    private String name;

    @NotBlank(message = "Mobile number is required")
    @Pattern(
        regexp = "^[6-9][0-9]{9}$",
        message = "Invalid mobile number"
    )
    private String mobileNumber;

    @Email(message = "Invalid email")
    private String email;

}
