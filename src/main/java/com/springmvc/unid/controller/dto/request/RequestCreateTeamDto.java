package com.springmvc.unid.controller.dto.request;

import com.springmvc.unid.controller.dto.RequirementDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RequestCreateTeamDto { // 팀 생성 요청
    private String name;
    private String user;
    private String oneLine;
    private String description;
    private String university;
    private String link;
    private List<RequirementDto> requirementList;
}
