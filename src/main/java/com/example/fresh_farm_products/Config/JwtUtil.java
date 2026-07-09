package com.example.fresh_farm_products.Config;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    
    private static final String SECRET = "mySuperSecretKey1234567890abcdef!";
    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

	public String generateToken(String customerId) {
		
		return Jwts.builder().setSubject(customerId).setIssuedAt(new Date())
              .setExpiration(new Date(System.currentTimeMillis()+ EXPIRATION_TIME))
              .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
              .compact();
	}
	
	public String extractCustomerId(String token){
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
