package com.decagon.OakLandv1be.serviceImpl;

import com.decagon.OakLandv1be.dto.EditProfileRequestDto;
import com.decagon.OakLandv1be.entities.User;
import com.decagon.OakLandv1be.repositries.UserRepository;
import com.decagon.OakLandv1be.services.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    User user;
    EditProfileRequestDto editProfileRequestDto;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        user = new User();

        editProfileRequestDto = new EditProfileRequestDto();
        editProfileRequestDto.setFirstName("Mary");
    }

    @Test
    void editProfile() {
        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class)))
                .thenReturn(user);

        EditProfileRequestDto userProfile = userService.editProfile(1L, editProfileRequestDto);
        assertNotNull(userProfile);
        assertEquals("Mary", userProfile.getFirstName());
    }
}