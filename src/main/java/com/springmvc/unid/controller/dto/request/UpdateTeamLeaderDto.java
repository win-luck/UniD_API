package com.springmvc.unid.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateTeamLeaderDto { // 팀장 변경 요청

    private Long leaderId;
    private String nextLeaderName;

    public UpdateTeamLeaderDto(Long leaderId, String nextLeaderName) {
        this.leaderId = leaderId;
        this.nextLeaderName = nextLeaderName;
    }
}
