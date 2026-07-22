package com.example.fresh_farm_products.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fresh_farm_products.Entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	List<Category> findByStatusTrue();

}
