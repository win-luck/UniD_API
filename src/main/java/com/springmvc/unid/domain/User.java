package com.springmvc.unid.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    private String id; // 사용자 id

    private String name; // 사용자 별명

    private String pw; // 사용자 비밀번호

    private String university; // 사용자 소속 대학

    private String major; // 사용자의 소속 학과

    private String link; // 사용자의 링크

    private List<userNotify> myNotifies; // 사용자가 가지고 있는 알림 명단 (N:N)

    private List<teamMember> teamList; // 사용자가 소속된 팀 명단 (N:N)
}

