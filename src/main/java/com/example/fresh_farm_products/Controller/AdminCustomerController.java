package com.example.fresh_farm_products.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fresh_farm_products.Repository.CustomerRepository;

@RestController
@RequestMapping("/api/admin/customers")
public class AdminCustomerController {

    @Autowired
    private CustomerRepository repository;

    @GetMapping
    public ResponseEntity<?> customers() {
        return ResponseEntity.ok(
                repository.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> customer(@PathVariable String id) {
        return ResponseEntity.ok(
                repository.findById(id)
        );
    }
}
