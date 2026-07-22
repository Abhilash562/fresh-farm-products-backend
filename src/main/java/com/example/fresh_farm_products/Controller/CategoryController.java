package com.example.fresh_farm_products.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fresh_farm_products.DTO.ApiResponse;
import com.example.fresh_farm_products.DTO.CategoryResponseDto;
import com.example.fresh_farm_products.Service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	@GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponseDto>>> getCategories() {

        List<CategoryResponseDto> categories =
                categoryService.getAllCategories();

        ApiResponse<List<CategoryResponseDto>> response =
                new ApiResponse<>(
                        true,
                        "Categories fetched successfully",
                        categories
                );

        return ResponseEntity.ok(response);
    }

}
