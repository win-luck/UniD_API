package com.springmvc.unid.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Requirement {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "requirement_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team; // Team과의 다대일 관계

    private String position; // 구인 파트

    private int n; // 구인 파트별 팀원 수

    private String contents; // 요구사항

    // 생성 메서드
    public static Requirement createRequirement(String position, int n, String contents) {
        Requirement requirement = new Requirement();
        requirement.position = position;
        requirement.n = n;
        requirement.contents = contents;
        return requirement;
    }

    // 요구사항 수정
    public void updateRequirement(String position, int n, String contents) {
        this.position = position;
        this.n = n;
        this.contents = contents;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
