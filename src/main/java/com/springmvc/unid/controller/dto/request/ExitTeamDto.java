package com.springmvc.unid.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ExitTeamDto { // 팀 탈퇴 요청
    private Long teamId;
}
