package com.example.fresh_farm_products.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminLoginResponse {
	private Long adminId;

    private String name;

    private String email;

    private String token;

}
