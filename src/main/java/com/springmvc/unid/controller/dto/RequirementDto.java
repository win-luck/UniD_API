package com.springmvc.unid.controller.dto;

import com.springmvc.unid.domain.Requirement;
import lombok.Data;

/**
 * RequirementDto의 용도
 * 1. 팀장이 파트별 요구사항 추가 request/response
 * 2. 팀장이 파트별 요구사항 수정 request/response
 * 3. 팀장이 파트별 요구사항 삭제 request/response
 * 4. 팀장이 파트별 요구사항 조회 결과 response
 */
@Data
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
