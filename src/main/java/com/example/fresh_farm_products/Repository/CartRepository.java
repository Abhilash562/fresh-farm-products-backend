package com.example.fresh_farm_products.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fresh_farm_products.Entity.Cart;

public interface CartRepository extends JpaRepository< Cart, Long>{

	List<Cart> findByCustomerId(String customerId);

	Optional<Cart> findByCustomerIdAndProductId(String customerId, Long productId);

}
