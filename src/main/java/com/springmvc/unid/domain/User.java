package com.springmvc.unid.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springmvc.unid.controller.dto.request.RequestCreateUserDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 알아서 값을 생성
    @Column(name = "user_id")
    private Long id;

    private String loginId; // 사용자 id

    private String name; // 사용자 별명

    @JsonIgnore
    private String pw; // 사용자 비밀번호

    private String university; // 사용자 소속 대학

    private String major; // 사용자의 소속 학과

    private String link; // 사용자의 링크

    @OneToMany(mappedBy = "user")
    private List<UserNotify> userNotifyList = new ArrayList<>(); // 사용자가 가지고 있는 알림 명단

    @OneToMany(mappedBy = "user")
    private List<TeamMember> teamMemberList = new ArrayList<>(); // 사용자가 소속된 팀 명단

    // 생성 메서드 (테스트코드용)
    public static User createUser(String LoginId, String name, String pw, String university, String major, String link) {
        User user = new User();
        user.setLoginId(LoginId);
        user.setName(name);
        user.setPw(pw);
        user.setUniversity(university);
        user.setMajor(major);
        user.setLink(link);
        return user;
    }

    // 생성 메서드(컨트롤러 회원가입용)
    public static User createUser(RequestCreateUserDto requestCreateUserDto) {
        User user = new User();
        user.setLoginId(requestCreateUserDto.getLoginId());
        user.setName(requestCreateUserDto.getName());
        user.setPw(requestCreateUserDto.getPw());
        user.setUniversity(requestCreateUserDto.getUniversity());
        user.setMajor(requestCreateUserDto.getMajor());
        user.setLink(requestCreateUserDto.getLink());
        return user;
    }

    // 비즈니스 로직
    // 사용자 정보 수정
    public void update(String name, String university, String major, String link) {
        this.name = name;
        this.university = university;
        this.major = major;
        this.link = link;
    }
}

