package com.springmvc.unid.domain;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Notify { // 사용자의 알림함에 존재할 알림함 객체

    private Long type; // 알림의 종류

    private String sendId; // 발신자의 id - 복합키에 포함

    private String receiveId; // 수신자의 id - 복합키에 포함
    // 위 두 아이디를 합한 복합 키가 기본키로 쓰이도록 한다. 또한 위 두 id는 user 테이블의 id를 참조한다.

    private String sendName; // 발신자의 별명

    private String teamName; // 발신자가 지원한(소속된) 팀명

    private String contents; // 알림(지원서, 가입승인, 가입거절, 탈퇴, 종료) 내용

    private List<String> links; // 지원자(승인한 팀장)이 첨부한 링크
}
