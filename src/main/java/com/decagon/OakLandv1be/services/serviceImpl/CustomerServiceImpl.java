package com.decagon.OakLandv1be.services.serviceImpl;

import com.decagon.OakLandv1be.dto.EditProfileRequestDto;
import com.decagon.OakLandv1be.entities.Person;
import com.decagon.OakLandv1be.exceptions.NoResourceFoundException;
import com.decagon.OakLandv1be.repositries.PersonRepository;
import com.decagon.OakLandv1be.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final PersonRepository personRepository;

    @Override
    public EditProfileRequestDto editProfile(Long customerId, EditProfileRequestDto editProfileRequestDto) {
        Person person = personRepository.findById(customerId)
                .orElseThrow(()-> new NoResourceFoundException("User not found"));
        BeanUtils.copyProperties(editProfileRequestDto, person);
        personRepository.save(person);
        return editProfileRequestDto;
    }
}
