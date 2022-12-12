package com.decagon.OakLandv1be.controllers;

import com.decagon.OakLandv1be.dto.EditProfileRequestDto;
import com.decagon.OakLandv1be.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
public class UserController {
    private final UserService userService;

    @PutMapping("/user/edit-profile/{userId}")
    public ResponseEntity<EditProfileRequestDto> editProfile(@PathVariable Long userId, @RequestBody EditProfileRequestDto editProfileRequestDto){
        return new ResponseEntity<>(userService.editProfile(userId, editProfileRequestDto), HttpStatus.OK);
    }
}
