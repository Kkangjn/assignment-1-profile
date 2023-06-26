package com.sparta.assignment1profile.entity;

import com.sparta.assignment1profile.dto.ProfileRequestDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Profile {
    private long id;
    private String title;
    private String name;
    private String contents;
    private String pw;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Profile(ProfileRequestDto requestDto) {
       this.title = requestDto.getTitle();
       this.name = requestDto.getName();
       this.contents = requestDto.getContents();
       this.pw = requestDto.getPw();
    }
}
