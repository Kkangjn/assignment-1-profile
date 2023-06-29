package com.sparta.assignment1profile.service;

import com.sparta.assignment1profile.dto.ProfileRequestDto;
import com.sparta.assignment1profile.dto.ProfileResponseDto;
import com.sparta.assignment1profile.entity.Profile;
import com.sparta.assignment1profile.repository.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return profileRepository.findAllByOrderByCreateAtDesc().stream().map(ProfileResponseDto::new).toList();
    }

    public ProfileResponseDto getprofile(long id) {
        Profile profile = findProfile(id);
        return new ProfileResponseDto(profile);
    }
    @Transactional
    public ProfileResponseDto updateProfile(long id, ProfileRequestDto requestDto) {
        Profile profile = findProfile(id);
        checkPw(profile.getPw(), requestDto.getPw());
        profile.update(requestDto);
        return new ProfileResponseDto(profile);
    }
    public String deleteProfile(long id, String pw) {
        Profile profile = findProfile(id);
        checkPw(profile.getPw(), pw);
        profileRepository.delete(profile);
        return "게시글이 삭제되었습니다.";
    }

    private Profile findProfile(long id) {
        return profileRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.")
        );
    }

    private void checkPw(String idPw, String inputPw) {
        if(!idPw.equals(inputPw)){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
// 비밀번호 확인 따로 빼기
//    private Profile findProfile(long id, String pw) {
//        return profileRepository.findByIdAndPw(id,pw).orElseThrow(() ->
//                new IllegalArgumentException("게시글이 존재하지 않거나 비밀번호가 틀렸습니다.")
//        );
//    }
