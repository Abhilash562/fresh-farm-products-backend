package com.example.fresh_farm_products.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fresh_farm_products.DTO.ApiResponse;
import com.example.fresh_farm_products.DTO.DeliveryAssignmentRequest;
import com.example.fresh_farm_products.DTO.DeliveryAssignmentResponse;
import com.example.fresh_farm_products.DTO.ExpectedDeliveryRequest;
import com.example.fresh_farm_products.DTO.UpdateDeliveryStatusRequest;
import com.example.fresh_farm_products.Entity.DeliveryAssignment;
import com.example.fresh_farm_products.Repository.DeliveryAssignmentRepository;
import com.example.fresh_farm_products.Service.DeliveryAssignmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/delivery-assignments")
public class DeliveryAssignmentController {

	@Autowired
    private DeliveryAssignmentService assignmentService;

    /**
     * ADMIN
     * Assign an order to a delivery partner.
     */
    @PostMapping
    public ResponseEntity<ApiResponse<DeliveryAssignmentResponse>>
    assignOrder(
            @Valid @RequestBody DeliveryAssignmentRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                    ApiResponse.success(
                        "Order assigned successfully",
                        assignmentService.assignOrder(request)
                    )
                );
    }

    /**
     * DELIVERY PARTNER
     */
    @GetMapping("/partner/{partnerId}")
    public ResponseEntity<
            ApiResponse<List<DeliveryAssignmentResponse>>>
    getAssignedOrders(
            @PathVariable Long partnerId
    ) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Assigned orders fetched successfully",
                        assignmentService.getAssignedOrders(
                                partnerId
                        )
                )
        );
    }

    /**
     * Get single assignment.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DeliveryAssignmentResponse>>
    getAssignment(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Assignment fetched successfully",
                        assignmentService.getAssignment(id)
                )
        );
    }

    /**
     * Update:
     * ASSIGNED
     * ACCEPTED
     * PICKED_UP
     * OUT_FOR_DELIVERY
     * DELIVERED
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<DeliveryAssignmentResponse>>
    updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateDeliveryStatusRequest request
    ) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Delivery status updated successfully",
                        assignmentService.updateStatus(
                                id,
                                request.getStatus()
                        )
                )
        );
    }

    /**
     * Delivery history.
     */
    @GetMapping("/partner/{partnerId}/history")
    public ResponseEntity<
            ApiResponse<List<DeliveryAssignmentResponse>>>
    getHistory(
            @PathVariable Long partnerId
    ) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Delivery history fetched successfully",
                        assignmentService.getDeliveryHistory(
                                partnerId
                        )
                )
        );
    }
    
    //All assignements
    @GetMapping
    public ResponseEntity<ApiResponse<List<DeliveryAssignmentResponse>>>
            getAllDeliveryAssignments() {

        List<DeliveryAssignmentResponse> assignments =
        		assignmentService.getAllDeliveryAssignments();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(
                        "Delivery assignments fetched successfully",
                        assignments
                ));
    }
    
    @Autowired
    private DeliveryAssignmentRepository assignmentRepository;
    
    @PutMapping("/{id}/expected-delivery")
    public ResponseEntity<DeliveryAssignment> updateExpectedDelivery(
            @PathVariable Long id,
            @RequestBody ExpectedDeliveryRequest request) {

        DeliveryAssignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Delivery assignment not found"));

        assignment.setExpectedDeliveryAt(request.getExpectedDeliveryAt());

        DeliveryAssignment updated = assignmentRepository.save(assignment);

        return ResponseEntity.ok(updated);
    }
}
