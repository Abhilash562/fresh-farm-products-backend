package com.example.fresh_farm_products.DTO;

import lombok.Data;

@Data
public class ProductResponseDto {
	
	private Long id;


    private String productName;


    private String description;


    private String productImage;


    private Double price;


    private Integer stock;


    private String unit;


    private String categoryName;

}
