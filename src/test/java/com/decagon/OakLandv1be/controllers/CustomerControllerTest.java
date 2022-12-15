package com.decagon.OakLandv1be.controllers;

import com.decagon.OakLandv1be.dto.EditProfileRequestDto;
import com.decagon.OakLandv1be.entities.Customer;
import com.decagon.OakLandv1be.entities.Person;
import com.decagon.OakLandv1be.enums.Gender;
import com.decagon.OakLandv1be.repositries.CustomerRepository;
import com.decagon.OakLandv1be.services.serviceImpl.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerTest {

    @Mock
    CustomerServiceImpl customerService;

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerController customerController;

    Person person;

    Customer customer;
    EditProfileRequestDto editProfileRequestDto;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        customer = new Customer();
        customer.setPerson(person);

        editProfileRequestDto = new EditProfileRequestDto();
        editProfileRequestDto.setFirstName("John");
    }

    @Test
    void editProfile(){
       when(customerRepository.save(any(Customer.class)))
               .thenReturn(customer);

       ResponseEntity<EditProfileRequestDto> userProfile = customerController.editProfile(1L,
                                                                                editProfileRequestDto);
        assertNotNull(userProfile);
        assertEquals(200, HttpStatus.OK.value());
    }
}