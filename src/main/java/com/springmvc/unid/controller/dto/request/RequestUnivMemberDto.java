package com.springmvc.unid.controller.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestUnivMemberDto { // 대학 내 팀 조회 등을 위한 대학명 요청
    private String university;
}
