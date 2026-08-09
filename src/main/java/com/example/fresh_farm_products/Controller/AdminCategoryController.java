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

import com.example.fresh_farm_products.Entity.Category;
import com.example.fresh_farm_products.Service.AdminCategoryService;

@RestController
@RequestMapping("/api/admin/categories")
public class AdminCategoryController {

    @Autowired
    private AdminCategoryService service;

    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        return ResponseEntity.ok(
                service.save(category)
        );
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(
                service.getAllCategories()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody Category category
    ) {
        return ResponseEntity.ok(
                service.update(id, category)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.ok(
                "Category deleted"
        );
    }
}
