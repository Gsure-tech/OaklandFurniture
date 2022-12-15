package com.decagon.OakLandv1be.services.serviceImpl;

import com.decagon.OakLandv1be.dto.EditProfileRequestDto;
import com.decagon.OakLandv1be.entities.Person;
import com.decagon.OakLandv1be.repositries.PersonRepository;
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

class CustomerServiceImplTest {

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    CustomerServiceImpl customerService;

    Person person;
    EditProfileRequestDto editProfileRequestDto;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        person = new Person();

        editProfileRequestDto = new EditProfileRequestDto();
        editProfileRequestDto.setFirstName("Mary");
    }

    @Test
    void editProfile() {
        when(personRepository.findById(anyLong()))
                .thenReturn(Optional.of(person));
        when(personRepository.save(any(Person.class)))
                .thenReturn(person);

        EditProfileRequestDto userProfile = customerService.editProfile(1L, editProfileRequestDto);
        assertNotNull(userProfile);
        assertEquals("Mary", userProfile.getFirstName());
    }
}