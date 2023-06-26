package com.sparta.assignment1profile.controller;

import com.sparta.assignment1profile.dto.ProfileRequestDto;
import com.sparta.assignment1profile.dto.ProfileResponseDto;
import com.sparta.assignment1profile.entity.Profile;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProfileController {
    private final Map<Long, Profile> profileList = new HashMap<>();
    private LocalDateTime currentTime;
    @PostMapping("/profile")
    public ProfileResponseDto createProfile(@RequestBody ProfileRequestDto requestDto){
        currentTime = LocalDateTime.now();
        Profile profile = new Profile(requestDto);
        long maxId = profileList.size()>0 ? Collections.max(profileList.keySet())+1 : 1;
        profile.setId(maxId);
        profile.setPw(requestDto.getPw());
        profile.setCreateAt(currentTime);
        profile.setUpdateAt(currentTime); // 안넣어줘도 상관없나? Map과 ResponseDto에 Null값이 들어가나? // 그냥 마지막 수정 날짜 정도로 생각하자!
        profileList.put(profile.getId(),profile);
        ProfileResponseDto responseDto = new ProfileResponseDto(profile);
        return responseDto;
    }

    @GetMapping("/profiles")
    public List<ProfileResponseDto> getProfiles(){
        List<ProfileResponseDto> responseList = profileList.values().stream().map(ProfileResponseDto::new).toList();
        // 맵으로된 프로필리스트(id,profile)의. value값들(Porfile). 반복해줘. ProfileResponseDto로 만들어. 리스트로 바꿔줘;
        return responseList;
    }
    // 시간이 안바뀜! final로 지정해서 그런가? // 여전히 처음에서 안바뀜;;
    // Class 필드로 선언 및 생성을 해줘서 그런듯 // 선언만 하고 메서드 내부에서 입력해주면? 성공(POST image 4,5)
}
