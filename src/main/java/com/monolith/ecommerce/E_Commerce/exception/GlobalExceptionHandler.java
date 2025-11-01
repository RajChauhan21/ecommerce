package com.monolith.ecommerce.E_Commerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class})
    public ResponseEntity<ErrorResponse> handleAuthenticationException(Exception ex,
                                                                       WebRequest request) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(HttpStatus.UNAUTHORIZED.value())
                .errorMessage(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .message("Invalid credentials")
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleCustomException(Exception e, WebRequest request){
        ErrorResponse response = ErrorResponse.builder()
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .message("Something went wrong")
                .errorMessage(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }
}
