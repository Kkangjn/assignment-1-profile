package com.sparta.assignment1profile.controller;

import com.sparta.assignment1profile.dto.ProfileRequestDto;
import com.sparta.assignment1profile.dto.ProfileResponseDto;
import com.sparta.assignment1profile.entity.Profile;
import com.sparta.assignment1profile.service.ProfileService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProfileController {
    private final ProfileService profileService;
    private final Map<Long, Profile> profileList = new HashMap<>();
    private LocalDateTime currentTime;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/profile")
    public ProfileResponseDto createProfile(@RequestBody ProfileRequestDto requestDto){
        return profileService.createProfile(requestDto);
    }

    @GetMapping("/profiles")
    public List<ProfileResponseDto> getProfiles(){
        return profileService.getProfiles();
    }

    @GetMapping("/profile/{id}")
    public ProfileResponseDto getprofile(@PathVariable long id){
        if (profileList.containsKey(id)){ // id가 있는지 확인
            ProfileResponseDto responseProfile = new ProfileResponseDto(profileList.get(id));
            return responseProfile;
        } else {
            throw new IllegalArgumentException("게시글이 존재하지 않습니다.");
        }
    }

    @PutMapping("/profile/{id}")
    public ProfileResponseDto updateProfile(@PathVariable long id, @RequestBody ProfileRequestDto requestDto){
        if(profileList.containsKey(id)){
            Profile profileReversion = profileList.get(id);
            Profile profileChange = new Profile(requestDto);
            currentTime = LocalDateTime.now();
            profileChange.setUpdateAt(currentTime);
            if (profileReversion.getPw().equals(profileChange.getPw())){ // 비밀번호 확인
                profileReversion.update(profileChange);
                ProfileResponseDto responseProfile = new ProfileResponseDto(profileReversion);
                return responseProfile;
            } else {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        }else {
            throw new IllegalArgumentException("게시글이 존재하지 않습니다.");
        }
    }

    @DeleteMapping("/profile/{id}")
    public String deleteProfile(@PathVariable long id, @RequestBody String pw){
        if(profileList.containsKey(id)){
            Profile profile = profileList.get(id);
            if (profile.getPw().equals(pw)){ // 비밀번호 확인
                profileList.remove(profile.getId());
                return "게시글이 삭제되었습니다.";
            } else {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        }else {
            throw new IllegalArgumentException("게시글이 존재하지 않습니다.");
        }
    }
}
// 시간이 안바뀜! final로 지정해서 그런가? // 여전히 처음에서 안바뀜;;
// Class 필드로 선언 및 생성을 해줘서 그런듯 // 선언만 하고 메서드 내부에서 입력해주면? 성공(POST image 4,5)
// 시작할때 착각해서 문제발생 - 게시글인데 명명을 profile로 하니깐 좀 이상함;; -> 바꾸려다 포기함 다음에는 신경쓰자!
// profileOrigin.setUpdateAt(currentTime); // update에 넣어주는게 더 좋나? // 더 보기 좋은듯!
// ProfileResponseDto responseProfile = new ProfileResponseDto(profileOrigin); // 원본을 다시 보내는 느낌이라 별로임
// if (profileReversion.getPw().equals(profileChange.getPw())){ 바꿨더니 여기가 맘에 안든다.. 그냥 넘어감
// throw new IllegalArgumentException("비밀번호가 일치하지 않습니다."); 이게 postman에 뜰줄 알았는데 intellij에 뜸, 일단 뜨긴하니깐 넘어감
// 비밀번호를 String 으로 받았는데 문제 없을까?, Postman에서 삭제는 잘되는거 확인(raw-text-1234)