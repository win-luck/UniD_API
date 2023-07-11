package com.springmvc.unid.controller.dto.request;

import com.springmvc.unid.controller.dto.TeamDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateTeamRequestDto {
    private TeamDto teamDto;
    private Long userId;
}
