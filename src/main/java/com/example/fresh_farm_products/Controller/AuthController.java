package com.example.fresh_farm_products.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fresh_farm_products.Config.JwtUtil;
import com.example.fresh_farm_products.DTO.ApiResponse;
import com.example.fresh_farm_products.DTO.AuthResponse;
import com.example.fresh_farm_products.DTO.LoginRequest;
import com.example.fresh_farm_products.DTO.RegisterRequest;
import com.example.fresh_farm_products.Entity.Customer;
import com.example.fresh_farm_products.Service.CustomerServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/customers")
public class AuthController {
	
	@Autowired
	private CustomerServiceImpl service;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/register")
	public ResponseEntity<ApiResponse<Customer>> register(
	        @RequestBody RegisterRequest request){

	    Customer customer =
	            service.registerCustomer(request);


	    return ResponseEntity.ok(
	            new ApiResponse<>(
	                    true,
	                    "Registration successful",
	                    customer
	            )
	    );
	}



	@PostMapping("/login")
	public ResponseEntity<ApiResponse<AuthResponse>> login(
	        @RequestBody LoginRequest request){


	    Customer customer =
	            service.loginCustomer(request);


	    String token =
	            jwtUtil.generateToken(customer.getCustomerId());


	    AuthResponse response =
	            new AuthResponse(
	                    token,
	                    customer.getCustomerId()
	            );


	    return ResponseEntity.ok(
	            new ApiResponse<>(
	                    true,
	                    "Login successful",
	                    response
	            )
	    );
	}

}
