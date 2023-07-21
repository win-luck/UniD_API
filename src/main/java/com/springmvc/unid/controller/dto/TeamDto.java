package com.springmvc.unid.controller.dto;

import com.springmvc.unid.domain.Team;
import com.springmvc.unid.domain.TeamMember;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * TeamDto의 용도
 * 1. 팀 생성 Request/Response
 * 2. 팀 정보 수정 Request/Response
 * 3. 팀 조회 결과 Response
 * 4. 팀 삭제 Request/Response
 */
@Data
@NoArgsConstructor
public class TeamDto {

    private Long teamId;
    private String name;
    private String leader;
    private String oneLine;
    private String description;
    private String university;
    private String link;
    private List<RequirementDto> requirementList;

    public TeamDto(Team team) {
        this.teamId = team.getId();
        this.name = team.getName();
        this.leader = team.getUser().getName();
        this.oneLine = team.getOneLine();
        this.description = team.getDescription();
        this.university = team.getUniversity();
        this.link = team.getLink();
        this.requirementList = team.getRequirementList().stream().map(RequirementDto::new).collect(toList());
    }

    public TeamDto(TeamMember teamMember) {
        this.teamId = teamMember.getTeam().getId();
        this.name = teamMember.getTeam().getName();
        this.leader = teamMember.getTeam().getUser().getName();
        this.oneLine = teamMember.getTeam().getOneLine();
        this.description = teamMember.getTeam().getDescription();
        this.university = teamMember.getTeam().getUniversity();
        this.link = teamMember.getTeam().getLink();
        this.requirementList = teamMember.getTeam().getRequirementList().stream().map(RequirementDto::new).collect(toList());
    }
}
