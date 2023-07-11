package com.springmvc.unid.controller.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestUpdateTeamLeaderDto { // 팀장 변경 요청
    private Long leaderId;
    private Long nextId;
}
