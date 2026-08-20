package com.example.fresh_farm_products.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ExpectedDeliveryRequest {
	
    private LocalDateTime expectedDeliveryAt;

}
