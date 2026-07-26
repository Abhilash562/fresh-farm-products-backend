package com.example.fresh_farm_products.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fresh_farm_products.DTO.AddressRequest;
import com.example.fresh_farm_products.DTO.AddressResponse;
import com.example.fresh_farm_products.Entity.CustomerAddress;
import com.example.fresh_farm_products.Exception.ResourceNotFoundException;
import com.example.fresh_farm_products.Repository.CustomerAddressRepository;

@Service
public class AddressService {
	
	@Autowired
	private CustomerAddressRepository addressRepository;


    public AddressResponse addAddress(AddressRequest request) {

        CustomerAddress address = new CustomerAddress();

        address.setCustomerId(request.getCustomerId());
        address.setFullName(request.getFullName());
        address.setMobileNumber(request.getMobileNumber());
        address.setAddressLine(request.getAddressLine());
        address.setVillage(request.getVillage());
        address.setCity(request.getCity());
        address.setState(request.getState());
        address.setCountry(request.getCountry());
        address.setPincode(request.getPincode());
        address.setDefaultAddress(false);


        CustomerAddress savedAddress =
                addressRepository.save(address);


        return convertToResponse(savedAddress);
    }


    public List<AddressResponse> getCustomerAddresses(String customerId) {

        List<CustomerAddress> addresses =
                addressRepository.findByCustomerId(customerId);


        return addresses.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }


    public AddressResponse updateAddress(
            Long id,
            AddressRequest request
    ) {

    	CustomerAddress address =
                addressRepository.findById(id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "Address not found"
                                )
                        );


    	address.setCustomerId(request.getCustomerId());
        address.setFullName(request.getFullName());
        address.setMobileNumber(request.getMobileNumber());
        address.setAddressLine(request.getAddressLine());
        address.setVillage(request.getVillage());
        address.setCity(request.getCity());
        address.setState(request.getState());
        address.setCountry(request.getCountry());
        address.setPincode(request.getPincode());
        address.setDefaultAddress(false);


        CustomerAddress updatedAddress =
                addressRepository.save(address);


        return convertToResponse(updatedAddress);
    }


    public void deleteAddress(Long id) {

    	CustomerAddress address =
                addressRepository.findById(id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "Address not found"
                                )
                        );


        addressRepository.delete(address);
    }


    public AddressResponse setDefaultAddress(Long id) {

    	CustomerAddress address =
                addressRepository.findById(id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "Address not found"
                                )
                        );


        List<CustomerAddress> customerAddresses =
                addressRepository.findByCustomerId(
                        address.getCustomerId()
                );


        customerAddresses.forEach(
                item -> item.setDefaultAddress(false)
        );


        address.setDefaultAddress(true);


        addressRepository.saveAll(customerAddresses);


        return convertToResponse(address);
    }


    /*
       Entity to DTO conversion
       Kept inside service as requested
    */
    private AddressResponse convertToResponse(CustomerAddress address) {

        AddressResponse response = new AddressResponse();

        response.setId(address.getId());
        response.setFullName(address.getFullName());
        response.setMobileNumber(address.getMobileNumber());
        response.setCustomerId(address.getCustomerId());
        response.setAddressLine(address.getAddressLine());
        response.setVillage(address.getVillage());
        response.setCity(address.getCity());
        response.setState(address.getState());
        response.setCountry(address.getCountry());
        response.setPincode(address.getPincode());
        response.setDefaultAddress(address.getDefaultAddress());

        return response;
    }
}
