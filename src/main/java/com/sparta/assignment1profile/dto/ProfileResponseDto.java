package com.sparta.assignment1profile.dto;

import com.sparta.assignment1profile.entity.Profile;
import lombok.Getter;

import java.sql.Timestamp;
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
        this.updateAt = profile.getUpdateAt();
    }

    public ProfileResponseDto(Long id, String title, String name, String contents, Timestamp createAt, Timestamp updateAt) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.contents = contents;
        this.createAt = createAt.toLocalDateTime();
        this.updateAt = updateAt.toLocalDateTime();
    }
}
