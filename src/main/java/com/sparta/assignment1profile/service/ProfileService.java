package com.sparta.assignment1profile.service;

import com.sparta.assignment1profile.dto.ProfileRequestDto;
import com.sparta.assignment1profile.dto.ProfileResponseDto;
import com.sparta.assignment1profile.entity.Profile;
import com.sparta.assignment1profile.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public ProfileResponseDto createProfile(ProfileRequestDto requestDto) {
        Profile profile = new Profile(requestDto);
        Profile saveProfile = profileRepository.save(profile);
        ProfileResponseDto responseDto = new ProfileResponseDto(saveProfile);
        return responseDto;
    }

    public List<ProfileResponseDto> getProfiles() {
        return profileRepository.findAllByOrderByIdDesc().stream().map(ProfileResponseDto::new).toList();
    }
}
