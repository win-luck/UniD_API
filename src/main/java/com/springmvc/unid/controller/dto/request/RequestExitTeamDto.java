package com.springmvc.unid.controller.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestExitTeamDto { // 팀에서 탈퇴 요청
    private Long teamId;
}
