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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fresh_farm_products.DTO.ApiResponse;
import com.example.fresh_farm_products.DTO.CartRequestDto;
import com.example.fresh_farm_products.DTO.CartResponseDto;
import com.example.fresh_farm_products.Service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	
	@Autowired
    private CartService cartService;

    /**
     * Add Product To Cart
     */
    @PostMapping
    public ResponseEntity<ApiResponse<String>> addToCart(
            @RequestBody CartRequestDto request
    ) {

        String message = cartService.addToCart(request);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        message,
                        null
                )
        );
    }

    /**
     * Get Customer Cart
     */
    @GetMapping("/{customerId}")
    public ResponseEntity<ApiResponse<CartResponseDto>> getCart(
            @PathVariable String customerId
    ) {

        CartResponseDto cart = cartService.getCart(customerId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Cart fetched successfully",
                        cart
                )
        );
    }

    /**
     * Update Cart Quantity
     */
    @PutMapping("/{cartId}")
    public ResponseEntity<ApiResponse<String>> updateCart(
            @PathVariable Long cartId,
            @RequestParam Integer quantity
    ) {

        String message = cartService.updateCart(
                cartId,
                quantity
        );

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        message,
                        null
                )
        );
    }

    /**
     * Remove Cart Item
     */
    @DeleteMapping("/{cartId}")
    public ResponseEntity<ApiResponse<String>> removeCartItem(
            @PathVariable Long cartId
    ) {

        String message = cartService.removeCartItem(cartId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        message,
                        null
                )
        );
    }

}
