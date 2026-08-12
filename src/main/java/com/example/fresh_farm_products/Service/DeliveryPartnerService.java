package com.example.fresh_farm_products.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.fresh_farm_products.Config.JwtUtil;
import com.example.fresh_farm_products.DTO.DPLoginRequest;
import com.example.fresh_farm_products.DTO.DPLoginResponse;
import com.example.fresh_farm_products.DTO.DPRegisterRequest;
import com.example.fresh_farm_products.DTO.DeliveryPartnerRequest;
import com.example.fresh_farm_products.DTO.DeliveryPartnerResponse;
import com.example.fresh_farm_products.Entity.DeliveryPartner;
import com.example.fresh_farm_products.Entity.DeliveryStatus;
import com.example.fresh_farm_products.Exception.BadRequestException;
import com.example.fresh_farm_products.Exception.ResourceNotFoundException;
import com.example.fresh_farm_products.Repository.DeliveryAssignmentRepository;
import com.example.fresh_farm_products.Repository.DeliveryPartnerRepository;

import jakarta.transaction.Transactional;

@Service
public class DeliveryPartnerService {

    @Autowired
    private DeliveryPartnerRepository partnerRepository;

    @Autowired
    private DeliveryAssignmentRepository assignmentRepository;
    
    @Autowired
    private PasswordEncoder encoder;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    public DeliveryPartner register(DPRegisterRequest request) {
    	
    	DeliveryPartner partner = new DeliveryPartner();

    	partner.setName(
                request.getName()
        );

    	partner.setMobileNumber(
    			request.getMobileNumber()
    	);
    	
    	partner.setEmail(
                request.getEmail()
        );

    	partner.setPassword(
                encoder.encode(
                        request.getPassword()
                )
        );

        partner.setStatus(true);
        partner.setAvailable(true);

        partner = partnerRepository.save(partner);

        // Generate partner ID: DP1, DP2, DP3...
        partner.setPartnerId("DP" + partner.getId());

        // Save partner ID
        return partnerRepository.save(partner);
	}

	public DPLoginResponse login(DPLoginRequest request) {
		
		DeliveryPartner partner = partnerRepository
                .findByEmail(request.getEmail())
                .orElseThrow(
                        () -> new RuntimeException("Delivery Partner not found")
                );

        boolean passwordMatch = encoder.matches(
                request.getPassword(),
                partner.getPassword()
        );

        if (!passwordMatch) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(
                partner.getEmail()
        );

        return new DPLoginResponse(
                partner.getId(),
                partner.getName(),
                partner.getEmail(),
                token
        );
	}
   

    @Transactional
    public DeliveryPartnerResponse createPartner(
            DeliveryPartnerRequest request) {

        if (partnerRepository.existsByPartnerId(
                request.getPartnerId())) {

            throw new BadRequestException(
                    "Partner ID already exists"
            );
        }

        if (partnerRepository.existsByMobileNumber(
                request.getMobileNumber())) {

            throw new BadRequestException(
                    "Mobile number already exists"
            );
        }

        DeliveryPartner partner = DeliveryPartner.builder()
                .partnerId(request.getPartnerId())
                .name(request.getName())
                .mobileNumber(request.getMobileNumber())
                .email(request.getEmail())
                .status(true)
                .available(true)
                .build();

        DeliveryPartner saved =
                partnerRepository.save(partner);

        return convertToResponse(saved);
    }

    public List<DeliveryPartnerResponse> getAllPartners() {

        return partnerRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public DeliveryPartnerResponse getPartner(Long id) {

        DeliveryPartner partner = partnerRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Delivery partner not found"
                        )
                );

        return convertToResponse(partner);
    }

    @Transactional
    public DeliveryPartnerResponse updatePartner(
            Long id,
            DeliveryPartnerRequest request) {

        DeliveryPartner partner = partnerRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Delivery partner not found"
                        )
                );

        if (!partner.getMobileNumber()
                .equals(request.getMobileNumber())
                && partnerRepository.existsByMobileNumber(
                        request.getMobileNumber())) {

            throw new BadRequestException(
                    "Mobile number already exists"
            );
        }

        partner.setName(request.getName());
        partner.setMobileNumber(request.getMobileNumber());
        partner.setEmail(request.getEmail());

        return convertToResponse(
                partnerRepository.save(partner)
        );
    }

    @Transactional
    public DeliveryPartnerResponse updateStatus(
            Long id,
            boolean status) {

        DeliveryPartner partner = partnerRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Delivery partner not found"
                        )
                );

        partner.setStatus(status);

        // Disabled partner cannot remain available
        if (!status) {
            partner.setAvailable(false);
        }

        return convertToResponse(
                partnerRepository.save(partner)
        );
    }

    @Transactional
    public DeliveryPartnerResponse updateAvailability(
            Long id,
            boolean available) {

        DeliveryPartner partner = partnerRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Delivery partner not found"
                        )
                );

        if (!partner.getStatus()) {
            throw new BadRequestException(
                    "Disabled partner cannot be available"
            );
        }

        partner.setAvailable(available);

        return convertToResponse(
                partnerRepository.save(partner)
        );
    }

    private DeliveryPartnerResponse convertToResponse(
            DeliveryPartner partner) {

        long assignedOrders = assignmentRepository
                .countByDeliveryPartnerIdAndDeliveryStatusNot(
                        partner.getId(),
                        DeliveryStatus.DELIVERED
                );

        return DeliveryPartnerResponse.builder()
                .id(partner.getId())
                .partnerId(partner.getPartnerId())
                .name(partner.getName())
                .mobileNumber(partner.getMobileNumber())
                .email(partner.getEmail())
                .status(partner.getStatus())
                .available(partner.getAvailable())
                .assignedOrders(assignedOrders)
                .build();
    }
}