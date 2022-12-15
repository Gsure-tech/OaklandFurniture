package com.decagon.OakLandv1be.controllers;

import com.decagon.OakLandv1be.dto.EditProfileRequestDto;
import com.decagon.OakLandv1be.enums.Gender;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    void editProfile(){
        try {
            EditProfileRequestDto customerProfile = new EditProfileRequestDto();
            customerProfile.setFirstName("Mand");
            customerProfile.setLastName("Rope");
            customerProfile.setEmail("find@gmail.com");
            customerProfile.setGender(Gender.FEMALE);
            customerProfile.setPhone("09012342d134");

            String requestBody = mapper.writeValueAsString(customerProfile);

            mockMvc.perform(post("/user/edit-profile/{userId}")
                            .contentType("application/json").content(requestBody))
                    .andExpect(status().isOk());

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}