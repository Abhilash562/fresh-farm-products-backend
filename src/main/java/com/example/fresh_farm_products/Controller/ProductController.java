package com.example.fresh_farm_products.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fresh_farm_products.DTO.ApiResponse;
import com.example.fresh_farm_products.DTO.ProductResponseDto;
import com.example.fresh_farm_products.Service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;

    /**
     * Get Products By Category
     */
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponse<List<ProductResponseDto>>> getProductsByCategory(
            @PathVariable Long categoryId
    ) {

        List<ProductResponseDto> products =
                productService.getProductsByCategory(categoryId);

        ApiResponse<List<ProductResponseDto>> response =
                new ApiResponse<>(
                        true,
                        "Products fetched successfully",
                        products
                );

        return ResponseEntity.ok(response);
    }


    /**
     * Get Featured Products
     */
    @GetMapping("/featured")
    public ResponseEntity<ApiResponse<List<ProductResponseDto>>> getFeaturedProducts() {

        List<ProductResponseDto> products =
                productService.getFeaturedProducts();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Featured products fetched successfully",
                        products
                )
        );
    }


    /**
     * Get Popular Products
     */
    @GetMapping("/popular")
    public ResponseEntity<ApiResponse<List<ProductResponseDto>>> getPopularProducts() {

        List<ProductResponseDto> products =
                productService.getPopularProducts();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Popular products fetched successfully",
                        products
                )
        );
    }


    /**
     * Search Products
     */
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<ProductResponseDto>>> searchProducts(
            @RequestParam String keyword
    ) {

        List<ProductResponseDto> products =
                productService.searchProducts(keyword);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Search result fetched successfully",
                        products
                )
        );
    }
    
    //Product details by id
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> getProductById(
            @PathVariable Long id) {


        ProductResponseDto product = productService.getProductById(id);


        return ResponseEntity.ok(
                new ApiResponse<>(
                    true,
                    "Product fetched successfully",
                    product
                )
        );
    }
    
    /**
     * Get Related Products
     */
    @GetMapping("/{id}/related")
    public ResponseEntity<ApiResponse<List<ProductResponseDto>>> getRelatedProducts(
            @PathVariable Long id
    ) {
        List<ProductResponseDto> products =
                productService.getRelatedProducts(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Related products fetched successfully",
                        products
                )
        );

    }
}
