package com.decagon.OakLandv1be.controllers;

import com.decagon.OakLandv1be.dto.EditProfileRequestDto;
import com.decagon.OakLandv1be.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1")
public class CustomerController {
    private final CustomerService customerService;

    @PutMapping("/customer/edit-profile/{customerId}")
    public ResponseEntity<EditProfileRequestDto> editProfile(@PathVariable Long customerId, @RequestBody EditProfileRequestDto editProfileRequestDto){
        return new ResponseEntity<>(customerService.editProfile(customerId, editProfileRequestDto), HttpStatus.OK);
    }
}
