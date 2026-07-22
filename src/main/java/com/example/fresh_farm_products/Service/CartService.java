package com.example.fresh_farm_products.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fresh_farm_products.DTO.CartItemResponseDto;
import com.example.fresh_farm_products.DTO.CartRequestDto;
import com.example.fresh_farm_products.DTO.CartResponseDto;
import com.example.fresh_farm_products.Entity.Cart;
import com.example.fresh_farm_products.Entity.Product;
import com.example.fresh_farm_products.Exception.ResourceNotFoundException;
import com.example.fresh_farm_products.Repository.CartRepository;
import com.example.fresh_farm_products.Repository.ProductRepository;

@Service
public class CartService {
	
	@Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;


    public String addToCart(CartRequestDto request) {

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        Cart cart = cartRepository.findByCustomerIdAndProductId(
                request.getCustomerId(),
                request.getProductId()
        ).orElse(new Cart());

        cart.setCustomerId(request.getCustomerId());
        cart.setProduct(product);
        cart.setQuantity(
                cart.getQuantity() == null ? request.getQuantity()
                        : cart.getQuantity() + request.getQuantity()
        );

        cartRepository.save(cart);

        return "Product added to cart";
    }


    public CartResponseDto getCart(String customerId) {

        List<Cart> carts = cartRepository.findByCustomerId(customerId);

        List<CartItemResponseDto> items = carts.stream()
                .map(cart -> {

                    CartItemResponseDto dto = new CartItemResponseDto();

                    dto.setCartId(cart.getId());
                    dto.setProductId(cart.getProduct().getId());
                    dto.setProductName(cart.getProduct().getProductName());
                    dto.setProductImage(cart.getProduct().getProductImage());
                    dto.setPrice(cart.getProduct().getPrice());
                    dto.setQuantity(cart.getQuantity());
                    dto.setUnit(cart.getProduct().getUnit());
                    dto.setSubtotal(
                            cart.getQuantity() * cart.getProduct().getPrice()
                    );

                    return dto;

                })
                .toList();

        Double total = items.stream()
                .mapToDouble(CartItemResponseDto::getSubtotal)
                .sum();

        CartResponseDto response = new CartResponseDto();

        response.setItems(items);
        response.setTotalItems(items.size());
        response.setTotalAmount(total);
        response.setDeliveryCharge(items.isEmpty() ? 0.0 : 40.0);
        response.setGrandTotal(total + response.getDeliveryCharge());

        return response;
    }


    public String updateCart(Long cartId, Integer quantity) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));

        cart.setQuantity(quantity);
        cartRepository.save(cart);

        return "Cart updated successfully";
    }


    public String removeCartItem(Long cartId) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));

        cartRepository.delete(cart);

        return "Cart item removed";
    }

}
