package com.example.fresh_farm_products.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="products")
@Data
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

    private String productName;


    private String description;


    private String productImage;


    private Double price;


    private Integer stock;


    private String unit;


    @Column(name = "is_featured")
    private Boolean featured;

    @Column(name = "is_popular")
    private Boolean popular;


    private Boolean status=true;

}
