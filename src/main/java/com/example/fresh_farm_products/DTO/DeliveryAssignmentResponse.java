package com.example.fresh_farm_products.DTO;

import java.time.LocalDateTime;

import com.example.fresh_farm_products.Entity.DeliveryStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeliveryAssignmentResponse {
	
	private Long id;

    private Long orderId;

    private Long deliveryPartnerId;

    private String partnerName;

    private String partnerMobile;

    private DeliveryStatus deliveryStatus;

    private LocalDateTime assignedAt;

    private LocalDateTime deliveredAt;

}
