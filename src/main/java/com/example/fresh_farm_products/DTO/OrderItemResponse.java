package com.example.fresh_farm_products.DTO;

import lombok.Data;

@Data
public class OrderItemResponse {
	
	 private Long productId;


	 private Integer quantity;


	 private Double price;


     private Double total;

}
