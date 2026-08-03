package com.example.fresh_farm_products.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fresh_farm_products.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, String>{

	List<Order> findByCustomerIdOrderByCreatedAtDesc(String customerId);


}
