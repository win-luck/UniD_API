package com.springmvc.unid.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CreateTeamDto { // 팀 생성 요청

    private String name;
    private Long leaderId;
    private String oneLine;
    private String description;
    private String university;
    private String link;
    private List<CreateRequirementDto> requirementList;
}
