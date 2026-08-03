package com.example.fresh_farm_products.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fresh_farm_products.Entity.OrderStatusHistory;

public interface OrderStatusHistoryRepository extends JpaRepository<OrderStatusHistory, Long>{

	Optional<OrderStatusHistory> findByOrderOrderIdOrderByUpdatedAtAsc(String orderId);

}
