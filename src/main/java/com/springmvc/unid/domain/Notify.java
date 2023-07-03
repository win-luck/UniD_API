package com.springmvc.unid.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "notify")
public class Notify { // 사용자의 알림함에 존재할 알림 객체

    @Column(nullable = false)
    private Long type; // 알림의 종류

    @Id
    private String sendId; // 발신자의 id - 기본키

    @Column(nullable = false)
    private String sendName; // 발신자의 별명

    @Column(nullable = false)
    private String teamName; // 발신자가 지원한(소속된) 팀명

    @Column(nullable = false)
    private String contents; // 알림(지원서, 가입승인, 가입거절, 탈퇴, 종료) 내용

    @Column(nullable = false)
    private String link; // 지원자(승인한 팀장)이 첨부한 링크

    @OneToMany(mappedBy = "notify")
    private List<userNotify> userNotifies; // 알림을 수신한 사용자 명단 (N:N)
}
