package com.springmvc.unid.dto;

import com.springmvc.unid.domain.User;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class TeamDto {
    private final String name;
    private final String leader;
    private final String oneLine;
    private final String description;
    private final String university;
    private final String link;

    public TeamDto(String name, String leader, String oneLine, String description, String university, String link) {
        this.name = name;
        this.leader = leader;
        this.oneLine = oneLine;
        this.description = description;
        this.university = university;
        this.link = link;
    }
}
