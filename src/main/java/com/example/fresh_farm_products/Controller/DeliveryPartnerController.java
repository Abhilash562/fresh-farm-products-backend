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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.fresh_farm_products.DTO.ApiResponse;
import com.example.fresh_farm_products.DTO.DPLoginRequest;
import com.example.fresh_farm_products.DTO.DPLoginResponse;
import com.example.fresh_farm_products.DTO.DPRegisterRequest;
import com.example.fresh_farm_products.DTO.DeliveryPartnerRequest;
import com.example.fresh_farm_products.DTO.DeliveryPartnerResponse;
import com.example.fresh_farm_products.Service.DeliveryPartnerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/delivery-partners")
public class DeliveryPartnerController {

	@Autowired
    private DeliveryPartnerService partnerService;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(
	@RequestBody DPRegisterRequest request){
		return ResponseEntity.ok(
				partnerService.register(request)
				);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(
	        @RequestBody DPLoginRequest request){

	    DPLoginResponse response = partnerService.login(request);

	    return ResponseEntity.ok(
	        new ApiResponse<>(
	            true,
	            "Delivery Partner login successful",
	            response
	        )
	    );
	}

    @PostMapping
    public ResponseEntity<ApiResponse<DeliveryPartnerResponse>>
    createPartner(
            @Valid @RequestBody DeliveryPartnerRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                    ApiResponse.success(
                        "Delivery partner created successfully",
                        partnerService.createPartner(request)
                    )
                );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DeliveryPartnerResponse>>>
    getAllPartners() {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Delivery partners fetched successfully",
                        partnerService.getAllPartners()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DeliveryPartnerResponse>>
    getPartner(@PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Delivery partner fetched successfully",
                        partnerService.getPartner(id)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DeliveryPartnerResponse>>
    updatePartner(
            @PathVariable Long id,
            @Valid @RequestBody DeliveryPartnerRequest request
    ) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Delivery partner updated successfully",
                        partnerService.updatePartner(id, request)
                )
        );
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<DeliveryPartnerResponse>>
    updateStatus(
            @PathVariable Long id,
            @RequestParam boolean enabled
    ) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Partner status updated successfully",
                        partnerService.updateStatus(id, enabled)
                )
        );
    }

    @PatchMapping("/{id}/availability")
    public ResponseEntity<ApiResponse<DeliveryPartnerResponse>>
    updateAvailability(
            @PathVariable Long id,
            @RequestParam boolean available
    ) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Partner availability updated successfully",
                        partnerService.updateAvailability(
                                id,
                                available
                        )
                )
        );
    }
}
