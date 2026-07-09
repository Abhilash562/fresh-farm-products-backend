package com.example.fresh_farm_products.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.fresh_farm_products.DTO.ApiResponse;

import java.io.IOException;
import java.util.Collections;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		String authHeader = request.getHeader("Authorization");

        if (authHeader == null || 
            !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = authHeader.substring(7);

        try {
            String customerId = 
                    jwtUtil.extractCustomerId(token);
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            customerId,
                            null,
                            Collections.emptyList()
                    );

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);
        } catch (Exception e) {
            sendErrorResponse(
                    response,
                    "Invalid or expired JWT token"
            );
            return;
        }
        
        filterChain.doFilter(request, response);
    }





    private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {

        ApiResponse<?> apiResponse =
                new ApiResponse<>(
                        false,
                        message,
                        null
                );

        response.setStatus(
                HttpStatus.UNAUTHORIZED.value()
        );


        response.setContentType(
                "application/json"
        );


        objectMapper.writeValue(
                response.getWriter(),
                apiResponse
        );
    }

}
