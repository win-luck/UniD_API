package com.springmvc.unid.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteRequirementDto { // 팀 요구사항 삭제 요청
    private Long requirementId;
    private Long userId;
}
