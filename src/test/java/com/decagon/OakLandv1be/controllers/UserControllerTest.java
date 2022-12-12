package com.decagon.OakLandv1be.controllers;

import com.decagon.OakLandv1be.dto.EditProfileRequestDto;
import com.decagon.OakLandv1be.enums.Gender;
import com.decagon.OakLandv1be.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    void editProfile(){
        try {
            EditProfileRequestDto userProfile = new EditProfileRequestDto();
            userProfile.setFirstName("Mack");
            userProfile.setLastName("Remy");
            userProfile.setEmail("remy@gmail.com");
            userProfile.setGender(Gender.FEMALE);
            userProfile.setPhone("09012342134");

            String requestBody = mapper.writeValueAsString(userProfile);

            mockMvc.perform(post("/user/edit-profile/{userId}")
                            .contentType("application/json").content(requestBody))
                    .andExpect(status().isOk());

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}