package com.decagon.OakLandv1be.controllers;

import com.decagon.OakLandv1be.controllers.UserController;
import com.decagon.OakLandv1be.dto.EditProfileRequestDto;
import com.decagon.OakLandv1be.entities.User;
import com.decagon.OakLandv1be.enums.Gender;
import com.decagon.OakLandv1be.repositries.UserRepository;
import com.decagon.OakLandv1be.services.serviceImpl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @Mock
    UserServiceImpl userService;

    @InjectMocks
    UserController userController;

    User user;
    EditProfileRequestDto editProfileRequestDto;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        user = new User();
    }

    @Test
    void editProfile() {
        when(userService.editProfile(anyLong(), any(EditProfileRequestDto.class)))
                .thenReturn(editProfileRequestDto);

        ResponseEntity<EditProfileRequestDto> userProfile = userController.editProfile(1L, editProfileRequestDto);
        assertNotNull(userProfile);
        assertEquals(200, userProfile.getStatusCode().value());
    }
}