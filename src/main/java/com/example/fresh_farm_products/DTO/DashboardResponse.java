package com.example.fresh_farm_products.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponse {
	
	private Long totalCustomers;
    private Long totalProducts;
    private Long totalOrders;
    private Double totalSales;
    private Long pendingOrders;

}
