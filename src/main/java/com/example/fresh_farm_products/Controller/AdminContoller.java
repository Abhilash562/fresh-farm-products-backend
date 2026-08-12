package com.example.fresh_farm_products.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fresh_farm_products.DTO.AdminLoginRequest;
import com.example.fresh_farm_products.DTO.AdminLoginResponse;
import com.example.fresh_farm_products.DTO.AdminRegisterRequest;
import com.example.fresh_farm_products.DTO.ApiResponse;
import com.example.fresh_farm_products.Service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminContoller {
	
	@Autowired
	private AdminService service;

	@PostMapping("/register")
	public ResponseEntity<?> register(
	@RequestBody AdminRegisterRequest request){
		return ResponseEntity.ok(
				service.register(request)
				);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(
	        @RequestBody AdminLoginRequest request){

	    AdminLoginResponse response = service.login(request);

	    return ResponseEntity.ok(
	        new ApiResponse<>(
	            true,
	            "Admin login successful",
	            response
	        )
	    );
	}
}
