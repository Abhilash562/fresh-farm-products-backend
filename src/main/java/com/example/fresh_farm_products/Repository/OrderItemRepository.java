package com.example.fresh_farm_products.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fresh_farm_products.Entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
