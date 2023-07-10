package com.springmvc.unid.controller.dto.request;

import lombok.Data;

@Data
public class RequestLoginDto {
    private String loginId;
    private String pw;

    public RequestLoginDto(String loginId, String pw) {
        this.loginId = loginId;
        this.pw = pw;
    }
}
