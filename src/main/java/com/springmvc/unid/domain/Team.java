package com.springmvc.unid.domain;

import com.springmvc.unid.controller.dto.RequirementDto;
import com.springmvc.unid.controller.dto.TeamDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    private String name; // 팀 이름

    // 각 팀 테이블은 팀장의 id를 외래키로 가짐
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 팀장

    private String oneLine; // 팀의 한줄설명

    private String description; // 팀의 구체적 설명

    private String university; // 팀의 소속 대학

    private String link; // 팀의 링크

    @OneToMany(mappedBy = "team", cascade = {CascadeType.PERSIST}, orphanRemoval = true)
    private List<Requirement> requirements = new ArrayList<>(); // 팀원 모집 요구사항

    @OneToMany(mappedBy = "team", cascade = {CascadeType.REMOVE})
    private List<TeamMember> teamMembers = new ArrayList<>(); // 소속된 팀원 명단

    // 생성 메서드
    public static Team createTeam(String name, User user, String oneLine, String description, String university, String link) {
        Team team = new Team();
        team.name = name;
        team.user = user;
        team.oneLine = oneLine;
        team.description = description;
        team.university = university;
        team.link = link;
        return team;
    }

    // 팀 정보 수정
    public void updateTeam(TeamDto teamDto) {
        this.name = teamDto.getName();
        this.oneLine = teamDto.getOneLine();
        this.description = teamDto.getDescription();
        this.university = teamDto.getUniversity();
        this.link = teamDto.getLink();
    }

    // 팀원 모집 요구사항 추가
    public void addRequirement(Requirement requirement) {
        requirement.setTeam(this);
        this.requirements.add(requirement);
    }

    // 팀원 모집 요구사항 수정
    public void modifyRequirement(Long id, RequirementDto requirementDto) {
        for (Requirement requirement : this.requirements) {
            if (id.equals(requirement.getId())) {
                requirement.updateRequirement(requirementDto.getPosition(), requirementDto.getN(), requirementDto.getContents());
                break;
            }
        }
    }

    // 팀원 모집 요구사항 삭제
    public void deleteRequirement(Long id) {
        for(Requirement requirement : this.requirements) {
            if(Objects.equals(requirement.getId(), id)) {
                this.requirements.remove(requirement);
                break;
            }
        }
    }

    // 팀장 교체
    public void setTeamLeader(User user) {
        this.user = user;
    }

    // 팀장인지 확인
    public boolean isLeader(Long userId) {
        return this.user.getId().equals(userId);
    }
}
