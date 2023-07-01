package com.springmvc.unid.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User{

    private String id; // 사용자 id

    private String name; // 사용자 별명

    private String pw; // 사용자 비밀번호

    private String university; // 사용자 소속 대학

    private String major; // 사용자의 소속 학과

    private String email; // 사용자의 이메일

    private ArrayList<Notify> notifies; // 사용자가 가지고 있는 알림 명단
}

