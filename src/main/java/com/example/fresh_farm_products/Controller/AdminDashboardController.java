package com.example.fresh_farm_products.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fresh_farm_products.Service.AdminDashboardService;


@RestController
@RequestMapping("/api/admin/dashboard")
public class AdminDashboardController {
	
	@Autowired
	private AdminDashboardService service;


	@GetMapping
	public ResponseEntity<?> dashboard(){

	    return ResponseEntity.ok(
	        service.getDashboardData()
	    );
	}
}
