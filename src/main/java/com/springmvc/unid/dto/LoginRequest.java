package com.springmvc.unid.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private final String name;
    private final String pw;
}
