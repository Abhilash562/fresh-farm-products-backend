package com.example.fresh_farm_products.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.fresh_farm_products.DTO.LoginRequest;
import com.example.fresh_farm_products.DTO.RegisterRequest;
import com.example.fresh_farm_products.Entity.Customer;
import com.example.fresh_farm_products.Exception.CustomerAlreadyExistsException;
import com.example.fresh_farm_products.Exception.InvalidCredentialException;
import com.example.fresh_farm_products.Exception.ResourceNotFoundException;
import com.example.fresh_farm_products.Repository.CustomerRepository;

@Service
public class CustomerServiceImpl {
	
	@Autowired
	private CustomerRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public Customer registerCustomer(RegisterRequest request){

	    if(repository.existsByMobileNumber(request.getMobileNumber())){
	        throw new CustomerAlreadyExistsException(
	                "Mobile number already registered"
	        );
	    }


	    Customer customer = new Customer();

	    customer.setCustomerId(generateCustomerId());
	    customer.setFullName(request.getFullName());
	    customer.setMobileNumber(request.getMobileNumber());
	    customer.setEmail(request.getEmail());
	    customer.setVillage(request.getVillage());
	    customer.setPassword(
	            encoder.encode(request.getPassword())
	    );


	    return repository.save(customer);
	}

	public Customer loginCustomer(LoginRequest request){

	    Customer customer =
	            repository.findByMobileNumber(request.getMobileNumber())
	            .orElseThrow(
	              () -> new InvalidCredentialException(
	                  "Invalid mobile number or password"
	              )
	            );


	    if(!encoder.matches(
	            request.getPassword(),
	            customer.getPassword())){

	        throw new InvalidCredentialException(
	                "Invalid mobile number or password"
	        );
	    }


	    return customer;
	}
    
    private String generateCustomerId(){
        long count = repository.count()+1;
        return String.format("CUS%03d",count);
    }
	
    public Customer getCustomerProfile(String customerId){

        return repository.findById(customerId)
                .orElseThrow(
                    () -> new ResourceNotFoundException(
                            "Customer not found"
                    )
                );
    }
}
