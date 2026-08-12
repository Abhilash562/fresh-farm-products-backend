package com.example.fresh_farm_products.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fresh_farm_products.Entity.DeliveryPartner;

public interface DeliveryPartnerRepository extends JpaRepository<DeliveryPartner, Long>{

	Optional<DeliveryPartner> findByPartnerId(String partnerId);

    boolean existsByPartnerId(String partnerId);

    boolean existsByMobileNumber(String mobileNumber);

    List<DeliveryPartner> findByStatusTrue();

    List<DeliveryPartner> findByStatusTrueAndAvailableTrue();

	Optional<DeliveryPartner> findByEmail(String email);
}
