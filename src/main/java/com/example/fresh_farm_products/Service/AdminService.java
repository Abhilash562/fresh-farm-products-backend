package com.example.fresh_farm_products.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.fresh_farm_products.Config.JwtUtil;
import com.example.fresh_farm_products.DTO.AdminLoginResponse;
import com.example.fresh_farm_products.DTO.AdminRegisterRequest;
import com.example.fresh_farm_products.Entity.Admin;
import com.example.fresh_farm_products.Entity.AdminLoginRequest;
import com.example.fresh_farm_products.Repository.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
    private AdminRepository repository;

    @Autowired
    private PasswordEncoder encoder;
    
    @Autowired
    private JwtUtil jwtUtil;

    public Admin register(AdminRegisterRequest request) {

        Admin admin = new Admin();

        admin.setName(
                request.getName()
        );

        admin.setEmail(
                request.getEmail()
        );

        admin.setPassword(
                encoder.encode(
                        request.getPassword()
                )
        );

        return repository.save(admin);
    }
    
    public AdminLoginResponse login(AdminLoginRequest request) {

        Admin admin = repository
                .findByEmail(request.getEmail())
                .orElseThrow(
                        () -> new RuntimeException("Admin not found")
                );

        boolean passwordMatch = encoder.matches(
                request.getPassword(),
                admin.getPassword()
        );

        if (!passwordMatch) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(
                admin.getEmail()
        );

        return new AdminLoginResponse(
                admin.getId(),
                admin.getName(),
                admin.getEmail(),
                token
        );
    }
}
