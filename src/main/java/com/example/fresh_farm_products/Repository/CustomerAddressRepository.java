package com.example.fresh_farm_products.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fresh_farm_products.Entity.CustomerAddress;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long>{

	List<CustomerAddress> findByCustomerId(String customerId);

}
