package com.springmvc.unid.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private final String accessToken;

    public LoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
