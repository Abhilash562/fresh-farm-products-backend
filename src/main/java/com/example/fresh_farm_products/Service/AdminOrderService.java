package com.example.fresh_farm_products.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fresh_farm_products.Entity.Order;
import com.example.fresh_farm_products.Entity.OrderStatus;
import com.example.fresh_farm_products.Repository.OrderRepository;

@Service
public class AdminOrderService {
	
	@Autowired
    private OrderRepository repository;

   public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public void updateStatus(
            String orderId,
            OrderStatus status
    ) {

        Order order = repository.findById(orderId)
                .orElseThrow(
                        () -> new RuntimeException("Order not found")
                );

        order.setOrderStatus(status);

        repository.save(order);
    }

}
