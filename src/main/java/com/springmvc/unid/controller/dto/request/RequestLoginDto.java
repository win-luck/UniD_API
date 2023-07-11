package com.springmvc.unid.controller.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RequestLoginDto의 용도
 * 1. 로그인을 위해 id와 pw를 클라이언트가 서버로 전송
 */
@Data
@NoArgsConstructor
public class RequestLoginDto {
    private String loginId;
    private String pw;

    public RequestLoginDto(String loginId, String pw) {
        this.loginId = loginId;
        this.pw = pw;
    }
}
