package com.springmvc.unid.controller.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestDeleteRequirementDto { // 팀의 요구사항 삭제 요청
    private Long requirementId;
    private Long userId;
}
