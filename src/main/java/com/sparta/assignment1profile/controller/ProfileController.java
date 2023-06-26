package com.sparta.assignment1profile.controller;

import com.sparta.assignment1profile.dto.ProfileRequestDto;
import com.sparta.assignment1profile.dto.ProfileResponseDto;
import com.sparta.assignment1profile.entity.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProfileController {
    private final Map<Long, Profile> profileList = new HashMap<>();
    private final LocalDateTime currentTime = LocalDateTime.now();
    @PostMapping("/profile")
    public ProfileResponseDto createProfile(@RequestBody ProfileRequestDto requestDto){
        Profile profile = new Profile(requestDto);
        long maxId = profileList.size()>0 ? Collections.max(profileList.keySet())+1 : 1;
        profile.setId(maxId);
        profile.setPw(requestDto.getPw());
        profile.setCreateAt(currentTime);
        profileList.put(profile.getId(),profile);
        ProfileResponseDto responseDto = new ProfileResponseDto(profile);
        return responseDto;
    }
}
