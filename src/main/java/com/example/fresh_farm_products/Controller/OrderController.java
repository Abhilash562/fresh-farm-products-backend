package com.example.fresh_farm_products.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fresh_farm_products.DTO.ApiResponse;
import com.example.fresh_farm_products.DTO.OrderResponse;
import com.example.fresh_farm_products.DTO.OrderTrackingResponse;
import com.example.fresh_farm_products.DTO.PlaceOrderRequest;
import com.example.fresh_farm_products.Service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> placeOrder(
            @Valid @RequestBody PlaceOrderRequest request
    ) {

        OrderResponse response = orderService.placeOrder(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Order placed successfully",
                        response
                ));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse<OrderResponse>> getOrder(
            @PathVariable String orderId
    ) {

        OrderResponse response = orderService.getOrderDetails(orderId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Order details fetched successfully",
                        response
                )
        );
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getCustomerOrders(
            @PathVariable String customerId
    ) {

        List<OrderResponse> response = orderService.getCustomerOrders(customerId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Customer orders fetched successfully",
                        response
                )
        );
    }
    
    //Track Order
    
    @GetMapping("/{orderId}/tracking")
    public ResponseEntity<ApiResponse<List<OrderTrackingResponse>>> trackOrder(
            @PathVariable String orderId) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Tracking fetched successfully",
                        orderService.trackOrder(orderId)
                )
        );
    }
    
    //Cancel Order
    
    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<ApiResponse<String>> cancelOrder(
            @PathVariable String orderId) {

        orderService.cancelOrder(orderId);

        return ResponseEntity.ok(
                new ApiResponse<>(true,
                        "Order cancelled successfully",
                        null)
        );
    }
    
    //Re-Order
    
    @PostMapping("/{orderId}/reorder")
    public ResponseEntity<ApiResponse<OrderResponse>> reorder(
            @PathVariable String orderId) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Reorder created successfully",
                        orderService.reorder(orderId)
                )
        );
    }
}
