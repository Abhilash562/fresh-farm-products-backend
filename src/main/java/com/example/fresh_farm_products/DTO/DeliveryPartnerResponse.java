package com.example.fresh_farm_products.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeliveryPartnerResponse {
	
	private Long id;

    private String partnerId;

    private String name;

    private String mobileNumber;

    private String email;

    private Boolean status;

    private Boolean available;

    private Long assignedOrders;

}
