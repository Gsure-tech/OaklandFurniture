package com.decagon.OakLandv1be.services;

import com.decagon.OakLandv1be.dto.EditProfileRequestDto;

public interface UserService {
    EditProfileRequestDto editProfile(Long userId, EditProfileRequestDto editProfileRequestDto);
}
