package com.sparta.assignment1profile.entity;

import com.sparta.assignment1profile.dto.ProfileRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "profile")
@NoArgsConstructor
public class Profile extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "contents", nullable = false, length = 500)
    private String contents;
    @Column(name = "pw", nullable = false)
    private String pw;

    public Profile(ProfileRequestDto requestDto) {
       this.title = requestDto.getTitle();
       this.name = requestDto.getName();
       this.contents = requestDto.getContents();
       this.pw = requestDto.getPw();
    }

    public void update(ProfileRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.name = requestDto.getName();
        this.contents = requestDto.getContents();
    }
}
