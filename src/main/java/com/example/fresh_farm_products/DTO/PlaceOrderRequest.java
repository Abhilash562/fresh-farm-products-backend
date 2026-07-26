package com.example.fresh_farm_products.DTO;

import java.util.List;

import com.example.fresh_farm_products.Entity.PaymentMethod;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlaceOrderRequest {
	
	@NotNull
    private String customerId;


    @NotNull
    private Long addressId;


    @NotNull(message="Payment method required")
    private PaymentMethod paymentMethod;


    @NotEmpty(message="Order items required")
    @Valid
    private List<OrderItemRequest> items;

}
