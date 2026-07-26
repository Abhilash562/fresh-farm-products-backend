package com.example.fresh_farm_products.DTO;

import java.time.LocalDateTime;
import java.util.List;

import com.example.fresh_farm_products.Entity.OrderStatus;
import com.example.fresh_farm_products.Entity.PaymentMethod;

import lombok.Data;

@Data
public class OrderResponse {
	
	private String orderId;


    private String customerId;


    private Double totalAmount;


    private PaymentMethod paymentMethod;


    private OrderStatus orderStatus;


    private LocalDateTime createdAt;


    private AddressResponse deliveryAddress;


    private List<OrderItemResponse> items;

}
