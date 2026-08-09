package com.example.fresh_farm_products.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fresh_farm_products.Entity.OrderStatus;
import com.example.fresh_farm_products.Service.AdminOrderService;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {

    @Autowired
    private AdminOrderService service;

    @GetMapping
    public ResponseEntity<?> getOrders() {
        return ResponseEntity.ok(
                service.getAllOrders()
        );
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(
            @PathVariable String id,
            @RequestParam OrderStatus status
    ) {
        service.updateStatus(id, status);

        return ResponseEntity.ok(
                "Status Updated"
        );
    }
}
