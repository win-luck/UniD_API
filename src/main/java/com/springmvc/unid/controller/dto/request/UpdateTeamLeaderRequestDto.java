package com.springmvc.unid.controller.dto.request;

import lombok.Data;

@Data
public class UpdateTeamLeaderRequestDto {
    private Long leaderId;
    private Long nextId;
}
