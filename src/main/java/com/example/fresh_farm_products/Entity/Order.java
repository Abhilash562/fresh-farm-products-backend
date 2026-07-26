package com.example.fresh_farm_products.Entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
	
	@Id
    private String orderId;


    private String customerId;


    private Double totalAmount;


    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;


    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;


    private LocalDateTime createdAt;


    @OneToMany(
        mappedBy = "order",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<OrderItem> items;

}
