package com.example.fresh_farm_products.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fresh_farm_products.DTO.DashboardResponse;
import com.example.fresh_farm_products.Entity.OrderStatus;
import com.example.fresh_farm_products.Repository.CustomerRepository;
import com.example.fresh_farm_products.Repository.OrderRepository;
import com.example.fresh_farm_products.Repository.ProductRepository;

@Service
public class AdminDashboardService {
	
	@Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    public DashboardResponse getDashboardData() {

        Long customers = customerRepository.count();

        Long products = productRepository.count();

        Long orders = orderRepository.count();

        Long pending = orderRepository
                .countByOrderStatus(OrderStatus.PENDING);

        Double sales = orderRepository.calculateTotalSales();

        return new DashboardResponse(
                customers,
                products,
                orders,
                sales,
                pending
        );
    }
}
