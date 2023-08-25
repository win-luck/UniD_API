package com.springmvc.unid.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
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

    @OneToMany(mappedBy = "user", cascade = {CascadeType.REMOVE})
    private List<UserNotify> userNotifies = new ArrayList<>(); // 사용자가 가지고 있는 알림 명단

    @OneToMany(mappedBy = "user", cascade = {CascadeType.REMOVE})
    private List<TeamMember> teamMembers = new ArrayList<>(); // 사용자가 소속된 팀 명단

    // 생성 메서드
    public static User createUser(String LoginId, String name, String pw, String university, String major, String link) {
        User user = new User();
        user.loginId = LoginId;
        user.name = name;
        user.pw = pw;
        user.university = university;
        user.major = major;
        user.link = link;
        return user;
    }

    // 사용자 정보 수정
    public void update(String name, String university, String major, String link) {
        this.name = name;
        this.university = university;
        this.major = major;
        this.link = link;
    }
}

