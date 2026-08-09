package com.example.fresh_farm_products.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fresh_farm_products.Entity.Customer;
import com.example.fresh_farm_products.Repository.CustomerRepository;

@Service
public class AdminCustomerService {
	
	@Autowired
    private CustomerRepository repository;

    public List<Customer> getCustomers() {
        return repository.findAll();
    }

    public Customer getCustomer(String id) {

        return repository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Customer not found")
                );
    }

}
