package com.example.fresh_farm_products.DTO;

import com.example.fresh_farm_products.Entity.DeliveryStatus;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateDeliveryStatusRequest {
	
	@NotNull(message = "Delivery status is required")
    private DeliveryStatus status;

}
