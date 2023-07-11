package com.springmvc.unid.controller.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateTeamLeaderRequestDto {
    private Long leaderId;
    private Long nextId;
}
