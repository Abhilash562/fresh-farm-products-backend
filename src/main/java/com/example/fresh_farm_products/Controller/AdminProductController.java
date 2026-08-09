package com.example.fresh_farm_products.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fresh_farm_products.Entity.Product;
import com.example.fresh_farm_products.Service.AdminProductService;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

    @Autowired
    private AdminProductService service;

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(
                service.save(product)
        );
    }

    @GetMapping
    public ResponseEntity<?> products() {
        return ResponseEntity.ok(
                service.getAllProducts()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody Product product
    ) {
        return ResponseEntity.ok(
                service.update(id, product)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.ok(
                "Product deleted"
        );
    }
}
