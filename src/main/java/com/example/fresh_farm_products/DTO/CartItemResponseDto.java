package com.example.fresh_farm_products.DTO;

import lombok.Data;

@Data
public class CartItemResponseDto {
	
	private Long cartId;

    private Long productId;

    private String productName;

    private String productImage;

    private Double price;

    private Integer quantity;

    private String unit;

    private Double subtotal;


}
