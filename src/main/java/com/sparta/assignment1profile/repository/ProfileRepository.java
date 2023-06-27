package com.sparta.assignment1profile.repository;

import com.sparta.assignment1profile.dto.ProfileResponseDto;
import com.sparta.assignment1profile.entity.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ProfileRepository {
    private final JdbcTemplate jdbcTemplate;
    private LocalDateTime currentTime;
    public ProfileRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public Profile save(Profile profile) { // Controller 에서 RequestBody 로 받아온 객체로된 데이터 RequestDto 를 Service 로 넘겨줌 -> 서비스는 RequestDto 를 Profile entity 에 저장하고 저장한 entity 를 Repository 로 보냄 -> 레파지토리는 entity 로 DB에 저장함
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체
        currentTime = LocalDateTime.now();
        profile.setCreateAt(currentTime);
        profile.setUpdateAt(currentTime);

        String sql = "INSERT INTO profile (title, name, contents, pw, createAt, updateAt) VALUES (?, ?, ?, ?, ?, ?)"; // ? 로 동적으로 처리해줌 // String 형식을 SQL 형식으로 맞춤
        jdbcTemplate.update( con -> { // DB에 저장
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS); // profile 형식(sql)에 기본키를 달아서 저장

                    preparedStatement.setString(1, profile.getTitle()); // 1번째 ? // sql 형식에 첫번째 ?에 (@Requestbody -> requestDto -> )Entity로 만든 제목을 넣어줌
                    preparedStatement.setString(2, profile.getName()); // 2번째 ? // sql 형식에 두번째 ?에 (@Requestbody -> requestDto -> )Entity로 만든 이름를 넣어줌
                    preparedStatement.setString(3, profile.getContents());
                    preparedStatement.setString(4, profile.getPw());
                    preparedStatement.setTimestamp(5, Timestamp.valueOf(profile.getCreateAt())); // preparedStatement 에 DateTime이 없다.(MySQL에만 있는 타입인가?) -> Timestamp로 형변환 시켜줌
                    preparedStatement.setTimestamp(6, Timestamp.valueOf(profile.getUpdateAt()));

                    return preparedStatement; // ?,?,?,?,? 를 저장한걸 반환
                },
                keyHolder); // 데이터, primeryKey 로 업데이트됨

        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue(); // 반환한 keyHolder에서 기본키를 가져와서 id에 저장
        profile.setId(id); // profile 객체의 id를 위에서 생성해준 기본key로 저장함

        return profile;
    }

    public List<ProfileResponseDto> findAll() {
        String sql = "SELECT * FROM profile";

        return jdbcTemplate.query(sql, new RowMapper<ProfileResponseDto>() {
            @Override
            public ProfileResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String name = rs.getString("name");
                String contents = rs.getString("contents");
                Timestamp createAt = rs.getTimestamp("createAt");
                Timestamp updateAt = rs.getTimestamp("updateAt");
                return new ProfileResponseDto(id, title, name, contents, createAt, updateAt); // 생성자가 없다고 뜬다.
            }
        });
    }
}
// return new ProfileResponseDto(id, title, name, contents, createAt, updateAt); // 생성자가 없다고 뜬다. profile 객체를 받는 생성자만 있음 // 그러면 할 때 마다 만들어 줘야하나? 너무 비효율 그냥 profile에 넣어서 처리하면 안되나?
// 처음에 update 를 안받았더니 Null exception이 발생함 -> 처음부터 받아주자('마지막 수정날짜 : ' 정도로 생각)
// https://u-it.tistory.com/entry/Spring-JDBC-Jdbc-Template%EC%9D%98-select-RowMapperType-%EA%B3%BC-%EC%9D%B5%EB%AA%85%ED%81%B4%EB%9E%98%EC%8A%A4-%EB%9E%8C%EB%8B%A4%EC%8B%9D%EB%AC%B8%EB%B2%95-%ED%99%9C%EC%9A%A9 - jdbcTemplate.query 참고