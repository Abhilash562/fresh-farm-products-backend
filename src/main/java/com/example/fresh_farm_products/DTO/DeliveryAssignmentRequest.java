package com.example.fresh_farm_products.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeliveryAssignmentRequest {
	
	@NotNull(message = "Order ID is required")
    private String orderId;

    @NotNull(message = "Delivery partner ID is required")
    private Long deliveryPartnerId;

}
