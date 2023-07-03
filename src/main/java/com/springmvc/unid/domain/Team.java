package com.springmvc.unid.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "team")
public class Team {

    @Id
    private String name; // 팀명 - 기본키

    @Column(nullable = false)
    private String oneLine; // 팀의 한줄설명

    @Column(nullable = false)
    private String description; // 팀의 구체적 설명

    @Column(nullable = false)
    private String university; // 팀의 소속 대학

    @Column(nullable = true)
    private String link; // 팀의 링크

    @OneToMany(mappedBy = "team")
    private List<Requirement> requirementList; // 팀원 모집 요구사항 (1:N)

    @OneToMany(mappedBy = "team")
    private List<teamMember> teamMembersList; // 소속된 팀원 명단 (N:N)
}
