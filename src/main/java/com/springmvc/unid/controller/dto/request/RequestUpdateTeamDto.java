package com.springmvc.unid.controller.dto.request;

import com.springmvc.unid.controller.dto.TeamDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestUpdateTeamDto { // 팀 정보 수정 요청
    private TeamDto teamDto;
    private Long userId;
}
