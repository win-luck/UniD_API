package com.springmvc.unid.controller.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestLoginDto { // 회원 로그인 요청
    private String loginId;
    private String pw;
}
