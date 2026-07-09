package com.example.fresh_farm_products.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
	
	private String token;

    private String customerId;

}
