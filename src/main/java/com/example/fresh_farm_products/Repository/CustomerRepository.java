package com.example.fresh_farm_products.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fresh_farm_products.Entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String>{

	Optional<Customer> findByMobileNumber(String mobileNumber);

	boolean existsByMobileNumber(String mobileNumber);

}
