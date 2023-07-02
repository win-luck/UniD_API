package com.springmvc.unid.domain;

import com.springmvc.unid.common.Pair;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class Team {

    private String name; // 팀명 - 기본키

    private String oneLine; // 팀의 한줄설명

    private String description; // 팀의 구체적 설명

    private String university; // 팀의 소속 대학

    private String link; // 팀의 링크

    private List<requirement> require; // 팀원 모집 요구사항 (1:N)

    private List<teamMember> teamMembers; // 소속된 팀원 명단 (N:N)
}
