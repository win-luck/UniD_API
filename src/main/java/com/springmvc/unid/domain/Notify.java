package com.springmvc.unid.domain;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Notify { // 사용자의 알림함에 존재할 알림 객체

    private Long type; // 알림의 종류

    private String sendId; // 발신자의 id - 기본키

    private String sendName; // 발신자의 별명

    private String teamName; // 발신자가 지원한(소속된) 팀명

    private String contents; // 알림(지원서, 가입승인, 가입거절, 탈퇴, 종료) 내용

    private String link; // 지원자(승인한 팀장)이 첨부한 링크

    private List<userNotify> userNotifies; // 알림을 수신한 사용자 명단 (N:N)
}
