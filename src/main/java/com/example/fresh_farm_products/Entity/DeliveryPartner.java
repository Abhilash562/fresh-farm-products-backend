package com.example.fresh_farm_products.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "delivery_partners")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryPartner {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "partner_id", unique = true)
    private String partnerId;

    @Column(nullable = false)
    private String name;

    @Column(name = "mobile_number", nullable = false, unique = true)
    private String mobileNumber;

    @Column(unique = true)
    private String email;

    /**
     * ENABLED / DISABLED
     */
    @Column(nullable = false)
    private Boolean status;

    /**
     * true  = currently available
     * false = currently unavailable
     */
    @Column(nullable = false)
    private Boolean available;
    
    private String password;

}
