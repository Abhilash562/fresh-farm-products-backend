package com.example.fresh_farm_products.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OrderItemRequest {
	
	@NotNull
    private Long productId;


    @NotNull
    @Min(value = 1,message="Quantity should be minimum 1")
    private Integer quantity;


    @NotNull
    @Positive(message="Price should be positive")
    private Double price;

}
