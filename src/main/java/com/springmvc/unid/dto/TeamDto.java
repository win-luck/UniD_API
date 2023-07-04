package com.springmvc.unid.dto;

import com.springmvc.unid.domain.Team;
import lombok.Data;

@Data
public class TeamDto {
    private final String name;
    private final String leader;
    private final String oneLine;
    private final String description;
    private final String university;
    private final String link;

    public TeamDto(Team team) {
        this.name = team.getName();
        this.leader = team.getUser().getName();
        this.oneLine = team.getOneLine();
        this.description = team.getDescription();
        this.university = team.getUniversity();
        this.link = team.getLink();
    }
}
