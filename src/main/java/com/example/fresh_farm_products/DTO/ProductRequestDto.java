package com.example.fresh_farm_products.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequestDto {
	
	@NotNull
    private Long categoryId;


    @NotBlank
    private String productName;


    private String description;


    private String productImage;


    @NotNull
    private Double price;


    @NotNull
    private Integer stock;


    private String unit;


    private Boolean featured;


    private Boolean popular;

}
