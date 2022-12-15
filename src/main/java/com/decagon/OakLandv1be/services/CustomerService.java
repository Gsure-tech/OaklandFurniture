package com.decagon.OakLandv1be.services;

import com.decagon.OakLandv1be.dto.EditProfileRequestDto;
import com.decagon.OakLandv1be.dto.SignupRequestDto;
import com.decagon.OakLandv1be.exceptions.AlreadyExistsException;
import com.decagon.OakLandv1be.utils.ApiResponse;

public interface CustomerService {
    ApiResponse saveCustomer(SignupRequestDto signupRequestDto) throws AlreadyExistsException;
    EditProfileRequestDto editProfile(Long userId, EditProfileRequestDto editProfileRequestDto);
}
