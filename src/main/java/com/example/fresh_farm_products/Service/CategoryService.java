package com.example.fresh_farm_products.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fresh_farm_products.DTO.CategoryResponseDto;
import com.example.fresh_farm_products.Entity.Category;
import com.example.fresh_farm_products.Exception.ResourceNotFoundException;
import com.example.fresh_farm_products.Repository.CategoryRepository;
import com.example.fresh_farm_products.Repository.ProductRepository;

@Service
public class CategoryService {
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

    public List<CategoryResponseDto> getAllCategories() {

        List<Category> categories =
                categoryRepository.findByStatusTrue();

        if (categories.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No categories found"
            );
        }

        return categories.stream()
                .map(this::convertToDto)
                .toList();
    }


    /**
     * Convert Category Entity To Response DTO
     */
    private CategoryResponseDto convertToDto(Category category) {

        CategoryResponseDto dto =
                new CategoryResponseDto();

        dto.setId(category.getId());
        dto.setCategoryName(category.getCategoryName());
        dto.setCategoryImage(category.getCategoryImage());

        dto.setProductCount(
                productRepository.countByCategoryId(
                        category.getId()
                )
        );

        return dto;
    }
}
