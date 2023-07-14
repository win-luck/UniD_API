package com.springmvc.unid.controller.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestUnivMemberDto { // 대학명 요청 (대학 소속 팀 목록 조회를 위함)
    private String university;
}
