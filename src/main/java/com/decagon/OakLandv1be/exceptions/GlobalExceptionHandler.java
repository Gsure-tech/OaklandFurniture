package com.decagon.OakLandv1be.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InputMismatchException.class)
    public ResponseEntity<ErrorResponse> userAlreadyExists(InputMismatchException ne){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ne.getMessage());
        errorResponse.setDebugMessage("User with provided Id already exist");
        errorResponse.setStatus(HttpStatus.CONFLICT);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFound(UserNotFoundException ne){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ne.getMessage());
        errorResponse.setDebugMessage("User not found");
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> alreadyExist(AlreadyExistsException ne){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ne.getMessage());
        errorResponse.setDebugMessage("Already exists");
        errorResponse.setStatus(HttpStatus.CONFLICT);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFound(ResourceNotFoundException ne){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ne.getMessage());
        errorResponse.setDebugMessage("Request not found");
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> productNotFound(ProductNotFoundException ne){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ne.getMessage());
        errorResponse.setDebugMessage("Product not found");
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
     }
        
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorResponse>tokenNotFound (InvalidTokenException ne){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ne.getMessage());
        errorResponse.setDebugMessage("Token not found");
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler({InsufficientBalanceInWalletException.class})
    public ResponseEntity<ErrorResponse> InsufficientBalanceInWallet(InsufficientBalanceInWalletException ne){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ne.getMessage());
        errorResponse.setDebugMessage("Oops! please fund your wallet");
        errorResponse.setStatus(HttpStatus.PAYMENT_REQUIRED);
        return new ResponseEntity<>(errorResponse, HttpStatus.PAYMENT_REQUIRED);
    }

    @ExceptionHandler({UnauthorizedUserException.class})
    public ResponseEntity<ErrorResponse> handleUnauthorizedUserException(UnauthorizedUserException ex){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getMessage())
                .debugMessage("User does not have the right access")
                .status(HttpStatus.UNAUTHORIZED).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidAttributeException.class)
    public ResponseEntity<ErrorResponse>invalidProductAttributes (InvalidAttributeException ie){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ie.getMessage());
        errorResponse.setDebugMessage("Attribute not valid or does not exist");
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse>invalidProductAttributes (MethodArgumentNotValidException ie){
        ErrorResponse errorResponse = new ErrorResponse();
        String[] errors = ie.getMessage().split(";");
        String errorMessage = errors[errors.length - 1];

        errorResponse.setMessage(errorMessage);
        errorResponse.setDebugMessage("Invalid Input Filled in Field");
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> userAlreadyExists(UsernameNotFoundException ne){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ne.getMessage());
        errorResponse.setDebugMessage(ne.getLocalizedMessage());
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse>illegalArgumentException (IllegalArgumentException ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setDebugMessage(ex.getLocalizedMessage());
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }
}
