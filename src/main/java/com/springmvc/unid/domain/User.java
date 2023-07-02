package com.springmvc.unid.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserInfo {

    private String id; // 사용자 id

    private String name; // 사용자 별명

    private String pw; // 사용자 비밀번호

    private String university; // 사용자 소속 대학

    private String major; // 사용자의 소속 학과

    private List<String> links; // 사용자의 관련 링크

    private List<Notify> notifies; // 사용자가 가지고 있는 알림 명단

    private List<teamMember> teamList; // 사용자가 소속된 팀 명단
}

