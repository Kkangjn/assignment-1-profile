package com.sparta.assignment1profile.repository;

import com.sparta.assignment1profile.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    List<Profile> findAllByOrderByIdDesc();
}
// return new ProfileResponseDto(id, title, name, contents, createAt, updateAt); // 생성자가 없다고 뜬다. profile 객체를 받는 생성자만 있음 // 그러면 할 때 마다 만들어 줘야하나? 너무 비효율 그냥 profile에 넣어서 처리하면 안되나?
// 처음에 update 를 안받았더니 Null exception이 발생함 -> 처음부터 받아주자('마지막 수정날짜 : ' 정도로 생각)
// https://u-it.tistory.com/entry/Spring-JDBC-Jdbc-Template%EC%9D%98-select-RowMapperType-%EA%B3%BC-%EC%9D%B5%EB%AA%85%ED%81%B4%EB%9E%98%EC%8A%A4-%EB%9E%8C%EB%8B%A4%EC%8B%9D%EB%AC%B8%EB%B2%95-%ED%99%9C%EC%9A%A9 - jdbcTemplate.query 참고