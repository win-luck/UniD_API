package com.springmvc.unid.controller.dto.response;

import com.springmvc.unid.domain.Team;
import com.springmvc.unid.domain.TeamMember;
import lombok.Getter;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
public class ResponseTeamDto {

    private Long teamId;
    private String name;
    private String leader;
    private String oneLine;
    private String description;
    private String university;
    private String link;
    private List<ResponseRequirementDto> requirements;

    public ResponseTeamDto(Team team) {
        this.teamId = team.getId();
        this.name = team.getName();
        this.leader = team.getUser().getName();
        this.oneLine = team.getOneLine();
        this.description = team.getDescription();
        this.university = team.getUniversity();
        this.link = team.getLink();
        this.requirements = team.getRequirements().stream().map(ResponseRequirementDto::new).collect(toList());
    }

    public ResponseTeamDto(TeamMember teamMember) {
        this.teamId = teamMember.getTeam().getId();
        this.name = teamMember.getTeam().getName();
        this.leader = teamMember.getTeam().getUser().getName();
        this.oneLine = teamMember.getTeam().getOneLine();
        this.description = teamMember.getTeam().getDescription();
        this.university = teamMember.getTeam().getUniversity();
        this.link = teamMember.getTeam().getLink();
        this.requirements = teamMember.getTeam().getRequirements().stream().map(ResponseRequirementDto::new).collect(toList());
    }
}
