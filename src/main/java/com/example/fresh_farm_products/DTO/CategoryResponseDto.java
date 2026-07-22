package com.example.fresh_farm_products.DTO;

import lombok.Data;

@Data
public class CategoryResponseDto {
	
	private Long id;

    private String categoryName;

    private String categoryImage;

    private Long productCount;

}
