package com.example.fresh_farm_products.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fresh_farm_products.DTO.ProductResponseDto;
import com.example.fresh_farm_products.Entity.Product;
import com.example.fresh_farm_products.Exception.ResourceNotFoundException;
import com.example.fresh_farm_products.Repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

    public List<ProductResponseDto> getProductsByCategory(Long categoryId) {

        List<Product> products =
                productRepository.findByCategoryId(categoryId);

        if (products.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No products found for this category"
            );
        }

        return products.stream()
                .map(this::convertToDto)
                .toList();
    }


    public List<ProductResponseDto> getFeaturedProducts() {

        List<Product> products =
                productRepository.findByFeaturedTrue();

        if (products.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No featured products found"
            );
        }

        return products.stream()
                .map(this::convertToDto)
                .toList();
    }


    public List<ProductResponseDto> getPopularProducts() {

        List<Product> products =
                productRepository.findByPopularTrue();

        if (products.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No popular products found"
            );
        }

        return products.stream()
                .map(this::convertToDto)
                .toList();
    }


    public List<ProductResponseDto> searchProducts(String keyword) {

        List<Product> products =
                productRepository
                        .findByProductNameContainingIgnoreCase(keyword);

        if (products.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No products found"
            );
        }

        return products.stream()
                .map(this::convertToDto)
                .toList();
    }


    /**
     * Convert Product Entity To Response DTO
     */
    private ProductResponseDto convertToDto(Product product) {

        ProductResponseDto dto = new ProductResponseDto();

        dto.setId(product.getId());
        dto.setProductName(product.getProductName());
        dto.setDescription(product.getDescription());
        dto.setProductImage(product.getProductImage());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        dto.setUnit(product.getUnit());

        if (product.getCategory() != null) {
            dto.setCategoryName(
                    product.getCategory().getCategoryName()
            );
        }

        return dto;
    }


    public ProductResponseDto getProductById(Long id) {


        Product product = productRepository.findById(id)
                .orElseThrow(() -> 
                    new ResourceNotFoundException(
                        "Product not found with id : " + id
                    )
                );


        ProductResponseDto dto = new ProductResponseDto();

        dto.setId(product.getId());
        dto.setProductName(product.getProductName());
        dto.setDescription(product.getDescription());
        dto.setProductImage(product.getProductImage());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        dto.setUnit(product.getUnit());

        if(product.getCategory()!=null) {
            dto.setCategoryName(product.getCategory().getCategoryName());
        }

        return dto;
    }


    public List<ProductResponseDto> getRelatedProducts(Long productId) {
        Product product =
                productRepository.findById(productId)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Product not found with id : " + productId
                    )
                );

        Long categoryId = product.getCategory().getId();
        List<Product> products =
                productRepository.findByCategoryId(
                        categoryId
                );

        if(products.isEmpty()) {

            throw new ResourceNotFoundException(
                    "No related products found"
            );
        }

        return products.stream()
                .map(this::convertToDto)
                .toList();
    }
}
