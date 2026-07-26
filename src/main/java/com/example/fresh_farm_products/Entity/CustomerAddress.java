package com.example.fresh_farm_products.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "customer_addresses")
@Data
public class CustomerAddress {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerId;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String mobileNumber;

    @Column(nullable = false)
    private String addressLine;

    @Column(nullable = false)
    private String village;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;
    
    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String pincode;

    private String landmark;

    private Boolean defaultAddress;

}
