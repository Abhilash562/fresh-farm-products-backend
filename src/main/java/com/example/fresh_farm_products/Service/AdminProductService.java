package com.example.fresh_farm_products.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fresh_farm_products.Entity.Product;
import com.example.fresh_farm_products.Repository.ProductRepository;

@Service
public class AdminProductService {
	
	@Autowired
    private ProductRepository repository;

    public Product save(Product product) {
        return repository.save(product);
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product update(Long id, Product product) {

        Product existing = repository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Product not found")
                );

        existing.setProductName(
                product.getProductName()
        );

        existing.setPrice(
                product.getPrice()
        );

        existing.setStock(
                product.getStock()
        );

        existing.setUnit(
                product.getUnit()
        );

        existing.setDescription(
                product.getDescription()
        );

        existing.setStatus(
                product.getStatus()
        );

        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
