package com.example.fresh_farm_products.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fresh_farm_products.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, String>{

	Optional<Order> findByCustomerIdOrderByCreatedAtDesc(String customerId);


}
