package com.example.fresh_farm_products.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="customers")
@Data
public class Customer {


    @Id
    private String customerId;


    private String fullName;


    @Column(unique = true)
    private String mobileNumber;


    private String email;


    private String village;


    private String password;


    private LocalDateTime createdAt;


    @PrePersist
    public void created(){

        createdAt = LocalDateTime.now();

    }

}
