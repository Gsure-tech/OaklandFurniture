package com.decagon.OakLandv1be.services.serviceImpl;

import com.decagon.OakLandv1be.dto.EditProfileRequestDto;
import com.decagon.OakLandv1be.entities.User;
import com.decagon.OakLandv1be.exceptions.NoResourceFoundException;
import com.decagon.OakLandv1be.repositries.UserRepository;
import com.decagon.OakLandv1be.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public EditProfileRequestDto editProfile(Long userId, EditProfileRequestDto editProfileRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new NoResourceFoundException("User not found"));
        BeanUtils.copyProperties(editProfileRequestDto, user);
        userRepository.save(user);
        return editProfileRequestDto;
    }
}
