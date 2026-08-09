package com.example.fresh_farm_products.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.fresh_farm_products.Entity.Order;
import com.example.fresh_farm_products.Entity.OrderStatus;

public interface OrderRepository extends JpaRepository<Order, String>{

	List<Order> findByCustomerIdOrderByCreatedAtDesc(String customerId);

	Long countByOrderStatus(OrderStatus pending);

	@Query("SELECT COALESCE(SUM(o.totalAmount),0) FROM Order o")
	Double calculateTotalSales();


}
