package com.example.fresh_farm_products.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.fresh_farm_products.DTO.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<?>> handleAlreadyExists(
            CustomerAlreadyExistsException ex){

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ApiResponse<>(
                        false,
                        ex.getMessage(),
                        null
                ));
    }



    @ExceptionHandler(InvalidCredentialException.class)
    public ResponseEntity<ApiResponse<?>> handleInvalidCredential(
            InvalidCredentialException ex){

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ApiResponse<>(
                        false,
                        ex.getMessage(),
                        null
                ));
    }



    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleNotFound(
            ResourceNotFoundException ex){

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(
                        false,
                        ex.getMessage(),
                        null
                ));
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGeneral(
            Exception ex){

        ex.printStackTrace(); // Add this

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(
                        false,
                        ex.getMessage(),
                        null
                ));
    }


}
