package com.springmvc.unid.controller.dto.request;

import com.springmvc.unid.domain.Requirement;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateRequirementDto {

    private String position;
    private int n;
    private String contents;

    public CreateRequirementDto(Requirement requirement) {
        this.position = requirement.getPosition();
        this.n = requirement.getN();
        this.contents = requirement.getContents();
    }
}
