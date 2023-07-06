package com.springmvc.unid.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "team")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {

    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long id;

    // 각 팀 테이블은 팀장의 id를 외래키로 가짐
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 팀장

    private String oneLine; // 팀의 한줄설명

    private String description; // 팀의 구체적 설명

    private String university; // 팀의 소속 대학

    private String link; // 팀의 링크

    @OneToMany(mappedBy = "team")
    private List<Requirement> requirementList = new ArrayList<>(); // 팀원 모집 요구사항

    @OneToMany(mappedBy = "team")
    private List<teamMember> teamMembersList = new ArrayList<>(); // 소속된 팀원 명단

    // 생성 메서드
    public static Team createTeam(User user, String oneLine, String description, String university, String link) {
        Team team = new Team();
        team.setUser(user);
        team.setOneLine(oneLine);
        team.setDescription(description);
        team.setUniversity(university);
        team.setLink(link);
        return team;
    }

    // 비즈니스 로직
    public void setRequirement(Requirement requirement) {
        this.requirementList.add(requirement);
        requirement.setTeam(this);
    } // 팀원 모집 요구사항 추가 시 사용

    public void setTeamMember(teamMember teamMember) {
        this.teamMembersList.add(teamMember);
        teamMember.setTeam(this);
    } // 팀원 추가 시 사용

    public void setTeamLeader(User user) {
        this.user = user;
    } // 팀장 교체 시 사용*/
}
