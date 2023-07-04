package com.springmvc.unid.domain;

import com.springmvc.unid.dto.UserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

    @Id
    private String userId; // user id - 기본키

    @Column(nullable = false)
    private String name; // 사용자 별명

    @Column(nullable = false)
    private String pw; // 사용자 비밀번호

    @Column(nullable = false)
    private String university; // 사용자 소속 대학

    @Column(nullable = false)
    private String major; // 사용자의 소속 학과

    @Column(nullable = false)
    private String link; // 사용자의 링크

    @OneToMany(mappedBy = "user")
    private List<userNotify> userNotifies; // 사용자가 가지고 있는 알림 명단 (N:N)

    @OneToMany(mappedBy = "user")
    private List<teamMember> teamMembers; // 사용자가 소속된 팀 명단 (N:N)

    public User(String name, String pw, String university, String major, String link) {
        this.name = name;
        this.pw = pw;
        this.university = university;
        this.major = major;
        this.link = link;
    }

    public User(UserDto userDto) {
        this.userId = userDto.getUserId();
        this.name = userDto.getName();
        this.pw = userDto.getPw();
        this.university = userDto.getUniversity();
        this.major = userDto.getMajor();
        this.link = userDto.getLink();
    }

    public User() {

    }
}

