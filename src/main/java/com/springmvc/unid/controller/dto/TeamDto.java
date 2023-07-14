package com.springmvc.unid.controller.dto;

import com.springmvc.unid.domain.Team;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;

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
}
