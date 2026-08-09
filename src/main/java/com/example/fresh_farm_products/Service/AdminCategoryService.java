package com.example.fresh_farm_products.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fresh_farm_products.DTO.CategoryResponseDto;
import com.example.fresh_farm_products.Entity.Category;
import com.example.fresh_farm_products.Repository.CategoryRepository;
import com.example.fresh_farm_products.Repository.ProductRepository;

@Service
public class AdminCategoryService {
	
	@Autowired
    private CategoryRepository repository;

	@Autowired
	private ProductRepository productRepository;
	
    public Category save(Category category) {
        return repository.save(category);
    }

    public List<CategoryResponseDto> getAllCategories() {

        return repository.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
    }
    
    private CategoryResponseDto convertToDto(Category category) {

        CategoryResponseDto dto = new CategoryResponseDto();

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
    
    public Category update(Long id, Category category) {

        Category existing = repository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Category not found")
                );

        existing.setCategoryName(
                category.getCategoryName()
        );

        existing.setCategoryImage(
                category.getCategoryImage()
        );

        existing.setStatus(
                category.getStatus()
        );

        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
