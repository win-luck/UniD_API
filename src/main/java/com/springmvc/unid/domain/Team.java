package com.springmvc.unid.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "team")
@NoArgsConstructor
public class Team {
    @Id
    private String name; // 팀명 - 기본키 (팀명은 변경할 수 없음)

    // 각 팀 테이블은 팀장의 id를 외래키로 가짐
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leader", nullable = false)
    private User user; // 팀장의 id - user의 기본키가 외래키

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

    public Team(String name, User user, String oneLine, String description, String university, String link, List<Requirement> requirementList) {
        this.name = name;
        this.user = user;
        this.oneLine = oneLine;
        this.description = description;
        this.university = university;
        this.link = link;
        this.requirementList = requirementList;
    }
}
