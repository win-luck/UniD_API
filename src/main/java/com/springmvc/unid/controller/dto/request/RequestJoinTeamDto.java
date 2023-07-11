package com.springmvc.unid.controller.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestJoinTeamDto { // 팀 가입 요청
    private Long teamId;
}
