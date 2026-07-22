package com.example.fresh_farm_products.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fresh_farm_products.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	Long countByCategoryId(Long id);

	List<Product> findByCategoryId(Long categoryId);

	List<Product> findByFeaturedTrue();

	List<Product> findByPopularTrue();

	List<Product> findByProductNameContainingIgnoreCase(String keyword);
}
