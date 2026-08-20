package com.example.fresh_farm_products.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fresh_farm_products.DTO.DeliveryAssignmentRequest;
import com.example.fresh_farm_products.DTO.DeliveryAssignmentResponse;
import com.example.fresh_farm_products.Entity.DeliveryAssignment;
import com.example.fresh_farm_products.Entity.DeliveryPartner;
import com.example.fresh_farm_products.Entity.DeliveryStatus;
import com.example.fresh_farm_products.Exception.BadRequestException;
import com.example.fresh_farm_products.Exception.ResourceNotFoundException;
import com.example.fresh_farm_products.Repository.DeliveryAssignmentRepository;
import com.example.fresh_farm_products.Repository.DeliveryPartnerRepository;

import jakarta.transaction.Transactional;

@Service
public class DeliveryAssignmentService {

    @Autowired
    private DeliveryAssignmentRepository assignmentRepository;

    @Autowired
    private DeliveryPartnerRepository partnerRepository;

    @Transactional
    public DeliveryAssignmentResponse assignOrder(
            DeliveryAssignmentRequest request) {

        if (assignmentRepository.existsByOrderId(request.getOrderId())) {
            throw new BadRequestException(
                    "Order is already assigned to a delivery partner"
            );
        }

        DeliveryPartner partner = partnerRepository.findById(
                request.getDeliveryPartnerId()
        ).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Delivery partner not found"
                )
        );

        if (!partner.getStatus()) {
            throw new BadRequestException(
                    "Delivery partner is disabled"
            );
        }

        if (!partner.getAvailable()) {
            throw new BadRequestException(
                    "Delivery partner is currently unavailable"
            );
        }

        DeliveryAssignment assignment = DeliveryAssignment.builder()
                .orderId(request.getOrderId())
                .deliveryPartner(partner)
                .deliveryStatus(DeliveryStatus.ASSIGNED)
                .assignedAt(LocalDateTime.now())
                .build();

        DeliveryAssignment saved = assignmentRepository.save(assignment);

        return convertToResponse(saved);
    }

    public List<DeliveryAssignmentResponse> getAssignedOrders(
            Long partnerId) {

        verifyPartner(partnerId);

        return assignmentRepository
                .findByDeliveryPartnerIdOrderByAssignedAtDesc(partnerId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public DeliveryAssignmentResponse getAssignment(Long assignmentId) {

        DeliveryAssignment assignment = assignmentRepository
                .findById(assignmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Delivery assignment not found"
                        )
                );

        return convertToResponse(assignment);
    }

    @Transactional
    public DeliveryAssignmentResponse updateStatus(
            Long assignmentId,
            DeliveryStatus newStatus) {

        DeliveryAssignment assignment = assignmentRepository
                .findById(assignmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Delivery assignment not found"
                        )
                );

        DeliveryStatus currentStatus = assignment.getDeliveryStatus();

        validateStatusTransition(currentStatus, newStatus);

        assignment.setDeliveryStatus(newStatus);

        if (newStatus == DeliveryStatus.DELIVERED) {
            assignment.setDeliveredAt(LocalDateTime.now());

            DeliveryPartner partner = assignment.getDeliveryPartner();

            partner.setAvailable(true);

            partnerRepository.save(partner);
        }

        return convertToResponse(
                assignmentRepository.save(assignment)
        );
    }

    public List<DeliveryAssignmentResponse> getDeliveryHistory(
            Long partnerId) {

        verifyPartner(partnerId);

        return assignmentRepository
                .findByDeliveryPartnerIdAndDeliveryStatusOrderByDeliveredAtDesc(
                        partnerId,
                        DeliveryStatus.DELIVERED
                )
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    private void verifyPartner(Long partnerId) {

        if (!partnerRepository.existsById(partnerId)) {
            throw new ResourceNotFoundException(
                    "Delivery partner not found"
            );
        }
    }

    private void validateStatusTransition(
            DeliveryStatus current,
            DeliveryStatus next) {

        boolean valid = switch (current) {
            case ASSIGNED ->
                    next == DeliveryStatus.ACCEPTED;

            case ACCEPTED ->
                    next == DeliveryStatus.PICKED_UP;

            case PICKED_UP ->
                    next == DeliveryStatus.OUT_FOR_DELIVERY;

            case OUT_FOR_DELIVERY ->
                    next == DeliveryStatus.DELIVERED;

            case DELIVERED ->
                    false;
        };

        if (!valid) {
            throw new BadRequestException(
                    "Invalid delivery status transition: "
                            + current + " -> " + next
            );
        }
    }

    private DeliveryAssignmentResponse convertToResponse(
            DeliveryAssignment assignment) {

        DeliveryPartner partner = assignment.getDeliveryPartner();

        return DeliveryAssignmentResponse.builder()
                .id(assignment.getId())
                .orderId(assignment.getOrderId())
                .deliveryPartnerId(partner.getId())
                .partnerName(partner.getName())
                .partnerMobile(partner.getMobileNumber())
                .deliveryStatus(assignment.getDeliveryStatus())
                .assignedAt(assignment.getAssignedAt())
                .deliveredAt(assignment.getDeliveredAt())
                .expectedDeliveryAt(assignment.getExpectedDeliveryAt())
                .build();
    }
    
    @Transactional
    public List<DeliveryAssignmentResponse> getAllDeliveryAssignments() {

        List<DeliveryAssignment> assignments =
        		assignmentRepository.findAll();

        return assignments.stream()
                .map(this::mapToResponse)
                .toList();
    }

    private DeliveryAssignmentResponse mapToResponse(
            DeliveryAssignment assignment) {

        return DeliveryAssignmentResponse.builder()
                .id(assignment.getId())
                .orderId(assignment.getOrderId())
                .deliveryPartnerId(
                        assignment.getDeliveryPartner().getId())
                .partnerName(
                        assignment.getDeliveryPartner().getName())
                .partnerMobile(
                        assignment.getDeliveryPartner().getMobileNumber())
                .deliveryStatus(assignment.getDeliveryStatus())
                .assignedAt(assignment.getAssignedAt())
                .deliveredAt(assignment.getDeliveredAt())
                .expectedDeliveryAt(assignment.getExpectedDeliveryAt())
                .build();
    }
}