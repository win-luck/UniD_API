package com.springmvc.unid.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
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
}

