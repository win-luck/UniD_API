package com.springmvc.unid.dto;

import com.springmvc.unid.domain.Requirement;
import com.springmvc.unid.domain.Team;
import lombok.Data;

import java.util.List;

@Data
public class TeamDto {
    private final String name;
    private final String leader;
    private final String oneLine;
    private final String description;
    private final String university;
    private final String link;
    private final List<Requirement> requirements;

    public TeamDto(Team team) {
        this.name = team.getName();
        this.leader = team.getUser().getName();
        this.oneLine = team.getOneLine();
        this.description = team.getDescription();
        this.university = team.getUniversity();
        this.link = team.getLink();
        this.requirements = team.getRequirementList();
    }
}
