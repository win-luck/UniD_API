package com.springmvc.unid.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "notify")
@NoArgsConstructor
public class Notify {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notify_id", nullable = false)
    private Long notifyId; // 알림의 기본키

    @Column(nullable = false)
    private Long type; // 알림의 종류

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 발신자의 id - user 테이블의 user_id 외래키

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_name", nullable = false)
    private Team team; // 발신자가 지원한(소속된) 팀명 - team 테이블의 name 외래키

    @Column(nullable = false)
    private String contents; // 알림(지원서, 가입승인, 가입거절, 탈퇴, 종료) 내용

    @Column(nullable = false)
    private String link; // 지원자(승인한 팀장)이 첨부한 링크

    @OneToMany(mappedBy = "notify")
    private List<userNotify> userNotifies; // 이 알림을 수신한 사용자 명단 (N:N)

    public Notify(Long type, User user, Team team, String contents, String link) {
        this.type = type;
        this.user = user;
        this.team = team;
        this.contents = contents;
        this.link = link;
    }
}
