package com.sparta.assignment1profile.dto;

import com.sparta.assignment1profile.entity.Profile;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class ProfileResponseDto {
    private long id;
    private String title;
    private String name;
    private String contents;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public ProfileResponseDto(Profile profile) {
        this.id = profile.getId();
        this.title = profile.getTitle();
        this.name = profile.getName();
        this.contents = profile.getContents();
        this.createAt = profile.getCreateAt();
    }
}
