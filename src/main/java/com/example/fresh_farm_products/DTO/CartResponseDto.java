package com.example.fresh_farm_products.DTO;

import java.util.List;

import lombok.Data;

@Data
public class CartResponseDto {

	private List<CartItemResponseDto> items;


    private Integer totalItems;


    private Double totalAmount;


    private Double deliveryCharge;


    private Double grandTotal;

}
