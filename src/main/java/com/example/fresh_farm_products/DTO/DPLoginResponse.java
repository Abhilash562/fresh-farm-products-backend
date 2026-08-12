package com.example.fresh_farm_products.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DPLoginResponse {
	
	private Long id;

    private String name;

    private String email;

    private String token;

}
