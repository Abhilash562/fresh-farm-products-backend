package com.example.fresh_farm_products.Controller;

import java.time.LocalDateTime;

import com.example.fresh_farm_products.Entity.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderTrackingResponse {
	
	private OrderStatus status;

    private LocalDateTime updatedAt;

}
