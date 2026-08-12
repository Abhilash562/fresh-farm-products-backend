package com.example.fresh_farm_products.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fresh_farm_products.Entity.DeliveryAssignment;
import com.example.fresh_farm_products.Entity.DeliveryStatus;

public interface DeliveryAssignmentRepository extends JpaRepository<DeliveryAssignment, Long>{

	List<DeliveryAssignment>
    findByDeliveryPartnerIdOrderByAssignedAtDesc(Long partnerId);

    List<DeliveryAssignment>
    findByDeliveryPartnerIdAndDeliveryStatus(
            Long partnerId,
            DeliveryStatus status
    );

    List<DeliveryAssignment>
    findByDeliveryPartnerIdAndDeliveryStatusIn(
            Long partnerId,
            List<DeliveryStatus> statuses
    );

    Optional<DeliveryAssignment>
    findByOrderId(Long orderId);

    boolean existsByOrderId(Long orderId);

    List<DeliveryAssignment>
    findByDeliveryPartnerIdAndDeliveryStatusOrderByDeliveredAtDesc(
            Long partnerId,
            DeliveryStatus status
    );

	long countByDeliveryPartnerIdAndDeliveryStatusNot(Long id, DeliveryStatus delivered);

	List<DeliveryAssignment> findByDeliveryPartner_Id(Long partnerId);

}
