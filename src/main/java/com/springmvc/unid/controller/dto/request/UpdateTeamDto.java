package com.springmvc.unid.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateTeamDto { // 팀 정보 수정 요청

    private Long userId;
    private String name;
    private String oneLine;
    private String description;
    private String university;
    private String link;
}
