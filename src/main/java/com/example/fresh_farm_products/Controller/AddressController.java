package com.example.fresh_farm_products.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fresh_farm_products.DTO.AddressRequest;
import com.example.fresh_farm_products.DTO.AddressResponse;
import com.example.fresh_farm_products.DTO.ApiResponse;
import com.example.fresh_farm_products.Service.AddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

	@Autowired
	private AddressService addressService;


	/**
     * Add Address
     */
    @PostMapping
    public ResponseEntity<ApiResponse<AddressResponse>> addAddress(
            @Valid @RequestBody AddressRequest request
    ) {

        AddressResponse response =
                addressService.addAddress(request);

        return new ResponseEntity<>(
                new ApiResponse<>(
                        true,
                        "Address added successfully",
                        response
                ),
                HttpStatus.CREATED
        );
    }


    /**
     * Get Customer Addresses
     */
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ApiResponse<List<AddressResponse>>> getCustomerAddresses(
            @PathVariable String customerId
    ) {

        List<AddressResponse> response =
                addressService.getCustomerAddresses(customerId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Addresses fetched successfully",
                        response
                )
        );
    }


    /**
     * Update Address
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AddressResponse>> updateAddress(
            @PathVariable Long id,
            @Valid @RequestBody AddressRequest request
    ) {

        AddressResponse response =
                addressService.updateAddress(id, request);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Address updated successfully",
                        response
                )
        );
    }


    /**
     * Delete Address
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAddress(
            @PathVariable Long id
    ) {

        addressService.deleteAddress(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Address deleted successfully",
                        null
                )
        );
    }


    /**
     * Set Default Address
     */
    @PatchMapping("/{id}/default")
    public ResponseEntity<ApiResponse<AddressResponse>> setDefaultAddress(
            @PathVariable Long id
    ) {

        AddressResponse response =
                addressService.setDefaultAddress(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Default address selected",
                        response
                )
        );
    }
}