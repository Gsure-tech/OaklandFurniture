package com.decagon.OakLandv1be.services;

import com.decagon.OakLandv1be.dto.EditProfileRequestDto;

public interface CustomerService {
    EditProfileRequestDto editProfile(Long userId, EditProfileRequestDto editProfileRequestDto);
}
