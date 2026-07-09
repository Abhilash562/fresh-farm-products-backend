package com.example.fresh_farm_products.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fresh_farm_products.DTO.ApiResponse;
import com.example.fresh_farm_products.Entity.Customer;
import com.example.fresh_farm_products.Service.CustomerServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/customers")
public class CustomerContoller{
	
	@Autowired
	private CustomerServiceImpl service;
	
	@GetMapping("/profile/{id}")
	public ResponseEntity<ApiResponse<Customer>> profile(
	        @PathVariable String id){


	    Customer customer =
	            service.getCustomerProfile(id);


	    return ResponseEntity.ok(
	            new ApiResponse<>(
	                    true,
	                    "Profile fetched successfully",
	                    customer
	            )
	    );
	}

}
