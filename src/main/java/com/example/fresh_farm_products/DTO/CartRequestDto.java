package com.example.fresh_farm_products.DTO;

import lombok.Data;

@Data
public class CartRequestDto {
	
	private String customerId;

    private Long productId;

    private Integer quantity;


}
