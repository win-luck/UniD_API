package com.springmvc.unid.controller.dto;

import com.springmvc.unid.domain.Requirement;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequirementDto {

    private Long requirementId;
    private String position;
    private Long teamId;
    private Long n;
    private String requireContents;

    public RequirementDto(Requirement requirement) {
        this.requirementId = requirement.getId();
        this.position = requirement.getPosition();
        this.teamId = requirement.getTeam().getId();
        this.n = requirement.getN();
        this.requireContents = requirement.getRequireContents();
    }
}
