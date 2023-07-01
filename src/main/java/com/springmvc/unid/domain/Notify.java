package com.springmvc.unid.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notify { // 사용자의 알림함에 존재할 알림함 객체

    private int type; // 알림의 종류

    private String applierId; // 발신자의 id

    private String applierName; // 발신자의 별명

    private String teamName; // 발신자가 지원한(소속된) 팀명

    private String contents; // 알림(지원서, 가입승인, 가입거절, 탈퇴, 종료) 내용

    private ArrayList<String> links; // 지원자(승인한 팀장)이 첨부한 링크
}
