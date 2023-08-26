package com.springmvc.unid.controller.dto.response;

import com.springmvc.unid.domain.Requirement;
import lombok.Getter;

@Getter
public class ResponseRequirementDto {

    private Long requirementId;
    private String position;
    private int n;
    private String contents;

    public ResponseRequirementDto(Requirement requirement) {
        this.requirementId = requirement.getId();
        this.position = requirement.getPosition();
        this.n = requirement.getN();
        this.contents = requirement.getContents();
    }
}
