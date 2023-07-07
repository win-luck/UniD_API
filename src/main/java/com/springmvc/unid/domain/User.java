package com.springmvc.unid.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    // 생성 메서드
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

}

