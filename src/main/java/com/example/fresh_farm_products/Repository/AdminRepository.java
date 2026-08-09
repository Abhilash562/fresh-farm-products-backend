package com.example.fresh_farm_products.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fresh_farm_products.Entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{

	Optional<Admin> findByEmail(String email);
}
